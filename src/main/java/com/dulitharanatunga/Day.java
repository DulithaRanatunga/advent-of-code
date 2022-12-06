package com.dulitharanatunga;

import java.util.List;
import java.util.Locale;

public abstract class Day {
    public Object run() { return calculate(Util.readInput(getDay()));};
    public Object runSample() { return calculate(Util.readInput(getDay() + "_sample"));};

    protected abstract Object calculate(List<String> lines);
    public String getDay() {
        return this.getClass().getSimpleName().toLowerCase(Locale.ROOT);
    }
}
