package com.dulitharanatunga;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day3 extends Day {
    @Override
    protected int calculate(List<String> lines) {
        return part1(lines);
    }

    private int part1(List<String> lines) {
        int[] gammaBinary = getGammaBinary(lines);
        int[] epsilonBinary = getEpsilonBinary(gammaBinary);
        System.out.println("gamma" + Arrays.toString(gammaBinary));
        System.out.println("epsilon" + Arrays.toString(epsilonBinary));
        return btoi(gammaBinary) * btoi(epsilonBinary);
    }

    private int btoi(int[] binaryArr) {
        int val = 0;
        int power = 0;
        for (int digit = binaryArr.length; digit > 0; digit--) {
            val += binaryArr[digit -1] * Math.pow(2,power);
            power++;
        }
        System.out.println(val);
        return val;
    }

    private int[] getEpsilonBinary(int[] gammaBinary) {
        int[] epsilonBinary = new int[gammaBinary.length];
        for (int i =0; i <gammaBinary.length; i++) {
            epsilonBinary[i] = gammaBinary[i] == 1 ? 0 : 1;
        }
        return epsilonBinary;
    }

    private int[] getGammaBinary(List<String> lines) {
        int columns = lines.get(0).length();
        int[] mostCommonArray = new int[columns];
        for (int col =0; col < columns; col++) {
            int ones = 0;
            int zeroes = 0;
            for (String row: lines) {
                if (row.charAt(col) == '0') { zeroes++; } else { ones++; };
            }
            mostCommonArray[col] = ones > zeroes ? 1 : 0;
        }
        return mostCommonArray;
    }

    @Override
    public String getDay() {
        return "day3";
    }
}
