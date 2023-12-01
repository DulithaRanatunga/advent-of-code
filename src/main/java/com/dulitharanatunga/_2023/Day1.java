package com.dulitharanatunga._2023;

import com.dulitharanatunga.Day;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day1 extends Day {

    int part1(List<String> lines) {
        int total = 0;
        for (var line: lines) {
            var i = 0;
            var j = line.length() - 1;
            Integer start = 0;
            Integer end = 0;
            while (i < line.length()) {
                try {
                   start = Integer.parseInt(String.valueOf(line.charAt(i)));
                   break;
                } catch (NumberFormatException e) {
                    // not a number.
                }
                i++;
            }
            while (j >= 0) {
                try {
                    end = Integer.parseInt(String.valueOf(line.charAt(j)));
                    break;
                } catch (NumberFormatException e) {
                    // not a number.
                }
                j--;
            }
            var values = Integer.parseInt(start + "" + end);
            System.out.println(values);
            total += values;
        }
        return total;
    }

    int part2(List<String> lines) {
        int total = 0;
        for (var line: lines) {
            var i = 0;
            var j = line.length() - 1;
            Integer start = 0;
            Integer end = 0;
            while (i < line.length()) {
                try {
                    start = Integer.parseInt(String.valueOf(line.charAt(i)));
                    break;
                } catch (NumberFormatException e) {
                    try {
                        start = isWordS(line.substring(i));
                        break;
                    } catch (RuntimeException f) {
                        // Not a number
                    }
                }
                i++;
            }
            while (j >= 0) {
                try {
                    end = Integer.parseInt(String.valueOf(line.charAt(j)));
                    break;
                } catch (NumberFormatException e) {
                    try {
                        end = isWordE(line.substring(0, j+1));
                        break;
                    } catch (RuntimeException f) {
                        // Not a number
                    }
                }
                j--;
            }
            var values = Integer.parseInt(start + "" + end);
            System.out.println(values);
            total += values;
        }
        return total;
    }

    private Integer isWordS(String s) {
        if (s.startsWith("one")) {
            return 1;
        }
        if (s.startsWith("two")) {
            return 2;
        }
        if (s.startsWith("three")) {
            return 3;
        }
        if (s.startsWith("four")) {
            return 4;
        }
        if (s.startsWith("five")) {
            return 5;
        }
        if (s.startsWith("six")) {
            return 6;
        }
        if (s.startsWith("seven")) {
            return 7;
        }
        if (s.startsWith("eight")) {
            return 8;
        }
        if (s.startsWith("nine")) {
            return 9;
        }
        throw new RuntimeException("not a number");
    }

    private Integer isWordE(String s) {
        if (s.endsWith("one")) {
            return 1;
        }
        if (s.endsWith("two")) {
            return 2;
        }
        if (s.endsWith("three")) {
            return 3;
        }
        if (s.endsWith("four")) {
            return 4;
        }
        if (s.endsWith("five")) {
            return 5;
        }
        if (s.endsWith("six")) {
            return 6;
        }
        if (s.endsWith("seven")) {
            return 7;
        }
        if (s.endsWith("eight")) {
            return 8;
        }
        if (s.endsWith("nine")) {
            return 9;
        }
        throw new RuntimeException("not a number");
    }

    @Override
    protected int calculate(List<String> lines) {
        return part2(lines);
    }

    @Override
    public String getDay() {
        return "day1";
    }
}
