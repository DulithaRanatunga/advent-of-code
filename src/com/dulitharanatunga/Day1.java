package com.dulitharanatunga;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Day1 {

    public int runSample() {
        return calculateIncreases("day1_sample");
    }

    public int run() {
       return calculateIncreases("day1");
    }

    private int part1(List<String> lines) {
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

    private int part2(List<String> lines) {
        // Challenge: calculate number of lines that are more than the previous.
        // But use a sliding window of size 3.
        List<Integer> parsed = lines.stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
        if (lines.size() < 3) {
            // Data too small. Abort!
            return 0;
        }

        // Could replace with an array pop/push, but hardcoding because lazy for this size.
        Integer window0 = parsed.get(0);
        Integer window1 = parsed.get(1);
        Integer window2 = parsed.get(2);
        Integer prevWindow = window0 + window1 + window2;
        int increases = 0;
        for (int i =3; i < parsed.size(); i++) {
            Integer current = parsed.get(i);
            int currentWindow = window1 + window2 + current;
            if (prevWindow != null && prevWindow < currentWindow) {
                increases++;
            }
            prevWindow = currentWindow;
            window0 = window1;
            window1 = window2;
            window2 = current;
        }
        return increases;
    }

    private int calculateIncreases(String fileName) {
        List<String> lines = readInput(fileName);
        // return part1(lines);
        return part2(lines);
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
