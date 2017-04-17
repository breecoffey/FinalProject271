package com.company;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by julianareider on 4/17/17.
 */
public class TripTest {
    private CargoItem firstItem = new CargoItem(1, "firstItem", 1);
    private CargoItem secondItem = new CargoItem(3, "secondItem", 4);
    private CargoItem thirdItem = new CargoItem(4, "thirdItem", 5);
    private CargoItem fourthItem = new CargoItem(5, "fourthItem", 7);
    private CargoItemList itemList = new CargoItemList();
    private CargoListGenerator testGen = new CargoListGenerator();
    private Plane tempPlane;
    private Trip tempTrip;

    @Test
    public void loadAirplane() throws Exception {
        itemList.addCargoItemToList(firstItem);
        itemList.addCargoItemToList(secondItem);
        itemList.addCargoItemToList(thirdItem);
        itemList.addCargoItemToList(fourthItem);
        tempPlane = new Plane("This kind of plane", 7);
        tempTrip = new Trip (tempPlane, 10.0);
        System.out.println( tempTrip.loadAirplane(itemList));

        //todo this method should:
        // load the airplane with the correct items that will create the max val while staying underneath the correct weight limit.
        // the string should reflect the correct items brought, left behind, and the max value of items bringing
        // the string shoudl also say that the plane was loaded. (the airplane item list must be correct)

        //System.out.println(testGen.generateList(itemList, 7));


    }

}