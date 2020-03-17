package com.company.ammunition;

import java.util.Objects;

public class Jacket extends Ammunition {

    private int leatherPercentage;

    public Jacket(String manufacturer, double price, double weight, int leatherPercentage) {
        super(manufacturer, price, weight);
        if (leatherPercentage < 0 || leatherPercentage > 100) {
            throw new IllegalArgumentException("Illegal argument in Jacket constructor!");
        }
        this.leatherPercentage = leatherPercentage;
    }

    public int getLeatherPercentage() {
        return leatherPercentage;
    }

    public void setLeatherPercentage(int leatherPercentage) {
        this.leatherPercentage = leatherPercentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Jacket)) return false;
        Jacket jacket = (Jacket) o;
        return getLeatherPercentage() == jacket.getLeatherPercentage();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLeatherPercentage());
    }

    @Override
    public String toString() {
        return "Jacket: manufacturer = " + this.getManufacturer()
                + ", price = " + this.getPrice()
                + ", weight = " + this.getWeight()
                + ", leather percentage = " + leatherPercentage;
    }
}
