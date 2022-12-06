package com.dulitharanatunga._2022;

import com.dulitharanatunga.Day;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day4 extends Day {

    private class Section {

        private int start;
        private int end;

        public Section(String s) {
            String[] split = s.split("-");
            this.start = Integer.parseInt(split[0]);
            this.end = Integer.parseInt(split[1]);
        }
    }

    private boolean fullyContained(Section a, Section b) {
        return (a.start >= b.start && a.end <= b.end) || (b.start >= a.start && b.end <= a.end);
    }

    private boolean NoOverlap(Section a, Section b) {
        return (a.start > b.end) || (b.start > a.end);
    }

    int part2(List<String> lines) {
        return (int) lines.stream()
                .map(line -> line.split(","))
                .filter(pairs -> !NoOverlap(new Section(pairs[0]), new Section(pairs[1])))
                .count();
    }

    int part1(List<String> lines) {
        return (int) lines.stream()
                .map(line -> line.split(","))
                .filter(pairs -> fullyContained(new Section(pairs[0]), new Section(pairs[1])))
                .count();
    }

    @Override
    protected int calculate(List<String> lines) {
        return part2(lines);
    }


}
