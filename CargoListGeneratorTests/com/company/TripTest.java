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
    //private CargoListGenerator testGen = new CargoListGenerator();
    private Plane tempPlane;
    private Trip tempTrip;
    //TODO should i make a lot of objects up here or should we have a file that we import for the creation of the CargoItem objects?

    //todo: when no items are left behind or none are brought along, have an output that reflects that

    @Test
    public void loadAirplane() throws Exception {
        itemList.addCargoItemToList(firstItem);
        itemList.addCargoItemToList(secondItem);
        itemList.addCargoItemToList(thirdItem);
        itemList.addCargoItemToList(fourthItem);
        tempPlane = new Plane("This kind of plane", 7);
        tempTrip = new Trip (tempPlane);
        //check for proper return string
        // the string should reflect the correct items brought, left behind, and the max value of items bringing
        String expectedOutput = "Airplane was loaded. \n\n" +
                "Here are the details of your trip: \n\n" +
                "Type of aircraft: This kind of plane\n" +
                "Weight Limit: 7 ounces \n" +
                "Bring these items: \n" +
                "\tItem: thirdItem, Weight: 4, Value: 5\n" +
                "\tItem: secondItem, Weight: 3, Value: 4\n" +
                "Total value of these items is: 9\n" +
                "Leave behind these items: \n" +
                "\tItem: fourthItem, Weight: 5, Value: 7\n" +
                "\tItem: firstItem, Weight: 1, Value: 1\n";
        assertEquals(expectedOutput, tempTrip.loadAirplane(itemList));

        //check proper items were stored in the plane
        CargoItemList expectedCList = new CargoItemList();
        expectedCList.addCargoItemToList(thirdItem);
        expectedCList.addCargoItemToList(secondItem);
        assertEquals(expectedCList.toString(), tempTrip.getPlaneForTrip().getItemsToTake().toString());
    }

    @Test
    public void bringAllItems() throws Exception { //tests that all items are brought when appropriate
        // A)
        // test that proper optimal list stored when 1) only one item on potential list 2)All items on potential list should be included
        CargoItemList cList = new CargoItemList();
        cList.addCargoItemToList(firstItem);
        tempPlane = new Plane("This kind of plane", 2);
        tempTrip = new Trip (tempPlane);
        tempTrip.loadAirplane(cList);
        assertEquals(cList.toString(),tempTrip.getPlaneForTrip().getItemsToTake().toString());

        //B)
        //test that proper optimal list stored when 1) two identical items exist on the list 2)All items on potential list should be included
        CargoItemList cargoList = new CargoItemList();
        cargoList.addCargoItemToList(new CargoItem(2, "b", 2));
        cargoList.addCargoItemToList(new CargoItem(2, "a", 2));
        Plane aPlane = new Plane("This kind of plane", 4); //weight lim = 4
        Trip aTrip = new Trip (aPlane);
        aTrip.loadAirplane(cargoList);
        CargoItemList expectedCargoItemList = new CargoItemList(); // the items in the actual list are of the reverse to which they were added to the original potential items.
        expectedCargoItemList.addCargoItemToList(new CargoItem(2, "a", 2));
        expectedCargoItemList.addCargoItemToList(new CargoItem(2, "b", 2));
        assertEquals(expectedCargoItemList.toString(), aTrip.getPlaneForTrip().getItemsToTake().toString());

        //test that proper optimal list stored when 1) no items are in the potential list of items  2)"All" items on potential list should be included

        //test that proper optimal list stored when 1) Many items are on list (10) 2)All items on potential list should be included

        //test that proper optimal list stored when 1) only one item on potential list (100) 2)All items on potential list should be included

    }

    @Test
    public void allItemsLeftBehind() throws Exception {//tests that all items are excluded from optimal list when appropriate
        //test that proper optimal list stored when 1) only one item on potential list 2)All items on potential list should be left behind

        //two identical items on list

        //Many items (10)

        //Many items (100)

    }

    @Test
    public void twoOrMoreSolutions() throws Exception {//Tests to see if at least one proper solution is found when there are multiple possible solutions

        //one item on list of potential items

        //two identical items on list of potential items. both items are in the solution

        //two identical items on list of potential items. neighter item is in the solution

        //two identical items on list of potential items. only one item is in the solution

    }

    @Test
    public void onePossibleSolution() throws Exception {//Tests to see if proper solution is found when there is only one possible solution

        //one item on list of potential items

        //two identical items on list of potential items. both items are in the solution

        //two identical items on list of potential items. neighter item is in the solution

        //zero items are on list of potential items

        // many items are on list of potential items (10)

        // many items are on list of potential items (100)

    }

    @Test
    public void oneItemTaken() throws Exception {//Tests to see if the proper solution is found when there should be only one item taken along from list of potential items

        //one item on list of potential items

        //two identical items on list of potential items. neither item is in the solution

        //two identical items on list of potential items. only one item is in the solution

        // many items are on list of potential items (10)

        // many items are on list of potential items (100)
    }

    @Test
    public void moreThanOneItemTaken() throws Exception {//Tests to see if the proper solutioin is found when there should be more than one item taen along from list of potential

        //two identical items on list of potential items. both items are in the solution

        //two identical items on list of potential items. neither item is in the solution

        //two identical items on list of potential items. only one item is in the solution

        // many items are on list of potential items (10)

        // many items are on list of potential items (100)

    }

}