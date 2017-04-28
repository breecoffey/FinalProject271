package com.company;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by julianareider on 4/14/17.
 */


public class CargoListGeneratorTest {
    private CargoItem firstItem = new CargoItem(1, "firstItem", 1);
    private CargoItem secondItem = new CargoItem(3, "secondItem", 4);
    private CargoItem thirdItem = new CargoItem(4, "thirdItem", 5);
    private CargoItem fourthItem = new CargoItem(5, "fourthItem", 7);
    CargoItemList itemList = new CargoItemList();
    CargoListGenerator testGenerator2 = new CargoListGenerator(itemList);
    CargoListGenerator testGen = new CargoListGenerator();
    CargoListGenerator testGen3 = new CargoListGenerator(itemList);

    private CargoItem aItem = new CargoItem(10, "aItem", 60);
    private CargoItem bItem = new CargoItem(20, "bItem", 100);
    private CargoItem cItem = new CargoItem(30, "cItem", 120);
    CargoItemList anotherItemList = new CargoItemList();
    CargoListGenerator testGenerator3 = new CargoListGenerator(anotherItemList);


    @Test
    public void calculateItemsLeftBehind() throws Exception {
        CargoItemList itemList = new CargoItemList();
        itemList.addCargoItemToList(firstItem);
        itemList.addCargoItemToList(secondItem);
        itemList.addCargoItemToList(thirdItem);
        itemList.addCargoItemToList(fourthItem);
        CargoListGenerator testGen4 = new CargoListGenerator();
        testGen4.setPotentialItems(itemList);
        CargoItemList tempActualItems = new CargoItemList();
        tempActualItems.addCargoItemToList(firstItem);
        tempActualItems.addCargoItemToList(secondItem);
        testGen4.setActualItems(tempActualItems);
        testGen4.calculateItemsLeftBehind();
        System.out.println("This is the End of my new tests. ");

        //todo check that the all items from actual list is subtracted from potential list . and the remaining are stored in itemsLeftBehind


    }
    @Test
    public void fillValueMDArray() throws Exception {
        //method to generally test the fillValueMDArray function
        int maxWeight = 7;
        itemList.addCargoItemToList(firstItem);
        itemList.addCargoItemToList(secondItem);
        itemList.addCargoItemToList(thirdItem);
        itemList.addCargoItemToList(fourthItem);
        int numItems = itemList.getSize();
        testGenerator2.fillValueMDArray(maxWeight);

        String stringTestOfMDArray = "";
        int[][] mdArray = testGenerator2.getPossibleValueArray();
        for (int indexOfAnItem = 0; indexOfAnItem <= numItems; indexOfAnItem++) {//considering one by one all items
            for (int incrementWeight = 0; incrementWeight <= maxWeight; incrementWeight++) {
                stringTestOfMDArray += mdArray[indexOfAnItem][incrementWeight] + ",";
            }
            stringTestOfMDArray += "\n";
        }
        //todo add more tests
    }


    @Test
    public void generateList() throws Exception {
        //method to generally test the generateList function
        itemList.addCargoItemToList(firstItem);
        itemList.addCargoItemToList(secondItem);
        itemList.addCargoItemToList(thirdItem);
        itemList.addCargoItemToList(fourthItem);
        //CargoListGenerator testGen = new CargoListGenerator();
        String expectedString = "Bring these items: \n" +
                "\tItem: thirdItem, Weight: 4, Value: 5\n" +
                "\tItem: secondItem, Weight: 3, Value: 4\n" +
                "Total value of these items is: 9\n" +
                "Leave behind these items: \n" +
                "\tItem: fourthItem, Weight: 5, Value: 7\n" +
                "\tItem: firstItem, Weight: 1, Value: 1\n"; //todo - once leave items behind calculation is done this should be updated
        assertEquals(expectedString, testGen.generateList(itemList, 7));

        anotherItemList.addCargoItemToList(aItem);
        anotherItemList.addCargoItemToList(bItem);
        anotherItemList.addCargoItemToList(cItem);
        //CargoListGenerator testGenerator3 = new CargoListGenerator(anotherItemList);
        expectedString = "Bring these items: \n" +
                "\tItem: cItem, Weight: 30, Value: 120\n" +
                "\tItem: bItem, Weight: 20, Value: 100\n" +
                "Total value of these items is: 220\n" +
                "Leave behind these items: \n" +
                "\tItem: aItem, Weight: 10, Value: 60\n";
        assertEquals(expectedString, testGenerator3.generateList(anotherItemList, 50)); //todo uncomment this

        //assertEquals("Item: secondItem, Weight: 3, Value: 4\n" +
        //        "Item: thirdItem, Weight: 4, Value: 5\n", testGen.generateList(itemList,7)); temporarily did this so the test wouldn't fail
    }

    @Test
    public void calculateMaxValue() throws Exception {
        ////method to generally test the calculateMaxValue function
        itemList.addCargoItemToList(firstItem);
        itemList.addCargoItemToList(secondItem);
        itemList.addCargoItemToList(thirdItem);
        itemList.addCargoItemToList(fourthItem);
        //CargoListGenerator testGenerator2 = new CargoListGenerator(itemList);
        assertEquals(9, testGenerator2.calculateMaxValue(7)); //size would be four, second parameter


        anotherItemList.addCargoItemToList(aItem);
        anotherItemList.addCargoItemToList(bItem);
        anotherItemList.addCargoItemToList(cItem);
        //CargoListGenerator testGenerator3 = new CargoListGenerator(anotherItemList);
        assertEquals(220, testGenerator3.calculateMaxValue(50)); // size would be three second param

        CargoListGenerator testG = new CargoListGenerator(anotherItemList);
        assertEquals(280, testG.calculateMaxValue(1000)); //tests for if all items can fit, if the value will be calculated correctly
        assertEquals(0, testG.calculateMaxValue(0)); //test to see if the max weight is zero, if no value can be stored on the plane

    }

    @Test
    public void findSelectedItems() throws Exception {
        //method to generally test the findSelectedItems function
        itemList.addCargoItemToList(firstItem);
        itemList.addCargoItemToList(secondItem);
        itemList.addCargoItemToList(thirdItem);
        itemList.addCargoItemToList(fourthItem);
        //CargoListGenerator testGen3 = new CargoListGenerator(itemList);
        testGen3.calculateMaxValue(7);
        //findSelectedItems(5,8);
        //assertEquals(9, testGen3.findSelectedItems(5, 8));

        //I think another additional way to test this method further is to
        // check that the sum of the values of all the selected items
        // have a combined value equal last value in the table
    }

}