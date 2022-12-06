package com.dulitharanatunga._2021;
import com.dulitharanatunga.Day;
import org.apache.commons.lang3.tuple.Pair;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Day2 extends Day {
    @Override
    protected Integer calculate(List<String> lines) {
        // return part1(lines);
        return part2(lines);
    }

    private int part2(List<String> lines) {
        Integer forward = 0;
        Integer depth = 0;
        Integer aim = 0;
        for (var pair: parse(lines)) {
            switch (pair.getLeft()) {
                case UP: aim -= pair.getRight(); break;
                case DOWN: aim += pair.getRight(); break;
                case FORWARD:
                    forward += pair.getRight();
                    depth += aim * pair.getRight();
                    break;
                default: throw new RuntimeException("WTF");
            }
        };
        return forward * depth;
    }

    private int part1(List<String> lines) {
        Integer forward = 0;
        Integer depth = 0;
        for (var pair: parse(lines)) {
            switch (pair.getLeft()) {
                case UP: depth -= pair.getRight(); break;
                case DOWN: depth += pair.getRight(); break;
                case FORWARD: forward += pair.getRight(); break;
                default: throw new RuntimeException("WTF");
            }
        };
        return forward * depth;
    }

    private List<Pair<Direction, Integer>> parse(List<String> lines) {
        return lines.stream()
                .map(line -> {
                    String[] parts = line.split(" ");
                    return Pair.of(Direction.valueOf(parts[0].toUpperCase(Locale.ROOT)), Integer.parseInt(parts[1]));
                })
                .collect(Collectors.toList());
    }

    @Override
    public String getDay() {
        return "day2";
    }

    private enum Direction {
        FORWARD, DOWN, UP;
    }
}
