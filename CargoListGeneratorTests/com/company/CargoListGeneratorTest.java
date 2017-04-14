package com.company;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by julianareider on 4/14/17.
 */
public class CargoListGeneratorTest {

    @Test
    public void generateList() throws Exception {

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
        assertEquals(9, testGenerator2.calculateMaxValue(7, 4));


        CargoItem aItem = new CargoItem(10, "aItem", 60);
        CargoItem bItem = new CargoItem(20, "bItem", 100);
        CargoItem cItem = new CargoItem(30, "cItem", 120);
        CargoItemList anotherItemList = new CargoItemList();
        anotherItemList.addCargoItemToList(aItem);
        anotherItemList.addCargoItemToList(bItem);
        anotherItemList.addCargoItemToList(cItem);
        CargoListGenerator testGenerator3 = new CargoListGenerator(anotherItemList);
        assertEquals(220, testGenerator3.calculateMaxValue(50, 3));


    }

    @Test
    public void findSelectedItemsWrapper() throws Exception {

    }

}