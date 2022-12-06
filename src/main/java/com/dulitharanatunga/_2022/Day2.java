package com.dulitharanatunga._2022;

import com.dulitharanatunga.Day;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day2 extends Day {

    private int points(char a) {
        switch (a) {
            case 'A':
            case 'X': return 1;
            case 'B':
            case 'Y': return 2;
            case 'C':
            case 'Z': return 3;
            default: return 0;
        }
    }
    int score(char me, char opponent) {
        return WIN_TABLE[points(me) -1][points(opponent) -1];
    }

    // int i is Me
    // int j is Them
    // int [i][j] is score.
    private static int[][] WIN_TABLE = new int[][] {
            // Rock, Paper, Scissor
            new int[] {3,0,6},
            new int[] {6,3,0},
            new int[] {0,6,3}
    };

    private static char[][] GOAL_TABLE = new char[][] {
            // Rock, Paper, Scissor
            new char[] {'Z','X','Y'},
            new char[] {'X','Y','Z'},
            new char[] {'Y','Z','X'}
    };


    int part1(List<String> lines) {
        int score = 0;
        for (String line: lines) {
            if (!StringUtils.isBlank(line)) {
                char enemy = line.charAt(0);
                char me = line.charAt(2);
                score += points(me) + score(me, enemy);
            }
        }
        return score;
    }

    int part2(List<String> lines) {
        int score = 0;
        for (String line: lines) {
            if (!StringUtils.isBlank(line)) {
                char enemy = line.charAt(0);
                char me = GOAL_TABLE[points(line.charAt(2))-1][points(enemy) - 1];
                score += points(me) + score(me, enemy);
            }
        }
        return score;
    }

    @Override
    protected Integer calculate(List<String> lines) {
        return part2(lines);
    }

    @Override
    public String getDay() {
        return "day2";
    }
}
