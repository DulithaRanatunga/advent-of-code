package com.dulitharanatunga._2022;

import com.dulitharanatunga.Day;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day11 extends Day {


    class Monkey {
        int id;
        Queue<Integer> queue = new PriorityQueue();
        Function<Integer, Integer> operation;
        Function<Integer, Boolean> testFn;
        Monkey ifTrue;
        Monkey ifFalse;
        int inspected = 0;
    }

    private Integer part1(List<String> lines) {
        int monkeys = 0;
        Map<Integer, Monkey> allMonkeys = new HashMap();

        for (int i = 0; i < lines.size(); i+=7) {
            Monkey current = allMonkeys.getOrDefault(monkeys, new Monkey());
            current.id = monkeys;
            Arrays.stream(lines.get(i + 1).substring(18).split(", "))
                    .forEach(item -> current.queue.add(Integer.parseInt(item)));
            current.operation = parseOperation(lines.get(i+2));
            current.testFn = parseTestFn(lines.get(i + 3));
            int tMid = parseTarget(lines.get(i+4));
            current.ifTrue = allMonkeys.getOrDefault((tMid),new Monkey());
            allMonkeys.put(tMid, current.ifTrue);
            tMid = parseTarget(lines.get(i+5));
            current.ifFalse = allMonkeys.getOrDefault(tMid,new Monkey());
            allMonkeys.put(tMid, current.ifFalse);
            allMonkeys.put(monkeys, current);
            monkeys++;
        }

        for (int round = 0; round < 20; round++) {
            for (int i = 0; i < monkeys; i++) {
                Monkey m = allMonkeys.get(i);
                while(!m.queue.isEmpty()) {
                    m.inspected++;
                    int worry = m.operation.apply(m.queue.poll());
                    worry = worry / 3;
                    (m.testFn.apply(worry) ? m.ifTrue : m.ifFalse).queue.add(worry);
                }
            }
        }

        final List<Integer> collect = allMonkeys.values().stream().map(m -> m.inspected).sorted(Comparator.reverseOrder()).limit(2).collect(Collectors.toList());
        return collect.get(0) * collect.get(1);
    }

    private Function<Integer, Integer> parseOperation(String s) {
        String[] split = s.split(" ");
        String operation = split[split.length - 2];
        String operand = split[split.length - 1];
        Function<Integer, Integer> parsedOperand = (i -> operand.equals("old") ? i : Integer.parseInt(operand));
        switch (operation) {
            case "*": return (i -> i * parsedOperand.apply(i));
            case "+": return (i -> i + parsedOperand.apply(i));
            case "-": return (i -> i - parsedOperand.apply(i));
            case "/": return (i -> i / parsedOperand.apply(i));
            default:
                return i -> i;
        }
    }

    private Function<Integer, Boolean> parseTestFn(String s) {
        String[] divisibleBy = s.split(" ");
        return (i -> i % Integer.parseInt(divisibleBy[divisibleBy.length - 1]) == 0);
    }

    private int parseTarget(String s) {
        final String[] s1 = s.split(" ");
        return Integer.parseInt(s1[s1.length - 1]);
    }

    @Override
    protected Integer calculate(List<String> lines) {
        return part1(lines);
    }


}
