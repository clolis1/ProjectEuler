package projecteuler.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Loads problem input files from src/main/resources/data, so solutions work no matter
 * which directory the program is started from.
 */
public class Resources {
    private Resources() {}

    private static InputStream open(String name) {
        InputStream in = Resources.class.getResourceAsStream("/data/" + name);
        if (in == null) throw new IllegalArgumentException("Missing resource: data/" + name);
        return in;
    }

    public static Scanner scanner(String name) {
        return new Scanner(open(name), StandardCharsets.UTF_8);
    }

    public static BufferedReader reader(String name) {
        return new BufferedReader(new InputStreamReader(open(name), StandardCharsets.UTF_8));
    }
}
