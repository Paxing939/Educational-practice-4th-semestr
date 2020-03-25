package com.company;

import java.util.Objects;

public class Drive implements Hardwarable {
    private boolean empty;

    private boolean working;

    public Drive(boolean empty) {
        this.empty = empty;
        this.working = false;
    }

    public Drive(boolean empty, boolean working) {
        this.empty = empty;
        this.working = working;
    }

    public boolean isWorking() {
        return working;
    }

    boolean IsEmpty() { return empty; }

    public void loadDisk(boolean empty) {
        this.empty = empty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Drive)) return false;
        Drive drive = (Drive) o;
        return empty == drive.empty;
    }

    @Override
    public int hashCode() {
        return Objects.hash(empty);
    }

    @Override
    public String toString() {
        if (empty) {
            return "Drive is not disk";
        } else {
            return "Drive is empty";
        }
    }

    @Override
    public void turnOnOff() { working = !working; }
}
