package projecteuler;

import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import projecteuler.problems.Problem;

/**
 * Command-line runner for the Project Euler solutions.
 *
 *   gradlew run --args="12"      solve problem 12 and show the time taken
 *   gradlew run --args="12e"     show the explanation for problem 12
 *   gradlew run --args="all"     solve every problem and print a timing table
 *   gradlew run                  interactive mode (same commands, Q to quit)
 */
public class Driver {
    private static final Path EXPLANATIONS = Path.of("explanations");
    private static final int TIME_LIMIT_SECONDS = 60;

    public static void main(String[] args) throws IOException {
        // Explanations use Unicode math (², √, ≤), so always write UTF-8 rather than the platform default.
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, StandardCharsets.UTF_8));

        if (args.length > 0) {
            handle(String.join("", args).trim());
            return;
        }

        System.out.println("Project Euler solutions — " + Problems.solved().size() + " problems solved.");
        System.out.println("Enter a number to solve it, a number followed by 'e' (e.g. 12e) for its explanation,");
        System.out.println("'all' to run everything, or Q to quit.");

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        while (true) {
            System.out.print("\n> ");
            String line = in.readLine();
            if (line == null || line.trim().equalsIgnoreCase("q")) break;
            if (!line.isBlank()) handle(line.trim());
        }
        System.out.println("Bye!");
    }

    private static void handle(String input) throws IOException {
        if (input.equalsIgnoreCase("all")) {
            runAll();
            return;
        }

        boolean explain = input.endsWith("e") || input.endsWith("E");
        String digits = explain ? input.substring(0, input.length() - 1) : input;
        if (!digits.matches("\\d+")) {
            System.out.println("Enter a problem number, a number followed by 'e', 'all', or Q.");
            return;
        }

        int n = Integer.parseInt(digits);
        if (explain) explain(n);
        else solve(n);
    }

    private static void solve(int n) {
        Problem p = Problems.create(n);
        if (p == null) {
            System.out.println("Problem " + n + " hasn't been solved yet.");
            return;
        }
        long start = System.nanoTime();
        String answer = p.solve();
        double seconds = (System.nanoTime() - start) / 1e9;
        System.out.printf("Problem %d: %s  (%.3f s)%n", n, answer, seconds);
    }

    private static void explain(int n) throws IOException {
        Path file = EXPLANATIONS.resolve(n + ".md");
        if (!Files.exists(file)) {
            System.out.println("No explanation for problem " + n + " yet.");
            return;
        }
        System.out.println(Files.readString(file, StandardCharsets.UTF_8));
    }

    private static void runAll() {
        System.out.printf("%5s  %-20s %9s%n", "#", "Answer", "Seconds");
        double total = 0;
        for (int n : Problems.solved().keySet()) {
            // Each problem runs on its own daemon thread so a slow one can be abandoned.
            FutureTask<String> task = new FutureTask<>(() -> Problems.create(n).solve());
            Thread worker = new Thread(task, "P" + n);
            worker.setDaemon(true);
            long start = System.nanoTime();
            worker.start();
            String answer;
            try {
                answer = task.get(TIME_LIMIT_SECONDS, TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                answer = "(gave up after " + TIME_LIMIT_SECONDS + " s)";
            } catch (Exception e) {
                answer = "(error: " + e.getCause() + ")";
            }
            double seconds = (System.nanoTime() - start) / 1e9;
            total += seconds;
            System.out.printf("%5d  %-20s %9.3f%n", n, answer, seconds);
        }
        System.out.printf("%5s  %-20s %9.3f%n", "", "Total", total);
    }
}
