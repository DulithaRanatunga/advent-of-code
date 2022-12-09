package com.dulitharanatunga._2022;

import com.dulitharanatunga.Day;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day8 extends Day {



    private int part1(List<String> lines) {
        int rows = lines.size();
        int cols = lines.get(0).length();
        int[][] grid = new int[rows][cols];
        for (int i =0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = Integer.parseInt(lines.get(i).charAt(j) + "");
            }
        }

        int highest = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int score = checkLeft(i, j, grid) *
                           checkRight(i,j, grid) *
                           checkTop(i,j, grid) *
                           checkDown(i,j, grid);
                highest = Math.max(score, highest);
            }
        }
        return highest;
    }

    // True if not visible
    // False = visible
    private int checkLeft(int i, int j, int[][] grid) {
        int me = grid[i][j];
        int score = 0;
        for (int x = j - 1; x >= 0; x--) {
            score++;
            if (grid[i][x] >= me) {
                return score;
            }
        }
        return score;
    }

    private int checkRight(int i, int j, int[][] grid) {
        int me = grid[i][j];
        int score = 0;
        for (int x = j + 1; x < grid[i].length; x++) {
            score++;
            if (grid[i][x] >= me) {
                return score;
            }
        }
        return score;
    }

    private int checkTop(int i, int j, int[][] grid) {
        int me = grid[i][j];
        int score = 0;
        for (int x = i - 1; x >= 0; x--) {
            score++;
            if (grid[x][j] >= me) {
                return score;
            }
        }
        return score;
    }

    private int checkDown(int i, int j, int[][] grid) {
        int me = grid[i][j];
        int score = 0;
        for (int x = i + 1; x < grid[i].length; x++) {
            score++;
            if (grid[x][j] >= me) {
                return score;
            }
        }
        return score;
    }

    @Override
    protected Integer calculate(List<String> lines) {
        return part1(lines);
    }


}
