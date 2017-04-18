package com.company;

import java.util.ArrayList;

/**
 * Created by julianareider on 4/10/17.
 * // A Dynamic Programming based solution for 0-1 Knapsack problem
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
        // possibleValueArray set values to negative one to initialize it?
    }

    public CargoListGenerator(CargoItemList pI){
        potentialItems = pI;
        actualItems = new CargoItemList();
        itemsLeftBehind = new CargoItemList();
    }

    /** Assures the the methods are called in the proper order.
     *
     * @return returned the value of the items stored on the optimal list with the give maxWeight
     */
    public String generateList(CargoItemList usersPotentialItems, int maxWeight){
        //stub //test this method

        potentialItems = usersPotentialItems;
        fillValueMDArray(maxWeight);
        int maxVal = calculateMaxValue(maxWeight);
        int row = potentialItems.getSize(); //do we need to add one for these? I trust your judgement Bree, the plus one still throws me off haha.
        int col = maxWeight;
        findSelectedItems(row, col);
        calculateItemsLeftBehind(); //this is an unfinished function so this will be empty for now
        return "Bring these items: \n" + actualItems.toString() +
                "Total value of these items is: " + maxVal +
                "\nLeave behind these items: \n" + itemsLeftBehind.toString(); //items must be stored in actual items at this point and calculateItemsLeftBehind must have been called.

    }

    /**
     * Returns the maximum value that can be put in a knapsack of capacity maxWeight
     * dynamic programming not recursive
     * @param maxWeight the maximum weight of cargo that can be stored in the airplane.
     * @return
     * @pre there must be potential items stored in potentialItems list
     */
    public int calculateMaxValue(int maxWeight){
        // I decided to change it back to return an int because it's called "calculateMaxValue", and the string represents more information that that.
        // I thought it might be better encapsulation if we kept the purpuse and return value more simple. I thought it might be easier to follow the flow
        // I thought it might be easier to follow the flow for programmers who were unfamilar with the code.
        // Also I broke this down into two functions for further encapsulation.
        // Thank you so much though for figuring out how to call findSelectedItems from this function though!
        // cause I know it was something we talked about when we last coded together :D
        fillValueMDArray(maxWeight);
        int numItems = potentialItems.getSize();
        int maxVal = possibleValueArray[numItems][maxWeight]; //used to be K[numItems][maxWeight] instead
        return maxVal; //todo  know for sure if the is all possible solutions bellow the weight limit or not, or just one

        //findSelectedItems(numItems, maxWeight);
        //return "The maximum value is: " + maxVal + "\nYou should bring: \n" + actualItems.toString();
    }

    /**
     * Fills value MD array based on the maximum weight and list of cargo items stored in possibleItmes
     * the largest value possible with the weight constraint will be stored inthe bottom right hand corner of the MDArray
     * @param maxWeight
     */
    public void fillValueMDArray(int maxWeight){
        //NOTE: the index of a given item in MDarry is one greater than the index of item in cargo array
        int numItems = potentialItems.getSize();
        int indexOfAnItem, incrementWeight;
        int K[][] = new int[numItems+1][maxWeight+1]; //[row][column] //todo proper name for K[][]

        // Build table K[][] in bottom up manner
        //finding solutions to different combinations of items and max weights. then storing them
        for (indexOfAnItem = 0; indexOfAnItem <= numItems; indexOfAnItem++)
        {//considering one by one all items
            for (incrementWeight = 0; incrementWeight <= maxWeight; incrementWeight++)
            {// trying different poss weights scenarios
                //todo should we throw an exception if there is nothing stored at indexOfAnItem-1?
                if (indexOfAnItem==0 || incrementWeight==0) //if items or weight is zero, solution must be zero
                    K[indexOfAnItem][incrementWeight] = 0;
                else if (potentialItems.getItem(indexOfAnItem-1).getOzWeight() <= incrementWeight) // if the weight of a given item is less than the incrementWeight, enter this branch
                    K[indexOfAnItem][incrementWeight] = max(potentialItems.getItem(indexOfAnItem-1).getValue() + K[indexOfAnItem-1][incrementWeight-potentialItems.getItem(indexOfAnItem-1).getOzWeight()],  K[indexOfAnItem-1][incrementWeight]);
                    //compare the value of including the item verses not including it our optimal set (comparison is done with the max() method.
                    // then stores the max value in the MDarray.
                else //if weight goes over, don't include.
                    K[indexOfAnItem][incrementWeight] = K[indexOfAnItem-1][incrementWeight];
            }
        }
        possibleValueArray = K;

        /* for testing :
        for (indexOfAnItem = 0; indexOfAnItem <= numItems; indexOfAnItem++) {//considering one by one all items
            for (incrementWeight = 0; incrementWeight <= maxWeight; incrementWeight++) {
                System.out.print(possibleValueArray[indexOfAnItem][incrementWeight] + ",");
            }
            System.out.println();
        }
        System.out.println();*/

    }

    /**
     * Searches the MDarray for proper items
     * todo Discuss how, When calling this for the first time, what the param's need to be so it's bottom right hand corner. Of MD array
     * @param row
     * @param col
     * @return
     * @pre condition must have run the calculateMaxValue() with the proper values - or it won't be accurate return vlaue
     */

    public void findSelectedItems(int row, int col){//recursive method. //todo changed to public . (no problem!--Juliana)

        if (possibleValueArray[row][col] == 0){//if the value stored in the MDarray is 0,this a BASE CASE: if K[r][c] == 0
            // there are no more items to add.
        }
        else if (possibleValueArray[row][col] == possibleValueArray[row-1][col]){//if value one row above K[r-1], is == to the num stored in curr cell, k[r][c]
            findSelectedItems(row-1, col);//curr item is not included. move on; find next cell to examine-- call recursive method again
        }
        else if (possibleValueArray[row][col] != possibleValueArray[row-1][col]){//If value one row above is != to the curr number k[r][c]
            actualItems.addCargoItemToList(potentialItems.getItem(row-1));// add item to list (item is included)//todo double check this (row-1)
            findSelectedItems(row-1, col - potentialItems.getItem(row-1).getOzWeight());// examine next cell: next col = currCol - weightOfItemJustTook. next row = currRow -1
        }

        // todo write another method to check that the selected items have a combined value equal last value in the table
        // call from this function?
        // this makes sure that the items add up to correct value. a way to double check our function
    }

    // A utility function that returns maximum of two integers
    private int max(int first, int second){ //rename parameters to reflect what we intend the values to represent
        return (first > second)? first : second; //todo more representative names
    }

    /**
     * items must be stored in potential items and actual items.
     * Returns which items are present in the potential items list but not in the actual items list.
     * @return
     */
    private void calculateItemsLeftBehind(){
        //stub
        //first must write a proper CargoItems equals method.
        // store potential items and actual items in sets and find the differnce of the actual from the potential.
        //store that difference in the itemsLeftBehind list.
        //return it

        //return itemsLeftBehind;
    }

    //POSSIBLE INNER CLASS OF TRIP.


    public int[][] getPossibleValueArray(){
        return possibleValueArray;
    }

    public CargoItemList getActualItems(){
        return actualItems;
    }



}
