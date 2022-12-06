package com.dulitharanatunga._2022;

import com.dulitharanatunga.Day;

import java.util.*;

public class Day6 extends Day {

    private int findMarker(int windowSize, String line) {
        LinkedList window = new LinkedList();
        HashMap<Integer, Integer> map = new HashMap<>();
        int i = 0;
        for (int c: line.toCharArray()) {
            if (i < 14) {
                window.add(c);
            } else {
                int out = (int) window.pop();
                int mv = map.get(out);
                if (--mv == 0) {
                    map.remove(out);
                } else {
                    map.put(out, mv);
                }
                window.add(c);
            }
            map.put(c, map.getOrDefault(c, 0) + 1);
            i++;
            if (map.size() == 14) {
                return i;
            }
        }
        return -1;
    }

    private int part1(String line) {
        return findMarker(4, line);
    }

    private int part2(String line) {
        return findMarker(14, line);
    }

    @Override
    protected Integer calculate(List<String> lines) {
        return part2(lines.get(0));
    }


}
