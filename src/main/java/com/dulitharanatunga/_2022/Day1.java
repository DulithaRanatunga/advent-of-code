package com.dulitharanatunga._2022;

import com.dulitharanatunga.Day;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day1 extends Day {

    int part1(List<String> lines) {
        int currentMax = 0;
        int currentValue = 0;

        for (String line: lines) {
            if (StringUtils.isBlank(line)) {
                currentMax = Math.max(currentValue, currentMax);
                currentValue = 0;
            } else {
                currentValue += Integer.parseInt(line);
            }
        }
        return currentMax;
    }

    int part2(List<String> lines) {
        int currentValue = 0;
        List<Integer> maxes = new ArrayList<>();
        for (String line: lines) {
            if (StringUtils.isBlank(line)) {
                maxes.add(currentValue);
                currentValue = 0;
            } else {
                currentValue += Integer.parseInt(line);
            }
        }
        return maxes
                .stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .collect(Collectors.summingInt(Integer::intValue));
    }

    @Override
    protected Integer calculate(List<String> lines) {
        return part2(lines);
    }

    @Override
    public String getDay() {
        return "day1";
    }
}
