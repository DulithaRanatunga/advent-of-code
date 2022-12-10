package com.dulitharanatunga._2022;

import com.dulitharanatunga.Day;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day9 extends Day {

    private enum Dir {
        L, U, R, D
    }

    public class Grid {
        int starti = 0;
        int startj = 0;
        int headi = 0;
        int headj = 0;
        int taili = 0;
        int tailj = 0;
    }

    private Integer part1(List<String> lines) {
        HashMap<Integer, Set<Integer>> seen = new HashMap<>();
        Grid grid = new Grid();
        int lowestI = 0;
        int lowestJ = 0;
        int highestI = 0;
        int highestJ = 0;
        for (String line: lines) {
            final String[] s = line.split(" ");
            Dir dir = Dir.valueOf(s[0]);
            for (int i = 0; i < Integer.parseInt(s[1]); i++) {
                moveHead(dir, grid);
                moveTail(grid);
                final Set<Integer> seenSet = seen.getOrDefault(grid.taili, new HashSet<>());
                seenSet.add(grid.tailj);
                seen.put(grid.taili, seenSet);
                lowestI = Math.min(lowestI, grid.headi);
                lowestJ = Math.min(lowestJ, grid.headj);
                highestI = Math.max(highestI, grid.headi);
                highestJ = Math.max(highestJ, grid.headj);
            }

        }
        for (int i = lowestI; i <= highestI; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = lowestJ; j <= highestJ; j++) {
                if (i == 0 && j == 0) {
                    sb.append("s");
                } else {
                    sb.append(seen.getOrDefault(i, Set.of()).contains(j) ? "#" : ".");
                }
            }
            System.out.println(sb.toString());
        }
        return seen.values().stream().collect(Collectors.summingInt(Set::size));

    }

    private void moveTail(Grid grid) {
        int iMove = (grid.headi - grid.taili) / 2;
        int jMove = (grid.headj - grid.tailj) / 2;
        grid.taili += iMove;
        grid.tailj += jMove;

        if (Math.abs(iMove) == 1 && grid.headj != grid.tailj) {
            // Move diagonal
            grid.tailj += (grid.headj - grid.tailj);
        }

        if (Math.abs(jMove) == 1 && grid.headi != grid.taili) {
            // Move diagonal
            grid.taili += (grid.headi - grid.taili);
        }
    }

    private void moveHead(Dir dir, Grid grid) {
        switch (dir) {
            case L: grid.headj--; break;
            case R: grid.headj++; break;
            case U: grid.headi--; break;
            case D: grid.headi++; break;
        }
    }

    @Override
    protected Integer calculate(List<String> lines) {
        return part1(lines);
    }


}
