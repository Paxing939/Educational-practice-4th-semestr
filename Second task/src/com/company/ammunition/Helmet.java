package com.company.ammunition;

import java.util.Objects;

public class Helmet extends Ammunition {

    private int radius;

    public Helmet(String manufacturer, double price, double weight,  int radius) {
        super(manufacturer, price, weight);
        if (radius < 0 ) {
            throw new IllegalArgumentException("Illegal argument in Helmet constructor!");
        }
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Helmet)) return false;
        Helmet helmet = (Helmet) o;
        return getRadius() == helmet.getRadius();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRadius());
    }

    @Override
    public String toString() {
        return "Helmet: manufacturer = " + this.getManufacturer()
                + ", price = " + this.getPrice()
                + ", weight = " + this.getWeight()
                + ", radius = " + radius;
    }
}
