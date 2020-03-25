package com.company;

import java.util.Objects;

public class Processor implements Hardwarable {
    private boolean working = false;

    private int frequency;

    public Processor(int frequency) {
        this.working = false;
        this.frequency = frequency;
    }

    public Processor(int frequency, boolean working) {
        this.working = working;
        this.frequency = frequency;
    }

    boolean isWorking() { return working; }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Processor)) return false;
        Processor processor = (Processor) o;
        return isWorking() == processor.isWorking();
    }

    @Override
    public int hashCode() {
        return Objects.hash(isWorking());
    }

    @Override
    public String toString() {
        if (working) {
            return "Processor is working";
        } else {
            return "Processor is not working";
        }
    }

    @Override
    public void turnOnOff() { working = !working; }
}
