package com.dulitharanatunga._2022;

import com.dulitharanatunga.Day;

import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day12 extends Day {

    public int part1(List<String> lines) {
        final Grid grid = new Grid(lines);
        return grid.printGrid().shortestPath(grid.start);
    }

    public int part2(List<String> lines) {
        final Grid grid = new Grid(lines);
        int shortest = Integer.MAX_VALUE;
        for (int i = 0; i <grid.grid.length; i++) {
            for (int j = 0; j < grid.grid[0].length; j++) {
                if (grid.grid[i][j] == 1) {
                    Point candidate = new Point(i,j);
                    candidate.setOnGrid(grid.grid);
                    final int i1 = grid.shortestPath(candidate);
                    shortest = Math.min(shortest, i1);
                }
            }
        }
        return shortest;
    }

    private class Point {
        int id;
        int i;
        int j;
        int val;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public void setOnGrid(int[][] grid) {
          this.id = grid[0].length * i + j;
          this.val = grid[i][j];
        }

        public boolean isValidDirection(Point current) {
            return (this.val - current.val) <= 1;
        }
    }

    public class Grid {
        int[][] grid;
        Point start;
        Point goal;

        public Grid(List<String> lines) {
            this.grid = this.parseGrid(lines);
        }

        private int shortestPath(Point startingPoint) {
            LinkedList<Point> queueNow = new LinkedList<>();
            LinkedList<Point> queueNext = new LinkedList<>();
            HashSet<Integer> visited = new HashSet<>();
            Point current;
            queueNext.add(startingPoint);
            int depth = 0;
            while(!queueNext.isEmpty()) {
                queueNow = queueNext;
                queueNext = new LinkedList<>();
                HashSet<Integer> queued = new HashSet<>();
                while (!queueNow.isEmpty()) {
                    current = queueNow.poll();
                    if (visited.contains(current.id)) {
                        // Skip
                        continue;
                    }
                    visited.add(current.id);
                    if (current.id == goal.id) {
                        return depth;
                    } else {
                        addNeighbours(queueNext, visited, queued, current);
                    }
                }
                depth++;
            }
            // No path found.
            return Integer.MAX_VALUE;
        }

        private void addNeighbours(LinkedList<Point> queue, HashSet<Integer> visited, HashSet<Integer> queued, Point current){
            getAllDirections(current)
                    .stream()
                    .filter(p -> p.isValidDirection(current))
                    .filter(p -> !visited.contains(p.id))
                    .filter(p -> !queued.contains(p.id))
                    .forEach(p -> queue.add(p));
        }

        private List<Point> getAllDirections(Point current) {
            List<Point> l = new ArrayList<>();
            if (current.i > 0) { l.add(new Point(current.i - 1, current.j)); }
            if (current.j > 0) { l.add(new Point(current.i, current.j - 1)); }
            if (current.j < grid[0].length - 1) { l.add(new Point(current.i, current.j + 1)); }
            if (current.i < grid.length - 1) { l.add(new Point(current.i + 1, current.j)); }
            l.forEach(p -> p.setOnGrid(grid));
            return l;
        }

        public int[][] parseGrid(List<String> lines) {
            int rows = lines.size();
            int cols = lines.get(0).length();
            int[][] grid = new int[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    final char c = lines.get(i).charAt(j);
                    int iVal = c - 96;
                    if (c == 'S') {
                        iVal = 1;
                        this.start = new Point(i, j);
                    }
                    if (c == 'E') {
                        iVal = 26;
                        this.goal = new Point(i,j);
                    }
                    grid[i][j] = iVal;
                }
            }
            this.start.setOnGrid(grid);
            this.goal.setOnGrid(grid);
            return grid;
        }

        public Grid printGrid() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i <grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    sb.append((grid[i][j] < 10 ? " " + grid[i][j]: grid[i][j]) + " ");
                }
                sb.append("\n");
            }
            System.out.printf("Start: %d,%d, Goal: %d, %d \n", start.i,start.j, goal.i, goal.j);
            System.out.print(sb);
            return this;
        }
    }

    @Override
    protected Integer calculate(List<String> lines) {
        return part2(lines);
    }




}
