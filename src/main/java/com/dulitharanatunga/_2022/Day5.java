package com.dulitharanatunga._2022;

import com.dulitharanatunga.Day;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class Day5 extends Day {



    String part1(List<String> lines) {
        List<String> stacks = new ArrayList<>();
        List<String> instructions = new ArrayList<>();
        String stackNumbers = "";
        int i = 0;
        while (i < lines.size()) {
            String line = lines.get(i);
            if (line.isBlank()) {
                stacks = lines.subList(0, i - 1);
                stackNumbers = lines.get(i - 1);
                instructions = lines.subList(i+1, lines.size());
            }
            i++;
        }
        final String[] snarr = stackNumbers.split("\\s+");
        int stackSize = Integer.parseInt(snarr[snarr.length - 1]);
        Map<Integer, Stack> stackMap = processStacks(stackSize, stacks);
        processInstructions(stackMap, instructions);
        return getTopOfStack(stackSize, stackMap);
    }

    private String getTopOfStack(int stacksize, Map<Integer, Stack> stackMap) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= stacksize; i++) {
            sb.append(stackMap.get(i).pop());
        }
        return sb.toString();
    }

    private void processInstructions(Map<Integer, Stack> stackMap, List<String> moves) {
        moves.stream().forEach(l -> {
            String[] tokens = l.split(" ");
            int move = Integer.parseInt(tokens[1]);
            int from = Integer.parseInt(tokens[3]);
            int to = Integer.parseInt(tokens[5]);
            for (int i =0; i < move; i++) {
                stackMap.get(to).push(stackMap.get(from).pop());
            }
        });
    }

    private void processInstructions2(Map<Integer, Stack> stackMap, List<String> moves) {
        moves.stream().forEach(l -> {
            String[] tokens = l.split(" ");
            int move = Integer.parseInt(tokens[1]);
            int from = Integer.parseInt(tokens[3]);
            int to = Integer.parseInt(tokens[5]);
            List<Object> pickedUp = new ArrayList<>();
            // Pick them up
            for (int i =0; i < move; i++) {
                pickedUp.add(stackMap.get(from).pop());
            }
            // Put back in other order
            for (int i = move - 1; i >= 0; i--) {
                stackMap.get(to).push(pickedUp.get(i));
            }
        });
    }


    private HashMap<Integer, Stack> processStacks(int stackSize, List<String> stackStrings) {
        final HashMap<Integer, Stack> integerStackHashMap = new HashMap<>();
        for (int i = 1; i <= stackSize; i++) {
            integerStackHashMap.put(i, new Stack());
        }
        for (int row = stackStrings.size() - 1; row >= 0; row--) {
            for (int i = 1; i <= stackSize; i++) {
                int idx = 4*i - 3;
                final String r = stackStrings.get(row);
                if (idx < r.length()) {
                    if (r.charAt(idx) != ' ') {
                        integerStackHashMap.get(i).push(r.charAt(idx));
                    }
                }
            }
        }
        return integerStackHashMap;
    }

    String part2(List<String> lines) {
        List<String> stacks = new ArrayList<>();
        List<String> instructions = new ArrayList<>();
        String stackNumbers = "";
        int i = 0;
        while (i < lines.size()) {
            String line = lines.get(i);
            if (line.isBlank()) {
                stacks = lines.subList(0, i - 1);
                stackNumbers = lines.get(i - 1);
                instructions = lines.subList(i+1, lines.size());
            }
            i++;
        }
        final String[] snarr = stackNumbers.split("\\s+");
        int stackSize = Integer.parseInt(snarr[snarr.length - 1]);
        Map<Integer, Stack> stackMap = processStacks(stackSize, stacks);
        processInstructions2(stackMap, instructions);
        return getTopOfStack(stackSize, stackMap);
    }


    @Override
    protected String calculate(List<String> lines) {
        return part2(lines);
    }


}
