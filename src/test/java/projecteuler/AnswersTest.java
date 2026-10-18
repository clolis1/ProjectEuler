package projecteuler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

/** Runs every solved problem and checks it against the known answer in answers.csv. */
class AnswersTest {
    private static final Duration TIME_LIMIT = Duration.ofMinutes(1);

    @TestFactory
    Stream<DynamicTest> everySolvedProblemGivesTheKnownAnswer() throws IOException {
        Map<Integer, String> answers = loadAnswers();
        return Problems.solved().keySet().stream().map(n -> DynamicTest.dynamicTest("Problem " + n, () -> {
            String expected = answers.get(n);
            Assumptions.assumeTrue(expected != null, "No known answer for problem " + n + " in answers.csv");
            String actual = assertTimeoutPreemptively(TIME_LIMIT, () -> Problems.create(n).solve());
            assertEquals(expected, actual);
        }));
    }

    private static Map<Integer, String> loadAnswers() throws IOException {
        Map<Integer, String> answers = new TreeMap<>();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(
                AnswersTest.class.getResourceAsStream("/answers.csv"), StandardCharsets.UTF_8))) {
            String line;
            while ((line = in.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                String[] parts = line.split(",", 2);
                answers.put(Integer.parseInt(parts[0].trim()), parts[1].trim());
            }
        }
        return answers;
    }
}
