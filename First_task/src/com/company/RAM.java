package com.company;

import java.util.Objects;

public class RAM implements Hardwarable {
    private int ram;

    private boolean working;

    public RAM(int ram) {
        this.ram = ram;
        this.working = false;
    }

    public RAM(int ram, boolean working) {
        this.ram = ram;
        this.working = working;
    }

    public boolean isWorking() {
        return working;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RAM)) return false;
        RAM ram1 = (RAM) o;
        return ram == ram1.ram;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ram);
    }

    @Override
    public String toString() {
        return "RAM = " + ram;
    }

    @Override
    public void turnOnOff() { working = !working; }
}
