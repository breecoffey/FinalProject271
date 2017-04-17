package com.company;

/**
 * Created by julianareider on 4/10/17.
 */
public class Plane {
    private CargoItemList itemsToTake;
    private String typeOfPlane;
    private int maxOzWeight;

    public Plane(){
        itemsToTake = new CargoItemList();
        typeOfPlane = "";
        maxOzWeight = 0;
    }

    public Plane(String top, int maxW){
        typeOfPlane = top;
        maxOzWeight = maxW;
        itemsToTake = new CargoItemList();
    }

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
        //todo make output pretty
        return "Plane{" +
                "itemsToTake=" + itemsToTake +
                ", typeOfPlane='" + typeOfPlane + '\'' +
                ", maxOzWeight=" + maxOzWeight +
                '}';
    }
}
