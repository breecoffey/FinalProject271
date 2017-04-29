package com.company;
import java.util.HashSet;

/**
 * Created by julianareider on 4/10/17.
 * A Dynamic Programming based solution for 0-1 Knapsack problem
 * A generator that assembles  a list of ideal cargo items to transport
 * Maximizes value, within given weight limit.
 */
public class CargoListGenerator {
    /** List of items available, items from this list will be selected to go on the optimal list (actual list).
     * The items will be evaluated based on weight and value to be determined if they should be on the optimal list */
    private CargoItemList potentialItems;
    /** These are the actual items, selected from the potential items list, that are bellow the weight limit and they maximize value. This is an optimal list*/
    private CargoItemList actualItems;
    /** These are the times on the potential list that did not make it onto the actualItems list. They will not be transported*/
    private CargoItemList itemsLeftBehind;
    /** This array is a collection of possible accumulative values of different groupings of items and various incremental weight constraints*/
    private int[][] possibleValueArray;

    //constructors
    /**
     * Constructor that initializes all the attributes of this CargoListGenerator object.
     */
    public CargoListGenerator(){
        potentialItems = new CargoItemList();
        actualItems = new CargoItemList();
        itemsLeftBehind = new CargoItemList();
        // TODO possibleValueArray set values to negative one to initialize it?
    }

    /**
     * Constructor that initializes all the attributes of this CargoListGenerator object.
     * Sets PotentialItems list to the argument CargoItemList object, pI
     * @param pI the potential items list from which another list will be compiled to go on the optimal list (actual list)
     */
    public CargoListGenerator(CargoItemList pI){
        potentialItems = pI;
        actualItems = new CargoItemList();
        itemsLeftBehind = new CargoItemList();
    }

    //Getters

    /**
     * Retrieves the possibleValueArray attribute.
     * @return a multi-dimensional array was returned
     */
    public int[][] getPossibleValueArray(){
        return possibleValueArray;
    }

    /**
     * Retrieves the optimal item list that has items that will maximize value while keeping the total weight of all itmes beneight weight constraint
     * @pre todo there must be some pre conditions to this one
     */
    public CargoItemList getActualItems(){
        return actualItems;
    }

    //todo add javadoc here
    public CargoItemList getItemsLeftBehind(){
        return itemsLeftBehind;
    }


    //setters

    /**
     * Sets the list of potentialItems to the items in a given CargoItemList object
     * @param potentialItems the collection of cargo items to be represented by the potentialItem data attribute
     */
    public void setPotentialItems(CargoItemList potentialItems) {
        CargoItemList tempList = new CargoItemList();
        this.potentialItems = tempList;
        this.potentialItems = potentialItems;
    }

    /**
     * Sets the list of actual items for optimal items list to given cargo items
     * @param aItems the collection of optimal items to be stored in the actualItems data attribute
     */
    public void setActualItems(CargoItemList aItems){
        CargoItemList tempList = new CargoItemList();
        actualItems = tempList;
        actualItems = aItems;
    }


    //Other Methods
    /** Assures the the methods to generate an optimal list are called in the proper order.
     * Decides which are the optimal items to bring or leave behind and generates string reflecting analysis.
     * @param usersPotentialItems a list of cargo items to be evaluated, items on this list will be selected to be a part of the optimal list
     * @param maxWeight the weight limit for all the cargo on optimal list
     * @return returned the value of the items stored on the optimal list with the give maxWeight
     * @pre there must be potential items stored in potentialItems list. Those items must have weight and values stored in their data attributes
     */
    public String generateList(CargoItemList usersPotentialItems, int maxWeight){

        potentialItems = usersPotentialItems;
        fillValueMDArray(maxWeight);
        int maxVal = calculateMaxValue(maxWeight);
        int row = potentialItems.getSize(); //do we need to add one for these? I trust your judgement Bree, the plus one still throws me off haha.
        int col = maxWeight;
        findSelectedItems(row, col);
        calculateItemsLeftBehind(); //this is an unfinished function so this will be empty for now todo add this back in!!!!!
        return "Bring these items: \n" + actualItems.toString() +
                "Total value of these items is: " + maxVal +
                "\nLeave behind these items: \n" + itemsLeftBehind.toString(); //items must be stored in actual items at this point and calculateItemsLeftBehind must have been called.

    }

    /**
     * Returns the maximum accumulative value of cargo items from potentialItems that can have an accumulative weight bellow maxWeight
     * Is a function based on dynamic programming not recursion.
     * @param maxWeight the maximum accumulative weight of cargo that can be stored in the airplane.
     * @return the maximum accumulative value (in American dollars) of cargo items that may be stored from potentialItems, with an accumulative weight bellow the weight limit
     * @pre there must be potential items stored in potentialItems list. Those items must have weight and values stored in their data attributes
     */
    public int calculateMaxValue(int maxWeight){
        fillValueMDArray(maxWeight);
        int numItems = potentialItems.getSize();
        int maxVal = possibleValueArray[numItems][maxWeight]; //used to be K[numItems][maxWeight] instead
        return maxVal; //todo  know for sure if the is all possible solutions bellow the weight limit or not, or just one

        //findSelectedItems(numItems, maxWeight);
        //return "The maximum value is: " + maxVal + "\nYou should bring: \n" + actualItems.toString();
    }

    /**
     * Fills the multi-dimensional array with int values based on the list of cargo items stored in possibleItems, and increment weights up to the maximum weight.
     * The largest value possible with the weight constraint will be stored in the bottom right hand corner of the MDArray.
     * @param maxWeight the weight capacity for the accumulative weight of a group of items, in ounces.
     * @pre function calculateMaxValue must have been called and executed properly immediately before this method.
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
     * Recursively searches the filled multi-dimensional array for items that would be included in the optimal cargo list
     * The optimal cargo list is one which maximizes value while keeping their accumulative weight bellow the weight limit)s
     * Recursive method.
     * @param row row of the next cell to be evaluated  (within the multi-dimensional array)
     * @param col column of the next cell to be evaluated  (within the multi-dimensional array)
     * @pre  the fillValueMDArray method must have been called immediately before this. Proper list must be stored in potential items. actualItems list is empty!
     * @post the list of the optimal cargo items were stored in actualItems list
     */
    public void findSelectedItems(int row, int col){
        //todo Discuss how, When calling this for the first time, what the param's need to be so it's bottom right hand corner. Of MD array
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

    /**
     * A utility function that returns maximum of two integers
     * @param firstInt The first int to be compared
     * @param secondInt The other int to be compared.
     * @return the integer value that was greater than the other integer value argument.
     */
    private int max(int firstInt, int secondInt){ //rename parameters to reflect what we intend the values to represent
        return (firstInt > secondInt)? firstInt : secondInt;
    }

    /**
     * Returns items to be abandoned. Compiles list of items that are present in the potential items list but not in the actual items list.
     * Items must be stored in potential items and actual items.
     * @return itemsLeftBehind a list of items left behind, items are not a part of the list of optimal items.
     * @pre Items are stored in potentialItems and actualItems list was generated with findSelectedItmes method.
     */
    public void calculateItemsLeftBehind(){

        HashSet<CargoItem> potentialItemsSet = new HashSet<>();
        HashSet<CargoItem> actualItemSet = new HashSet<>();

        potentialItemsSet.addAll(potentialItems.getItemArrayList());// store potential items and actual items in sets
        actualItemSet.addAll(actualItems.getItemArrayList());// store potential items and actual items in sets
        potentialItemsSet.removeAll(actualItemSet);// and find the difference of the actual from the potential. //The difference of sets A, B is the set whose elements belong to A but not to B.

        //todo make sure the the itemsleftbehind list is empty now. if it isn't empty it.
        for (CargoItem c: potentialItemsSet){
            itemsLeftBehind.addCargoItemToList(c);//store that difference in the itemsLeftBehind list.
        }

    }
}
