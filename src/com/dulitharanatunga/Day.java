package com.dulitharanatunga;

import java.util.List;

public abstract class Day {
    public int run() { return calculate(Util.readInput(getDay()));};
    public int runSample() { return calculate(Util.readInput(getDay() + "_sample"));};

    protected abstract int calculate(List<String> lines);
    public abstract String getDay();
}
