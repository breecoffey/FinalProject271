package com.company;

/**
 * Created by julianareider on 4/10/17.
 * Plane represents a cargo aircraft.
 */
public class Plane {
    /** itemsToTake represents a list of cargoItems to be transported on this aircraft */
    private CargoItemList itemsToTake;
    /** type of plane is the kind of aircraft */
    private String typeOfPlane;
    /** maxOzWeight is the maximum weight of Cargo that can be transported on this aircraft (in ounces) */
    private int maxOzWeight;

    //Constructors
    /**
     * Empty constructor that initialize all the attributes.
     */
    public Plane(){
        itemsToTake = new CargoItemList();
        typeOfPlane = "";
        maxOzWeight = 0;
    }

    /**
     * Constructor that initialize all the attributes and sets typeOfPlane and maxOzWeight to arguments.
     * @param top is the type of plane this plane is.
     * @param maxW is the maximum weight of cargo Items that can be transported on this aircraft,  in ounces.
     */
    public Plane(String top, int maxW){
        typeOfPlane = top;
        maxOzWeight = maxW;
        itemsToTake = new CargoItemList();
    }

    //setters and getters
    /**
     * Retrieves a list of cargoItems to be transported on this aircraft.
     * @return list of CargoItems to be transported on this aircraft was returned.
     */
    public CargoItemList getItemsToTake() {
        return itemsToTake;
    }

    /**
     * Sets the itemsToTake attribute to the itt list of CargoItems.
     * @param itt is the list of items to take.
     * @pre the list of item's total weight should be less than maxOzWeight
     * @post itt was stored in the itemsToTake attribute.
     */
    public void setItemsToTake(CargoItemList itt) {
        itemsToTake = itt;
    }

    /**
     * Retrieves the kind of aircraft
     * @return a string representing the kind of plane was returned.
     */
    public String getTypeOfPlane() {
        return typeOfPlane;
    }

    /**
     * Retrieves the the maximum weight of Cargo that can be transported on this aircraft (in ounces)
     * @return the maxOzWeight value was returned.
     */
    public int getMaxOzWeight() {
        return maxOzWeight;
    }

    //Other methods

    /**
     * Retrieves information about the attributes in this plane object.
     * @return a string representation of the attributes of the plane object
     */
    @Override
    public String toString() {
        //todo make output pretty
        return "Plane{" +
                "itemsToTake=" + itemsToTake +
                ", typeOfPlane='" + typeOfPlane + '\'' +
                ", maxOzWeight=" + maxOzWeight +
                '}';
    }
}
