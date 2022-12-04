package com.dulitharanatunga;

import java.util.List;
import java.util.Locale;

public abstract class Day {
    public int run() { return calculate(Util.readInput(getDay()));};
    public int runSample() { return calculate(Util.readInput(getDay() + "_sample"));};

    protected abstract int calculate(List<String> lines);
    public String getDay() {
        return this.getClass().getSimpleName().toLowerCase(Locale.ROOT);
    }
}
