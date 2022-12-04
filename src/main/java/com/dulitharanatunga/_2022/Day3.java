package com.dulitharanatunga._2022;

import com.dulitharanatunga.Day;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class Day3 extends Day {

    int getPriority(char c) {

        return c > 90 ? c - 96 : c - 38;
    }
    int part1(List<String> lines) {
        int sumP = 0;
        for (String rucksack: lines) {

            List<Integer> pList = getPList(rucksack);
            List<Integer> left = pList.subList(0, rucksack.length()/2).stream().sorted().collect(Collectors.toList());
            List<Integer> right = pList.subList(rucksack.length()/2, rucksack.length()).stream().sorted().collect(Collectors.toList());
            int l = 0;
            int r = 0;
            while(l < left.size() && r < right.size()) {
                int lv = left.get(l);
                int rv = right.get(r);
                if (lv == rv) {
                    sumP += lv;
                    break;
                }
                if (lv < rv) {
                    l++;
                } else {
                    r++;
                }
            }
        }
        return sumP;
    }

    List<Integer> getPList(String rucksack) {
        return rucksack.chars().mapToObj(i -> (char) i).map(x -> getPriority(x)).collect(Collectors.toList());
    }

    Set<Integer> asUniqueSet(String rucksack) {
        return getPList(rucksack).stream().sorted().distinct().collect(Collectors.toSet());
    }

    int part2(List<String> lines) {
        int score = 0;
        for (int group = 0; group < lines.size() / 3; group ++) {
            Set<Integer> rucksack1 = asUniqueSet(lines.get(group*3));
            Set<Integer> rucksack2 = asUniqueSet(lines.get(group*3 + 1));
            Set<Integer> rucksack3 = asUniqueSet(lines.get(group*3 + 2));
            for(Integer r : rucksack1) {
                if (rucksack2.contains(r) && rucksack3.contains(r)) {
                    score+=r;
                    break;
                }
            }
        }
        return score;
    }

    @Override
    protected int calculate(List<String> lines) {
        return part2(lines);
    }


}
