package com.dulitharanatunga;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day1 {

    public int runSample() {
        return calculateIncreases("day1_sample");
    }

    public int run() {
       return calculateIncreases("day1");
    }

    private int calculateIncreases(String fileName) {
        List<String> lines = readInput(fileName);
        // Challenge: calculate number of lines that are more than the previous.
        Integer prevDepth = null;
        int increases = 0;
        for (int i =0; i < lines.size(); i++) {
            Integer current = Integer.parseInt(lines.get(i));
            if (prevDepth != null && prevDepth < current) {
                increases++;
            }
            prevDepth = current;
        }
        return increases;
    }

    // Reads the input file and returns a list of lines.
    public List<String> readInput(String fileName) {
        try {
            return Files.readAllLines(Path.of("src/resources/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
