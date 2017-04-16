package com.company;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by julianareider on 4/14/17.
 */
public class CargoListGeneratorTest {

    @Test
    public void CargoListGenerator() throws Exception {

    }
    //I wrote this test. It fails. This is a problem lol. It has to do with the generateList method as a whole and i think it fails because findSelectedItems isn't working right.
    @Test
    public void generateList() throws Exception {
        CargoItem firstItem = new CargoItem(1, "firstItem", 1);
        CargoItem secondItem = new CargoItem(3, "secondItem", 4);
        CargoItem thirdItem = new CargoItem(4, "thirdItem", 5);
        CargoItem fourthItem = new CargoItem(5, "fourthItem", 7);
        CargoItemList itemList = new CargoItemList();
        itemList.addCargoItemToList(firstItem);
        itemList.addCargoItemToList(secondItem);
        itemList.addCargoItemToList(thirdItem);
        itemList.addCargoItemToList(fourthItem);
        CargoListGenerator testGen = new CargoListGenerator(itemList);
        assertEquals("Item: secondItem, Weight: 3, Value: 4\n" +
                "Item: thirdItem, Weight: 4, Value: 5\n", testGen.generateList(itemList,7));
    }

    @Test
    public void calculateMaxValue() throws Exception {

        /*CargoListGenerator testGenerator = new CargoListGenerator();

        int[] weightArray = {1,3,4,5};
        int[] valueArray = {1,4,5,7};

        int returnValue0 = testGenerator.calculateMaxValue(7, weightArray, valueArray, 4);
        System.out.println(returnValue0);
        assertEquals(9, returnValue0);

        int wt[] = new int[]{10, 20, 30};
        int val[] = new int[]{60, 100, 120};
        int  W = 50;
        int n = val.length;
        int returnValue1 = testGenerator.calculateMaxValue(W, wt, val, n);
        System.out.println(returnValue1);
        assertEquals(220, returnValue1);*/

        CargoItem firstItem = new CargoItem(1, "firstItem", 1);
        CargoItem secondItem = new CargoItem(3, "secondItem", 4);
        CargoItem thirdItem = new CargoItem(4, "thirdItem", 5);
        CargoItem fourthItem = new CargoItem(5, "fourthItem", 7);
        CargoItemList itemList = new CargoItemList();
        itemList.addCargoItemToList(firstItem);
        itemList.addCargoItemToList(secondItem);
        itemList.addCargoItemToList(thirdItem);
        itemList.addCargoItemToList(fourthItem);
        CargoListGenerator testGenerator2 = new CargoListGenerator(itemList);
        assertEquals(9, testGenerator2.calculateMaxValue(7)); //size would be four, second parameter


        CargoItem aItem = new CargoItem(10, "aItem", 60);
        CargoItem bItem = new CargoItem(20, "bItem", 100);
        CargoItem cItem = new CargoItem(30, "cItem", 120);
        CargoItemList anotherItemList = new CargoItemList();
        anotherItemList.addCargoItemToList(aItem);
        anotherItemList.addCargoItemToList(bItem);
        anotherItemList.addCargoItemToList(cItem);
        CargoListGenerator testGenerator3 = new CargoListGenerator(anotherItemList);
        assertEquals(220, testGenerator3.calculateMaxValue(50)); // size would be three second param

        CargoListGenerator testG = new CargoListGenerator(anotherItemList);
        assertEquals(280, testG.calculateMaxValue(1000)); //tests for if all items can fit, if the value will be calculated correctly
        assertEquals(0, testG.calculateMaxValue(0)); //test to see if the max weight is zero, if no value can be stored on the plane

    }

    @Test
    public void findSelectedItems() throws Exception {
        CargoItem firstItem = new CargoItem(1, "firstItem", 1);
        CargoItem secondItem = new CargoItem(3, "secondItem", 4);
        CargoItem thirdItem = new CargoItem(4, "thirdItem", 5);
        CargoItem fourthItem = new CargoItem(5, "fourthItem", 7);
        CargoItemList itemList = new CargoItemList();
        itemList.addCargoItemToList(firstItem);
        itemList.addCargoItemToList(secondItem);
        itemList.addCargoItemToList(thirdItem);
        itemList.addCargoItemToList(fourthItem);
        CargoListGenerator testGenerator3 = new CargoListGenerator(itemList);
        testGenerator3.calculateMaxValue(7);
        //findSelectedItems(5,8);
        //assertEquals(9, testGenerator3.findSelectedItems(5, 8));
    }

}