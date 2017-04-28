package com.company;

import java.util.ArrayList;

/**
 * Created by julianareider on 4/10/17.
 * CargoItemList is a collection of cargo items.
 */
public class CargoItemList {
    //Attributes
    /** itemList represents the list of cargo items within the collection */
    private ArrayList<CargoItem> itemList;
    /** listName represents the name of the group of items */
    private String listName;

    //Constructors
    /**
     * Constructor that initializes the CargoItemList attributes.
     */
    public CargoItemList(){
        itemList = new ArrayList<>();
        listName = "";
    }

    /**
     * Constructor that initializes the CargoItemList attributes. Sets our itemList to the list parameter.
     * @param list the ArrayList of CargoItems that we wish to be in this CargoItemList
     */
    public CargoItemList(ArrayList<CargoItem> list){
        itemList = list;
        listName = "";
    }

    //getters

    /**
     * Retrieves the CargoItem stored at a particular index within our itemList.
     * @param index is the index of the desired CargoItem within our itemList ArrayList.
     * @return the CargoItem object located at index index within our intemList was returned
     */
    public CargoItem getItem(int index){
        return itemList.get(index);
    }

    /**
     * todo javadoc here
     * @return
     */
    public ArrayList<CargoItem> getItemList() {
        return itemList;
    }

    /**
     * Retrieves number of elements stored in the list.
     * @return the number of elements on the itemList was returned.
     */
    public int getSize(){
        return itemList.size();
    }

    //mutater methods

    /**
     * Adds an additional CargoItem object to the itemList.
     * @param newI the CargoItem object to be added to our collection of items.
     * @post newI was added to the itemList at the end of the arrayList.
     */
    public void addCargoItemToList(CargoItem newI){
        itemList.add(newI);
    }

    //Additional methods
    public void clearList(){
        itemList.clear();
    }

    /**
     * Retrieves information about all the items stored in our collection of CargoItems.
     * @return a string representing all the CargoItems currently stored in our list .
     */
    @Override
    public String toString() {
        String listOfItemsString ="";
        for (CargoItem cI: itemList){
            listOfItemsString += "\t" + cI.toString();
        }
        return listOfItemsString;
    }
}
