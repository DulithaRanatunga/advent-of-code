package com.dulitharanatunga._2022;

import com.dulitharanatunga.Day;

import java.util.*;
import java.util.stream.Collectors;

public class Day10 extends Day {


    private Integer part1(List<String> lines) {
        int cycle = 0;
        int x = 1; // X is sprite position
        int sigStrengthCount = 0;
        int sigStr = 0;
        StringBuilder sb = new StringBuilder();
        for (String line: lines) {
            String[] ops = line.split(" ");
            if (ops[0].equals("addx")) {
                int dx = Integer.parseInt(ops[1]);
                for(int i = 0; i < 2; i++) {
                    sb.append(Math.abs((x % 40) - (cycle % 40)) <= 1 ? "#" : ".");
                    cycle++;
                    if (cycle % 40 == 0) {
//                        System.out.println(x + " <-- x. cycle:  --> " + cycle);
                        sigStr += x * cycle;
                        sigStrengthCount++;
//                        System.out.println(cycle + ":" + sigStr);
                        sb.append("\n");
                        if (cycle == 240) {
                            System.out.println(sb);
                            return sigStr;
                        }
                    }
                }
                x += dx;
            } else {
                sb.append(Math.abs((x % 40) - (cycle % 40)) <= 1 ? "#" : ".");
                cycle++;
                if (cycle % 40 == 0) {
//                    System.out.println(x + " <-- x. cycle:  --> " + cycle);
                    sigStr += x * cycle;
                    sigStrengthCount++;
//                    System.out.println(cycle + ":" + sigStr);
                    sb.append("\n");
                    if (cycle == 240) {
                        System.out.println(sb);
                        return sigStr;
                    }
                }
            }
        }
        return sigStr;
    }

    @Override
    protected Integer calculate(List<String> lines) {
        return part1(lines);
    }


}
