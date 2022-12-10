package com.dulitharanatunga._2022;

import com.dulitharanatunga.Day;

import java.util.*;
import java.util.stream.Collectors;

public class Day7 extends Day {

    public static Set<Node> smallDirectories = new HashSet<>();
    public static Set<Node> allDirectories = new HashSet<>();

    public class Node {
        Node parent;
        String label;
        int size = 0;
        boolean isLeaf = false;

        public Node(String label, Node parent) {
            this.label = label;
            this.parent = parent;
            if (!smallDirectories.add(this)) {
                System.out.println("Duplicate: " + parent.label + " / " + this.label);
            };
            allDirectories.add(this);
        }

        public Node(String label, Node parent, Integer size) {
            this(label, parent);
            this.size = size;
            this.isLeaf = true;
        }

        HashMap<String, Node> children = new HashMap<>();

        public void updateParent(Integer size) {
            this.size += size;
            if (this.parent != null) {
                this.parent.updateParent(size);
            }
            if (this.size > 100000) {
                smallDirectories.remove(this);
            }
        }

        public void addChild(Node n) {
            if (!this.children.containsKey(n.label)) {
                // Skip already seen ones.
                this.updateParent(n.size);
                this.children.put(n.label, n);
            }
        }

        public Node getDir(String l) {
            return this.children.get(l);
        }

    }


    private Node buildTree(List<String> lines) {
        Node root = new Node("/", null);
        Node currentDir = root;
        for(int i =0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.startsWith("$")) {
                // process Command I
                String[] cmdArgs = line.split(" ");
                switch(cmdArgs[1]) {
                    case "cd":
                        String dir = cmdArgs[2].trim();
                        switch(dir) {
                            case "..": currentDir = currentDir.parent; break;
                            case "/": currentDir = root; break;
                            default:
                                currentDir = currentDir.getDir(dir);
                        }
                        break;
                    case "ls":
                        continue;
                }
            } else {
                // Inside an LS
                String[] out = line.split(" ");
                if (out[0].equals("dir")) {
                    currentDir.addChild(new Node(out[1], currentDir));
                } else {
                    currentDir.addChild(new Node(out[1], currentDir, Integer.parseInt(out[0])));
                }
            }
        }
        return root;
    }

    private int part1(List<String> lines) {
        final Node node = buildTree(lines);
        smallDirectories = smallDirectories.stream().filter(n -> !n.isLeaf).collect(Collectors.toSet());
        final List<String> collect = allDirectories.stream().filter(n -> !n.isLeaf).map(l -> l.label).sorted().collect(Collectors.toList());
        return smallDirectories.stream().map(n -> n.size).collect(Collectors.summingInt(Integer::intValue));
    }

    private int part2(List<String> lines) {
        final Node root = buildTree(lines);
        int totalDirSize = root.size;
        int unused = 70000000 - totalDirSize;
        int target = 30000000 - unused;
        return allDirectories.stream()
                .filter(n -> !n.isLeaf)
                .map(n -> n.size)
                .sorted().filter(l -> l >=  target).findFirst().get();
    }

    @Override
    protected Integer calculate(List<String> lines) {
        smallDirectories = new HashSet<>();
        allDirectories = new HashSet<>();
        return part2(lines);
    }


}
