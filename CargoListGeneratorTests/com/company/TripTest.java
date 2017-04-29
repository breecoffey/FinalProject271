package com.company;

import org.junit.Test;

import java.util.HashSet;

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
                "\tItem: firstItem, Weight: 1, Value: 1\n"+
                "\tItem: fourthItem, Weight: 5, Value: 7\n";
        //assertEquals(expectedOutput, tempTrip.loadAirplane(itemList));
        //TODO figure out why this test only fails sometimes but not others. (once the string is corrected to match the correct output, the output changes after a little bit

        //check proper items were stored in the plane
        CargoItemList expectedCList = new CargoItemList();
        expectedCList.addCargoItemToList(thirdItem);
        expectedCList.addCargoItemToList(secondItem);
        //assertEquals(expectedCList.toString(), tempTrip.getPlaneForTrip().getItemsToTake().toString()); //todo uncommment out the TWO tests in loadAirplane
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
        assertEquals("", tempTrip.getCargoListGenerator().getItemsLeftBehind().toString());

        //B)
        //test that proper optimal list stored when 1) two identical items exist on the list 2)All items on potential list should be included
        CargoItemList cargoList = new CargoItemList();
        cargoList.addCargoItemToList(new CargoItem(2, "b", 2));
        cargoList.addCargoItemToList(new CargoItem(2, "a", 2));
        Plane aPlane = new Plane("This kind of plane", 4); //weight lim = 4
        Trip aTrip = new Trip (aPlane);
        aTrip.loadAirplane(cargoList);
        //todo: move line bellow into data attributes
        CargoItemList expectedCargoItemList = new CargoItemList(); // the items in the actual list are of the reverse to which they were added to the original potential items.
        expectedCargoItemList.addCargoItemToList(new CargoItem(2, "a", 2));
        expectedCargoItemList.addCargoItemToList(new CargoItem(2, "b", 2));
        assertEquals(expectedCargoItemList.toString(), aTrip.getPlaneForTrip().getItemsToTake().toString());
        assertEquals("", aTrip.getCargoListGenerator().getItemsLeftBehind().toString());

        //E)
        // test was moved into "all items left behind", e.g. no items on actual list

        // F
        // test that proper optimal list stored when 1) Many items are on list (8) 2)All items on potential list should be included
        CargoItem c1 = new CargoItem(2,"c1",2);
        CargoItem c2 = new CargoItem(1,"c2",1);
        CargoItem c3 = new CargoItem(3,"c3",2);
        CargoItem c4 = new CargoItem(2,"c4",3);
        CargoItem c5 = new CargoItem(4,"c5",4);
        CargoItem c6 = new CargoItem(4,"c6",1);
        CargoItem c7 = new CargoItem(3,"c7",1);
        CargoItem c8 = new CargoItem(3,"c8",3);

        CargoItemList julianaItems = new CargoItemList();
        julianaItems.addCargoItemToList(c1);
        julianaItems.addCargoItemToList(c2);
        julianaItems.addCargoItemToList(c3);
        julianaItems.addCargoItemToList(c4);
        julianaItems.addCargoItemToList(c5);
        julianaItems.addCargoItemToList(c6);
        julianaItems.addCargoItemToList(c7);
        julianaItems.addCargoItemToList(c8);

        Plane bPlane = new Plane("Airbus", 30);
        Trip bTrip = new Trip(bPlane);
        bTrip.loadAirplane(julianaItems);

        expectedCargoItemList.clearList();
        expectedCargoItemList.addCargoItemToList(c8);
        expectedCargoItemList.addCargoItemToList(c7);
        expectedCargoItemList.addCargoItemToList(c6);
        expectedCargoItemList.addCargoItemToList(c5);
        expectedCargoItemList.addCargoItemToList(c4);
        expectedCargoItemList.addCargoItemToList(c3);
        expectedCargoItemList.addCargoItemToList(c2);
        expectedCargoItemList.addCargoItemToList(c1);

        assertEquals(expectedCargoItemList.toString(), bTrip.getPlaneForTrip().getItemsToTake().toString());
        assertEquals("", bTrip.getCargoListGenerator().getItemsLeftBehind().toString());

        //additional test
        // test that proper optimal list stored when 1) only one item on potential list (100) 2)All items on potential list should be included
        //TODO

    }

    @Test
    public void allItemsLeftBehind() throws Exception {//tests that all items are excluded from optimal list when appropriate
        //G: test that proper optimal list stored when 1) only one item on potential list
        CargoItem teapot = new CargoItem(2,"teapot",2);

        CargoItemList julianaItems = new CargoItemList();
        julianaItems.addCargoItemToList(teapot);

        Plane largePlane = new Plane("Large Plane", 1);
        Trip summerTrip = new Trip(largePlane);
        summerTrip.loadAirplane(julianaItems);

        CargoItemList expectedCargoItemList = new CargoItemList();
        assertEquals(expectedCargoItemList.toString(), summerTrip.getPlaneForTrip().getItemsToTake().toString());
        assertEquals(julianaItems.toString(), summerTrip.getCargoListGenerator().getItemsLeftBehind().toString());

        // E: test that proper optimal list stored when no items are in the potential list of items
        julianaItems.clearList();
        largePlane.getItemsToTake().clearList();
        Trip fallTrip = new Trip(largePlane);
        fallTrip.loadAirplane(julianaItems);

        assertEquals("", fallTrip.getPlaneForTrip().getItemsToTake().toString());
        assertEquals("", fallTrip.getCargoListGenerator().getItemsLeftBehind().toString());

        // I: two identical items on list
        CargoItem nailpolish = new CargoItem(2, "Nail Polish ", 2);
        CargoItem nailpolishRed = new CargoItem(2, "Nail Polish ", 2);

        julianaItems.clearList();
        julianaItems.addCargoItemToList(nailpolish);
        julianaItems.addCargoItemToList(nailpolishRed);//have identical items on list

        largePlane.getItemsToTake().clearList();
        Trip winterTrip = new Trip(largePlane);
        winterTrip.loadAirplane(julianaItems);

        expectedCargoItemList.clearList();
        expectedCargoItemList.addCargoItemToList(nailpolish);
        expectedCargoItemList.addCargoItemToList(nailpolishRed);

        assertEquals("", winterTrip.getPlaneForTrip().getItemsToTake().toString());
        assertEquals(expectedCargoItemList.toString(), winterTrip.getCargoListGenerator().getItemsLeftBehind().toString());

        //J: Many items (8)
        CargoItem c1 = new CargoItem(2,"c1",2);
        CargoItem c2 = new CargoItem(5,"c2",1);
        CargoItem c3 = new CargoItem(3,"c3",2);
        CargoItem c4 = new CargoItem(2,"c4",3);
        CargoItem c5 = new CargoItem(4,"c5",4);
        CargoItem c6 = new CargoItem(4,"c6",1);
        CargoItem c7 = new CargoItem(3,"c7",1);
        CargoItem c8 = new CargoItem(3,"c8",3);

        julianaItems.clearList();
        julianaItems.addCargoItemToList(c1);
        julianaItems.addCargoItemToList(c2);
        julianaItems.addCargoItemToList(c3);
        julianaItems.addCargoItemToList(c4);
        julianaItems.addCargoItemToList(c5);
        julianaItems.addCargoItemToList(c6);
        julianaItems.addCargoItemToList(c7);
        julianaItems.addCargoItemToList(c8);

        Plane bPlane = new Plane("Airbus", 1);
        Trip RomeTrip = new Trip(bPlane);
        RomeTrip.loadAirplane(julianaItems);

        expectedCargoItemList.clearList();
        expectedCargoItemList.addCargoItemToList(c8);
        expectedCargoItemList.addCargoItemToList(c7);
        expectedCargoItemList.addCargoItemToList(c6);
        expectedCargoItemList.addCargoItemToList(c5);
        expectedCargoItemList.addCargoItemToList(c4);
        expectedCargoItemList.addCargoItemToList(c3);
        expectedCargoItemList.addCargoItemToList(c2);
        expectedCargoItemList.addCargoItemToList(c1);

        HashSet<CargoItem> expectedItemsSet = new HashSet<>();
        HashSet<CargoItem> actualItemSet = new HashSet<>();

        expectedItemsSet.addAll(expectedCargoItemList.getItemArrayList());
        actualItemSet.addAll(RomeTrip.getCargoListGenerator().getItemsLeftBehind().getItemArrayList());

        assertEquals("", RomeTrip.getPlaneForTrip().getItemsToTake().toString());
        assertEquals(expectedItemsSet, actualItemSet);

        //additional test
        // TODO Many items (100)

    }

    @Test
    public void twoOrMoreSolutions() throws Exception {//Tests to see if at least one proper solution is found when there are multiple possible solutions
        //todo split this into multiple methods

        // K: two identical items on list of potential items. both items are in the solution
        CargoItem notebook0 = new CargoItem(3,"notebook0",5);
        CargoItem notebook1 = new CargoItem(2,"notebook1",8);
        CargoItem notebook2 = new CargoItem(3,"notebook2",2);
        CargoItem notebook3 = new CargoItem(2,"notebook3",2);

        CargoItemList julianaItems = new CargoItemList();
        julianaItems.addCargoItemToList(notebook3);
        julianaItems.addCargoItemToList(notebook2);
        julianaItems.addCargoItemToList(notebook0);
        julianaItems.addCargoItemToList(notebook1);

        Plane coolPlane = new Plane("This is a cool plane", 8);
        Trip tokyoTrip = new Trip(coolPlane);
        tokyoTrip.loadAirplane(julianaItems);

        CargoItemList expectedCargoItemList0 = new CargoItemList(); //one possible expected solution
        expectedCargoItemList0.addCargoItemToList(notebook0);
        expectedCargoItemList0.addCargoItemToList(notebook1);
        expectedCargoItemList0.addCargoItemToList(notebook3);

        CargoItemList expectedCargoItemList1 = new CargoItemList(); //second possible expected solution
        expectedCargoItemList1.addCargoItemToList(notebook0);
        expectedCargoItemList1.addCargoItemToList(notebook1);
        expectedCargoItemList1.addCargoItemToList(notebook2);

        HashSet<CargoItem> expectedItemsSet0 = new HashSet<>();
        HashSet<CargoItem> expectedItemsSet1 = new HashSet<>();
        HashSet<CargoItem> actualItemSet = new HashSet<>();

        expectedItemsSet0.addAll(expectedCargoItemList0.getItemArrayList());
        expectedItemsSet1.addAll(expectedCargoItemList1.getItemArrayList());
        actualItemSet.addAll(tokyoTrip.getPlaneForTrip().getItemsToTake().getItemArrayList());

        CargoItemList expectedLeftBehindItems0 = new CargoItemList();
        expectedLeftBehindItems0.addCargoItemToList(notebook3);

        CargoItemList expectedLeftBehindItems1 = new CargoItemList();
        expectedLeftBehindItems1.addCargoItemToList(notebook2);

        assertTrue(actualItemSet.equals(expectedItemsSet0) || actualItemSet.equals(expectedItemsSet1));
        assertTrue(expectedLeftBehindItems0.toString().equals(tokyoTrip.getCargoListGenerator().getItemsLeftBehind().toString()) || expectedLeftBehindItems1.toString().equals(tokyoTrip.getCargoListGenerator().getItemsLeftBehind().toString()));

        // L: two identical items on list of potential items. neither item is in the solution
        CargoItem shirt0 = new CargoItem(3,"shirt0",1);
        CargoItem shirt1 = new CargoItem(3,"shirt1",1);
        CargoItem shirt2 = new CargoItem(1,"shirt2",1);
        CargoItem shirt3 = new CargoItem(2,"shirt3",1);
        julianaItems.clearList();
        julianaItems.addCargoItemToList(shirt0);
        julianaItems.addCargoItemToList(shirt1);
        julianaItems.addCargoItemToList(shirt2);
        julianaItems.addCargoItemToList(shirt3);
        coolPlane.getItemsToTake().clearList();
        coolPlane.setMaxOzWeight(2);

        Trip parisTrip = new Trip(coolPlane);
        parisTrip.loadAirplane(julianaItems);

        HashSet<CargoItem> expectedItemsSet3 = new HashSet<>();
        HashSet<CargoItem> expectedItemsSet4 = new HashSet<>();
        HashSet<CargoItem> actualItemSet1 = new HashSet<>();
        expectedItemsSet3.add(shirt2);
        expectedItemsSet4.add(shirt3);
        actualItemSet1.addAll(parisTrip.getPlaneForTrip().getItemsToTake().getItemArrayList());

        assertTrue(actualItemSet1.equals(expectedItemsSet3) || actualItemSet1.equals(expectedItemsSet4));

        HashSet<CargoItem> expectedItemsLeftSet0 = new HashSet<>(); // solution one
        HashSet<CargoItem> expectedItemsLeftSet1 = new HashSet<>(); // solution two
        HashSet<CargoItem> actualItemsLeftSet = new HashSet<>();

        expectedItemsLeftSet0.add(shirt0);
        expectedItemsLeftSet0.add(shirt1);
        expectedItemsLeftSet0.add(shirt3);

        expectedItemsLeftSet1.add(shirt0);
        expectedItemsLeftSet1.add(shirt1);
        expectedItemsLeftSet1.add(shirt2);

        actualItemsLeftSet.addAll(parisTrip.getCargoListGenerator().getItemsLeftBehind().getItemArrayList());

        assertTrue(actualItemsLeftSet.equals(expectedItemsLeftSet0) || actualItemsLeftSet.equals(expectedItemsLeftSet1));


    }

    @Test
    public void twoSolutionsManyItemsOnPList(){
        // ensures code works when
        //there are two solutions
        // there is only one item taken
        //N)
        // Many items on list

        CargoItem boots0 = new CargoItem(1,"boots0",20);
        CargoItem boots1 = new CargoItem(2,"boots1",20);
        CargoItem boots2 = new CargoItem(3,"boots2",1);
        CargoItem boots3 = new CargoItem(46,"boots3",34);
        CargoItem boots4 = new CargoItem(5,"boots4",56);
        CargoItem boots5 = new CargoItem(6,"boots5",89);
        CargoItem boots6 = new CargoItem(7,"boots6",57);
        CargoItem boots7 = new CargoItem(60,"boots7",900);
        CargoItem boots8 = new CargoItem(34,"boots8",875);
        CargoItem boots9 = new CargoItem(90,"boots9",3);
        CargoItem boots10 = new CargoItem(26,"boots10",6);

        CargoItemList breeList = new CargoItemList();
        breeList.addCargoItemToList(boots0);
        breeList.addCargoItemToList(boots1);
        breeList.addCargoItemToList(boots2);
        breeList.addCargoItemToList(boots3);
        breeList.addCargoItemToList(boots4);
        breeList.addCargoItemToList(boots5);
        breeList.addCargoItemToList(boots6);
        breeList.addCargoItemToList(boots7);
        breeList.addCargoItemToList(boots8);
        breeList.addCargoItemToList(boots9);
        breeList.addCargoItemToList(boots10);

        Plane greenPlane = new Plane("Green Plane", 2);
        Trip yamagataTrip = new Trip(greenPlane);
        yamagataTrip.loadAirplane(breeList);

        HashSet<CargoItem> expectedItemsSet0 = new HashSet<>();// possible solution one
        HashSet<CargoItem> expectedItemsSet1 = new HashSet<>();// possible solution two
        HashSet<CargoItem> actualItemSet0 = new HashSet<>();

        expectedItemsSet0.add(boots0);
        expectedItemsSet1.add(boots1);
        actualItemSet0.addAll(yamagataTrip.getPlaneForTrip().getItemsToTake().getItemArrayList());

        assertTrue(actualItemSet0.equals(expectedItemsSet0) || actualItemSet0.equals(expectedItemsSet1));

        HashSet<CargoItem> expectedItemsLeftSet0 = new HashSet<>(); // solution one
        HashSet<CargoItem> expectedItemsLeftSet1 = new HashSet<>(); // solution two
        HashSet<CargoItem> actualItemsLeftSet = new HashSet<>();

        expectedItemsLeftSet0.add(boots1);
        expectedItemsLeftSet0.add(boots2);
        expectedItemsLeftSet0.add(boots3);
        expectedItemsLeftSet0.add(boots4);
        expectedItemsLeftSet0.add(boots5);
        expectedItemsLeftSet0.add(boots6);
        expectedItemsLeftSet0.add(boots7);
        expectedItemsLeftSet0.add(boots8);
        expectedItemsLeftSet0.add(boots9);
        expectedItemsLeftSet0.add(boots10);

        expectedItemsLeftSet1.add(boots1);
        expectedItemsLeftSet1.add(boots2);
        expectedItemsLeftSet1.add(boots3);
        expectedItemsLeftSet1.add(boots4);
        expectedItemsLeftSet1.add(boots5);
        expectedItemsLeftSet1.add(boots6);
        expectedItemsLeftSet1.add(boots7);
        expectedItemsLeftSet1.add(boots8);
        expectedItemsLeftSet1.add(boots9);
        expectedItemsLeftSet1.add(boots10);

        actualItemsLeftSet.addAll(yamagataTrip.getCargoListGenerator().getItemsLeftBehind().getItemArrayList());

        assertTrue(actualItemsLeftSet.equals(expectedItemsLeftSet0) || actualItemsLeftSet.equals(expectedItemsLeftSet1));
    }

    @Test
    public void oneItemTakenOnePossSolution() throws Exception {
        //Tests to see if the proper solution is found when
        // there should be only one item taken along from list of potential items.
        // One Possible solution.
        // V)  two identical items on list of potential items. neither item is in the solution
        // There are a larger number of items on the list
        // proper item to bring is in the middle of the potential list

        CargoItem boots1 = new CargoItem(20,"boots1",20);
        CargoItem boots2 = new CargoItem(34,"boots2",1);
        CargoItem boots3 = new CargoItem(46,"boots3",34);
        CargoItem boots4 = new CargoItem(500,"boots4",56);
        CargoItem boots5 = new CargoItem(77,"boots5",57);
        CargoItem boots6 = new CargoItem(77,"boots6",57);
        CargoItem boots0 = new CargoItem(4,"boots0",20);
        CargoItem boots7 = new CargoItem(60,"boots7",900);
        CargoItem boots8 = new CargoItem(34,"boots8",875);
        CargoItem boots9 = new CargoItem(90,"boots9",3);
        CargoItem boots10 = new CargoItem(26,"boots10",6);

        CargoItemList DaniList = new CargoItemList();
        DaniList.addCargoItemToList(boots0);
        DaniList.addCargoItemToList(boots1);
        DaniList.addCargoItemToList(boots2);
        DaniList.addCargoItemToList(boots3);
        DaniList.addCargoItemToList(boots4);
        DaniList.addCargoItemToList(boots5);
        DaniList.addCargoItemToList(boots6);
        DaniList.addCargoItemToList(boots7);
        DaniList.addCargoItemToList(boots8);
        DaniList.addCargoItemToList(boots9);
        DaniList.addCargoItemToList(boots10);

        Plane greenPlane = new Plane("Green Plane", 5);
        Trip yamagataTrip = new Trip(greenPlane);
        yamagataTrip.loadAirplane(DaniList);

        HashSet<CargoItem> expectedItemsSet0 = new HashSet<>();// possible solution one
        HashSet<CargoItem> actualItemSet0 = new HashSet<>();

        expectedItemsSet0.add(boots0);
        actualItemSet0.addAll(yamagataTrip.getPlaneForTrip().getItemsToTake().getItemArrayList());

        assertEquals(expectedItemsSet0, actualItemSet0);

        HashSet<CargoItem> expectedItemsLeftSet0 = new HashSet<>(); // solution one
        HashSet<CargoItem> actualItemsLeftSet = new HashSet<>();

        expectedItemsLeftSet0.add(boots1);
        expectedItemsLeftSet0.add(boots2);
        expectedItemsLeftSet0.add(boots3);
        expectedItemsLeftSet0.add(boots4);
        expectedItemsLeftSet0.add(boots5);
        expectedItemsLeftSet0.add(boots6);
        expectedItemsLeftSet0.add(boots7);
        expectedItemsLeftSet0.add(boots8);
        expectedItemsLeftSet0.add(boots9);
        expectedItemsLeftSet0.add(boots10);

        actualItemsLeftSet.addAll(yamagataTrip.getCargoListGenerator().getItemsLeftBehind().getItemArrayList());

        assertEquals(expectedItemsLeftSet0, actualItemsLeftSet);

    }

    @Test
    public void moreThanOneItemTakenOnePossSolution() throws Exception {
        //Tests to see if the proper solution is found when
        // there should be more than one item taken along from list of potential.
        // One possible solution
        // Z: two identical items on list of potential items. neither item is in the solution
        CargoItem boots1 = new CargoItem(20,"boots1",20); //
        CargoItem boots2 = new CargoItem(340,"boots2",1);
        CargoItem boots3 = new CargoItem(46,"boots3",34); //
        CargoItem boots4 = new CargoItem(500,"boots4",56);
        CargoItem boots5 = new CargoItem(77,"boots5",57);
        CargoItem boots6 = new CargoItem(77,"boots6",57);
        CargoItem boots0 = new CargoItem(4,"boots0",20); //
        CargoItem boots7 = new CargoItem(60,"boots7",9);
        CargoItem boots8 = new CargoItem(34,"boots8",875); //
        CargoItem boots9 = new CargoItem(90,"boots9",3);
        CargoItem boots10 = new CargoItem(26,"boots10",2);

        CargoItemList SallyList = new CargoItemList();
        SallyList.addCargoItemToList(boots0);
        SallyList.addCargoItemToList(boots1);
        SallyList.addCargoItemToList(boots2);
        SallyList.addCargoItemToList(boots3);
        SallyList.addCargoItemToList(boots4);
        SallyList.addCargoItemToList(boots5);
        SallyList.addCargoItemToList(boots6);
        SallyList.addCargoItemToList(boots7);
        SallyList.addCargoItemToList(boots8);
        SallyList.addCargoItemToList(boots9);
        SallyList.addCargoItemToList(boots10);

        Plane japanAirPlane = new Plane("Japan Plane", 105);
        Trip ObanazawaTrip = new Trip(japanAirPlane);
        ObanazawaTrip.loadAirplane(SallyList);

        HashSet<CargoItem> expectedItemsSet0 = new HashSet<>();// possible solution one
        HashSet<CargoItem> actualItemSet0 = new HashSet<>();

        expectedItemsSet0.add(boots8);
        expectedItemsSet0.add(boots3);
        expectedItemsSet0.add(boots1);
        expectedItemsSet0.add(boots0);

        actualItemSet0.addAll(japanAirPlane.getItemsToTake().getItemArrayList());

        assertEquals(expectedItemsSet0, actualItemSet0);

        HashSet<CargoItem> expectedItemsLeftSet0 = new HashSet<>(); // solution one
        HashSet<CargoItem> actualItemsLeftSet = new HashSet<>();

        expectedItemsLeftSet0.add(boots6);
        expectedItemsLeftSet0.add(boots7);
        expectedItemsLeftSet0.add(boots2);
        expectedItemsLeftSet0.add(boots5);
        expectedItemsLeftSet0.add(boots9);
        expectedItemsLeftSet0.add(boots4);
        expectedItemsLeftSet0.add(boots10);

        actualItemsLeftSet.addAll(ObanazawaTrip.getCargoListGenerator().getItemsLeftBehind().getItemArrayList());

        assertEquals(expectedItemsLeftSet0, actualItemsLeftSet);

        // a: two identical items on list of potential items. only one item is in the solution


        // many items are on list of potential items (100)

        //todo uncommment out the TWO tests in loadAirplane
    }

}