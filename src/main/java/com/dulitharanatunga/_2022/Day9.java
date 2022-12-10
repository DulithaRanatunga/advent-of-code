package com.dulitharanatunga._2022;

import com.dulitharanatunga.Day;

import java.util.*;
import java.util.stream.Collectors;

public class Day9 extends Day {

    private enum Dir {
        L, U, R, D
    }

    public class Grid {
        List<Knot> knots = new ArrayList<>();
        Knot head;
        Knot tail;

        public Grid() {
            head = new Knot("H");
            knots.add(head);
            for (int i = 1; i <= 8; i++) {
                knots.add(new Knot(i + ""));
            }
            tail = new Knot("T");
            knots.add(tail);
        }
    }

    public class Knot {
        String label;
        int i = 0;
        int j = 0;

        public Knot(String l) {
            label = l;
        }
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
                for (int t = 1; t < grid.knots.size(); t++) {
                    moveTail(grid.knots.get(t - 1), grid.knots.get(t));
                }
                final Set<Integer> seenSet = seen.getOrDefault(grid.tail.i, new HashSet<>());
                seenSet.add(grid.tail.j);
                seen.put(grid.tail.i, seenSet);
                lowestI = Math.min(lowestI, grid.head.i);
                lowestJ = Math.min(lowestJ, grid.head.j);
                highestI = Math.max(highestI, grid.head.i);
                highestJ = Math.max(highestJ, grid.head.j);



            }
            for (int printI = lowestI; printI <= highestI; printI++) {
                StringBuilder sb = new StringBuilder();
                for (int printJ = lowestJ; printJ <= highestJ; printJ++) {
                    if (printI == 0 && printJ == 0) {
                        sb.append("s");
                    } else {
                        int finalI = printI;
                        int finalJ = printJ;
                        sb.append(grid.knots.stream().filter(k -> k.i == finalI && k.j == finalJ).findFirst().map(l -> l.label).orElse("."));
//                    sb.append(seen.getOrDefault(i, Set.of()).contains(j) ? "#" : ".");
                    }
                }
                System.out.println(sb);
            }

        }

        return seen.values().stream().collect(Collectors.summingInt(Set::size));

    }

    private void moveTail(Knot head, Knot tail) {
        int iMove = (head.i - tail.i) / 2;
        int jMove = (head.j - tail.j) / 2;
        tail.i += iMove;
        tail.j += jMove;

        if (Math.abs(iMove) == 1 && Math.abs(jMove) == 1) {
            // Skip
        } else {
            if (Math.abs(iMove) == 1 && head.j != tail.j) {
                // Move diagonal
                tail.j += (head.j - tail.j);
            }

            if (Math.abs(jMove) == 1 && head.i != tail.i) {
                // Move diagonal
                tail.i += (head.i - tail.i);
            }
        }
    }
    private void moveHead(Dir dir, Grid grid) {
        switch (dir) {
            case L: grid.head.j--; break;
            case R: grid.head.j++; break;
            case U: grid.head.i--; break;
            case D: grid.head.i++; break;
        }
    }

    @Override
    protected Integer calculate(List<String> lines) {
        return part1(lines);
    }


}
