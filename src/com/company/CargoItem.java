package com.company;

/**
 * Created by julianareider on 4/10/17.
 * CargoItem represents a cargo item.
 */
public class CargoItem {
    //Attributes
    /** The ozWeight is used to represent the weight of the cargo item in ounces  */
    private int ozWeight;
    /** name is the name of the cargo item */
    private String name;
    /** value is the price of an item in american dollars */
    private int value;

    //constructors
    /**
     * Constructor that initialize all the attributes of this CargoItem object.
     */
    public CargoItem(){
        ozWeight = 0;
        name = "Not set";
        value = 0;
    }

    /**
     * Constructor that initialize all the variables and set the attributes to given values.
     * @param ozWeight the weight of the cargo item in ounces.
     * @param name is the name of the cargo item.
     * @param value the price of an item in american dollars.
     */
    public CargoItem(int ozWeight, String name, int value) {
        this.ozWeight = ozWeight;
        this.name = name;
        this.value = value;
    }

    //Setters and getters
    /**
     * Retrieves the weight of the cargo item in oz.
     * @return the weight of the single item
     */
    public int getOzWeight() {
        return ozWeight;
    }

    /**
     * Sets the weight of the cargo item to new weight.
     * @param ozWeight the new weight of the cargo item.
     */
    public void setOzWeight(int ozWeight) {
        this.ozWeight = ozWeight;
    }

    /**
     * Retrieves the name of the cargo item.
     * @return string representing the name of the cargo item.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this cargo item to a new name.
     * @param name the new name of the cargo item.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the value of this cargo item.
     * @return the value of the cargo item in American dollars.
     */
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Tests if this cargo item is equivalant in weight and value to cargo item ci
     * @param ci cargo item to compare to this one
     * @return true if they are equal, false otherwise.
     */
    public boolean equals(CargoItem ci){
        //stub (untested code here)

        return this.getOzWeight() == ci.getOzWeight() && this.getName().equalsIgnoreCase(ci.getName()) && this.getValue() == ci.getValue();
    }

    /**
     * Retrieves information about the attributes in this cargo item.
     * @return a string representation of the attributes of the item.
     */
    @Override
    public String toString() {
        return "Item: " + name + ", Weight: " + ozWeight + ", Value: " + value + "\n";
    }
}
