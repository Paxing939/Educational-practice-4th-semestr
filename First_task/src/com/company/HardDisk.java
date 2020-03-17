package com.company;

import java.util.Objects;

public class HardDisk implements Hardwarable {
    private int capacityGB;

    private boolean working;

    private boolean viruses;

    public HardDisk(int capacity) {
        this.capacityGB = capacity;
        this.working = false;
        this.viruses = false;
    }

    public HardDisk(int capacity, boolean working) {
        this.capacityGB = capacity;
        this.working = working;
        this.viruses = true;
    }

    public boolean isWorking() {
        return working;
    }

    public int getCapacityGB() {
        return capacityGB;
    }

    public void setCapacityGB(int capacityGB) {
        this.capacityGB = capacityGB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HardDisk)) return false;
        HardDisk hardDisk = (HardDisk) o;
        return getCapacityGB() == hardDisk.getCapacityGB();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCapacityGB());
    }

    @Override
    public String toString() {
        return "Capacity of hard disk: " + capacityGB;
    }

    @Override
    public void turnOnOff() { working = !working; }

    boolean isThereViruses() {
        return viruses;
    }
}
