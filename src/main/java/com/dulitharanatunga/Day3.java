package com.dulitharanatunga;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day3 extends Day {
    @Override
    protected int calculate(List<String> lines) {
        return part2(lines);
    }

    private int part1(List<String> lines) {
        int[] gammaBinary = getGammaBinary(lines);
        int[] epsilonBinary = getEpsilonBinary(gammaBinary);
        System.out.println("gamma" + Arrays.toString(gammaBinary));
        System.out.println("epsilon" + Arrays.toString(epsilonBinary));
        return btoi(gammaBinary) * btoi(epsilonBinary);
    }

    private int part2(List<String> lines) {
        return oxygenRating(lines) * co2(lines);
    }

    private int oxygenRating(List<String> lines) {
        List<String> linesToConsider = lines;
        int col = 0;
        while(linesToConsider.size() > 1 && col < lines.get(0).length()) {
            int commonBit = mostCommonBit(linesToConsider, col, true);
            final int compare = col;
            linesToConsider = linesToConsider
                    .stream()
                    .filter(l -> ctob(l.charAt(compare)) == commonBit)
                    .collect(Collectors.toList());
            col++;
        }
        return stob(linesToConsider.get(0));
    }

    private int ctob(char x) {
        return x == '1' ? 1:0;
    }

    private int stob(String x) {
        int[] arr = new int[x.length()];
        for (int i =0;i <arr.length;i++) {
            arr[i] = ctob(x.charAt(i));
        }
        System.out.println("stob:x: " + x + " arr:" + Arrays.toString(arr));
        return btoi(arr);
    }

    private int co2(List<String> lines) {
        List<String> linesToConsider = lines;
        int col = 0;
        while(linesToConsider.size() > 1 && col < lines.get(0).length()) {
            int leastCommonBit = mostCommonBit(linesToConsider, col, true) == 1 ? 0 : 1;
            final int compare = col;
            linesToConsider = linesToConsider
                    .stream()
                    .filter(l -> ctob(l.charAt(compare)) == leastCommonBit)
                    .collect(Collectors.toList());
            col++;
        }
        return stob(linesToConsider.get(0));
    }

    private int btoi(int[] binaryArr) {
        int val = 0;
        int power = 0;
        for (int digit = binaryArr.length; digit > 0; digit--) {
            val += binaryArr[digit -1] * Math.pow(2,power);
            power++;
        }
//        System.out.println(val);
        return val;
    }

    private int[] getEpsilonBinary(int[] gammaBinary) {
        int[] epsilonBinary = new int[gammaBinary.length];
        for (int i =0; i <gammaBinary.length; i++) {
            epsilonBinary[i] = gammaBinary[i] == 1 ? 0 : 1;
        }
        return epsilonBinary;
    }

    private int mostCommonBit(List<String> lines, int col, boolean favourOnes) {
        int ones = 0;
        int zeroes = 0;
        for (String row: lines) {
            if (row.charAt(col) == '0') { zeroes++; } else { ones++; };
        }
        if (ones == zeroes) {
            return favourOnes ? 1: 0;
        }
        return ones > zeroes ? 1 : 0;
    }


    private int[] getGammaBinary(List<String> lines) {
        int columns = lines.get(0).length();
        int[] mostCommonArray = new int[columns];
        for (int col =0; col < columns; col++) {
            mostCommonArray[col] = mostCommonBit(lines, col, true);
        }
        return mostCommonArray;
    }

    @Override
    public String getDay() {
        return "day3";
    }
}
