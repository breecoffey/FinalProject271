package com.company;

import java.util.ArrayList;

/**
 * Created by julianareider on 4/10/17.
 * // A Dynamic Programming based solution for 0-1 Knapsack problem
 class Knapsack
 */
public class CargoListGenerator {

    private CargoItemList potentialItems;
    private CargoItemList actualItems;
    private CargoItemList itemsLeftBehind;
    private int[][] possibleValueArray;

    public CargoListGenerator(){
        potentialItems = new CargoItemList();
        actualItems = new CargoItemList();
        itemsLeftBehind = new CargoItemList();
    }

    //which of the two constructors bellow is needed?
    public CargoListGenerator(ArrayList<CargoItem> pI){
        potentialItems = new CargoItemList(pI);
        actualItems = new CargoItemList();
        itemsLeftBehind = new CargoItemList();
    }
    public CargoListGenerator(CargoItemList pI){
        potentialItems = pI;
        actualItems = new CargoItemList();
        itemsLeftBehind = new CargoItemList();
    }

    public void generateList(){
        //calls calculateMaxValue
        //calls findSelectedItems
    }

    // Returns the maximum value that can be put in a knapsack of capacity maxWeight
    //dysnamic programming not recursive
    public int calculateMaxValue(int maxWeight, int weightArray[], int valueArray[], int numItems){ //todo change to arraylist? /// change to private??


        //NOTE: the index of a given item in MDarry is one greater than the index of item in cargo array

        int indexOfAnItem, incrementWeight;
        int K[][] = new int[numItems+1][maxWeight+1]; //[row][column]

        // Build table K[][] in bottom up manner
        //finding solutions to different combinations of items and max weights. then storing them
        for (indexOfAnItem = 0; indexOfAnItem <= numItems; indexOfAnItem++)
        {//considering one by one all items
            for (incrementWeight = 0; incrementWeight <= maxWeight; incrementWeight++)
            {// trying different poss weights scenarios
                if (indexOfAnItem==0 || incrementWeight==0) //if items or weight is zero, solution must be zero
                    K[indexOfAnItem][incrementWeight] = 0;
                else if (weightArray[indexOfAnItem-1] <= incrementWeight) // if the weight of a given item is less than the incrementWeight, enter this branch
                    K[indexOfAnItem][incrementWeight] = max(valueArray[indexOfAnItem-1] + K[indexOfAnItem-1][incrementWeight-weightArray[indexOfAnItem-1]],  K[indexOfAnItem-1][incrementWeight]);
                    //compare the value of including the item verses not including it our optimal set (comparison is done with the max() method.
                    // then stores the max value in the MDarray.
                else //if weight goes over, don't include.
                    K[indexOfAnItem][incrementWeight] = K[indexOfAnItem-1][incrementWeight];
                    //
            }
        }
        possibleValueArray = K;
        /*for (indexOfAnItem = 0; indexOfAnItem <= numItems; indexOfAnItem++) {//considering one by one all items
            for (incrementWeight = 0; incrementWeight <= maxWeight; incrementWeight++) {
                System.out.print(possibleValueArray[indexOfAnItem][incrementWeight] + ",");
            }
            System.out.println();
        }*/

        //todo  know for sure if the is all possible solutions bellow the weight limit or not.

        //todo IDEA: let's call the wrapper method from here and return a  string with our information we want, instead of returning a int from this method.

        return K[numItems][maxWeight];
    }

    /**
     *
     * @param row
     * @param col
     * @return
     * @pre condition must have run the calculateMaxValue() with the proper values - or it won't be accurate return vlaue
     */
    private void findSelectedItems(int row, int col){//recurseive method.
        //pesudocode for finding taken items, searches the MDarray for proper items
        //if the value stored in the MDarray is 0,this a BASE CASE: if K[r][c] == 0
        if (possibleValueArray[row][col] == 0){
            // there are no more items to add.
        }
        //      deal with properly
        //      return items you have added to the list, since there are no more items to add.
        //Else If value one row above K[r-1], is == to the num stored in curr cell, k[r][c], then
        else if (possibleValueArray[row][col] == possibleValueArray[row-1][col]){
            //curr item is not included
            findSelectedItems(row-1, col);
        }
        //      item is not included
        //      find next cell to examin row = r-1 col = currCol
        //      -- call recursive method again
        else if (possibleValueArray[row][col] != possibleValueArray[row-1][col]){
            actualItems.addCargoItemToList(potentialItems.getItem(row-1));//todo double check this (row-1)
            findSelectedItems(row-1, col - potentialItems.getItem(row-1).getOzWeight());
        }
        //Else If value one row above is != to the curr number k[r][c], then
        //      add item to list (item is included)
        //      find the next cell to examin: next col = currCol - weightOfItemJustTook.  next row = currRow -1

        // wrapper method calls recursive method w/ k[n][w]
        // w/in wrapper method , check that the selected items with a lower weight, and the value equal last value in the table
        // this makes sure that the items add up to correct value. a way to double check our function
        //

        //return actualItems;
    }

    /**
     * Wrapper method
     * @param row
     * @param col
     * @return
     */
    public CargoItemList findSelectedItemsWrapper(int row, int col){
        return actualItems;

    }

    // A utility function that returns maximum of two integers
    private int max(int first, int second){ //rename parameters to reflect what we intend the values to represent

        return (first > second)? first : second; //todo more representitive names
    }

    //POSSIBLE INNER CLASS OF TRIP.


}
