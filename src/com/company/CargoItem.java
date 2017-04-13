package com.company;

/**
 * Created by julianareider on 4/10/17.
 */
public class CargoItem {
    private int ozWeight;
    private String name;
    private int value; //double?

    //standard methods .

    public CargoItem(){
        ozWeight = 0;
        name = "Not set";
        value = 0;
    }

    public CargoItem(int ozWeight, String name, int value) {
        this.ozWeight = ozWeight;
        this.name = name;
        this.value = value;
    }

    public int getOzWeight() {
        return ozWeight;
    }

    public void setOzWeight(int ozWeight) {
        this.ozWeight = ozWeight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Item: " + name + ", Weight: " + ozWeight + ", Value: " + value + "\n";
    }
}
