package com.company;

/**
 * Created by julianareider on 4/10/17.
 */
public class Plane {
    private CargoItemList itemsToTake;
    private String typeOfPlane;
    private int maxOzWeight;

    public CargoItemList getItemsToTake() {
        return itemsToTake;
    }

    public void setItemsToTake(CargoItemList itt) {
        itemsToTake = itt;
    }

    public String getTypeOfPlane() {
        return typeOfPlane;
    }

    public void setTypeOfPlane(String top) {
        typeOfPlane = top;
    }

    public int getMaxOzWeight() {
        return maxOzWeight;
    }

    public void setMaxOzWeight(int mow) {
        maxOzWeight = mow;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "itemsToTake=" + itemsToTake +
                ", typeOfPlane='" + typeOfPlane + '\'' +
                ", maxOzWeight=" + maxOzWeight +
                '}';
    }
}
