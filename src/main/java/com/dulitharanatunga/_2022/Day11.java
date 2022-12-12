package com.dulitharanatunga._2022;

import com.dulitharanatunga.Day;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day11 extends Day {

    class Monkey {
        int id;
        LinkedList<BigInteger> queue = new LinkedList<>();
        Function<BigInteger, BigInteger> operation;
        int mod;
        Monkey ifTrue;
        Monkey ifFalse;
        int inspected = 0;
    }
    private BigInteger part1(List<String> lines) {
        int monkeys = 0;
        Map<Integer, Monkey> allMonkeys = new HashMap();

        for (int i = 0; i < lines.size(); i+=7) {
            Monkey current = allMonkeys.getOrDefault(monkeys, new Monkey());
            current.id = monkeys;
            Arrays.stream(lines.get(i + 1).substring(18).split(", "))
                    .forEach(item -> current.queue.add(new BigInteger(item)));
            current.operation = parseOperation(lines.get(i+2));
            current.mod = parseTestFn(lines.get(i + 3));
            int tMid = parseTarget(lines.get(i+4));
            current.ifTrue = allMonkeys.getOrDefault((tMid),new Monkey());
            allMonkeys.put(tMid, current.ifTrue);
            tMid = parseTarget(lines.get(i+5));
            current.ifFalse = allMonkeys.getOrDefault(tMid,new Monkey());
            allMonkeys.put(tMid, current.ifFalse);
            allMonkeys.put(monkeys, current);
            monkeys++;
        }

        int superModulo = allMonkeys.values().stream().map(x -> x.mod).reduce(1, (a, b) -> a * b);

        for (int round = 0; round < 10000; round++) {
            for (int i = 0; i < monkeys; i++) {
                Monkey m = allMonkeys.get(i);
                while(!m.queue.isEmpty()) {
                    m.inspected++;
                    // Use BigInteger instead of long for the worry variable
                    BigInteger worry = m.operation.apply(m.queue.poll());
                    worry = worry.mod(BigInteger.valueOf(superModulo));
                    // Use BigInteger.mod() method instead of % operator
                    (worry.mod(BigInteger.valueOf(m.mod)).equals(BigInteger.ZERO) ? m.ifTrue : m.ifFalse).queue.add(worry);
                }
            }
        }

        final List<Integer> collect = allMonkeys.values().stream().map(m -> m.inspected).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println(collect);
        BigInteger rval = BigInteger.valueOf(collect.get(0)).multiply(BigInteger.valueOf(collect.get(1)));
        return rval;
    }
    private Function<BigInteger, BigInteger> parseOperation(String s) {
        String[] split = s.split(" ");
        String operation = split[split.length - 2];
        String operand = split[split.length - 1];
        // Use BigInteger and parseBigInteger() instead of long and parseLong()
        Function<BigInteger, BigInteger> parsedOperand = (i -> operand.equals("old") ? i : new BigInteger(operand));
        switch (operation) {
            case "*": return (i -> i.multiply(parsedOperand.apply(i)));
            case "+": return (i -> i.add(parsedOperand.apply(i)));
            default:
                throw new RuntimeException("HUH");
        }
    }

    private int parseTestFn(String s) {
        String[] divisibleBy = s.split(" ");
        return Integer.parseInt(divisibleBy[divisibleBy.length - 1]);
    }

    private int parseTarget(String s) {
        final String[] s1 = s.split(" ");
        return Integer.parseInt(s1[s1.length - 1]);
    }

    @Override
    protected BigInteger calculate(List<String> lines) {
        return part1(lines);
    }


}
