package com.company.driver;

import com.company.ammunition.Ammunition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Motorcyclist {

    private ArrayList<Ammunition> ammunitions;

    private double price;

    public Motorcyclist() {
        this.ammunitions = new ArrayList<>();
    }

    public Motorcyclist(ArrayList<Ammunition> ammunitions) {
        this.ammunitions = ammunitions;
        for (var ammunition : ammunitions) {
            price += ammunition.getPrice();
        }
    }

    public void addAmunition(Ammunition ammunitionToAdd) {
        this.ammunitions.add(ammunitionToAdd);
        price = 0;
        for (var ammunition : ammunitions) {
            price += ammunition.getPrice();
        }
    }

    public ArrayList<Ammunition> getAmmunitions() {
        return ammunitions;
    }

    public void setAmmunitions(ArrayList<Ammunition> ammunitions) {
        this.ammunitions = ammunitions;
    }

    public double getPrice() {
        price = 0;
        for (var ammunition : ammunitions) {
            price += ammunition.getPrice();
        }
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void sortAmmunition() {
        Collections.sort(ammunitions);
    }

    public ArrayList<Ammunition> findInRange(double firstPrice, double secondPrice) {
        var returnArray = new ArrayList<Ammunition>();
        for (var ammunition : ammunitions) {
            if (ammunition.getPrice() >= firstPrice && ammunition.getPrice() <= secondPrice) {
                returnArray.add(ammunition);
            }
        }
        return returnArray;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Motorcyclist)) return false;
        Motorcyclist that = (Motorcyclist) o;
        return Double.compare(that.price, price) == 0 &&
                getAmmunitions().equals(that.getAmmunitions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAmmunitions(), price);
    }

    @Override
    public String toString() {
        return "Motorcyclist: ammunition = " + ammunitions
                + ", price = " + price;
    }
}
