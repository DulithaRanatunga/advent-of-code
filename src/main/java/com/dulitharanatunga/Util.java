package com.dulitharanatunga;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Util {

    // Reads the input file and returns a list of lines.
    public static List<String> readInput(String fileName) {
        try {
            return Files.readAllLines(Path.of("src/main/java/resources/2022/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
