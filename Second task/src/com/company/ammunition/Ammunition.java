package com.company.ammunition;


import java.util.Objects;

public abstract class Ammunition implements Comparable<Ammunition> {

    private String manufacturer;

    private double price;

    private double weight;

    public Ammunition(String manufacturer, double price, double weight) {
        if (manufacturer.isEmpty() || price < 0) {
            throw new IllegalArgumentException("Illegal argument in Ammunition constructor!");
        }
        this.manufacturer = manufacturer;
        this.price = price;
        this.weight = weight;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ammunition)) return false;
        Ammunition ammunition = (Ammunition) o;
        return Double.compare(ammunition.getPrice(), getPrice()) == 0 &&
                Objects.equals(getManufacturer(), ammunition.getManufacturer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getManufacturer(), getPrice());
    }

    @Override
    public String toString() {
        return "Ammunition: manufacturer = " + manufacturer + ", weight = " + price;
    }

    @Override
    public int compareTo(Ammunition ammunition) {
        if (this.weight < ammunition.weight) {
            return -1;
        } else {
            return 1;
        }
    }

}
