package com.company;

import java.util.ArrayList;

/**
 * Created by julianareider on 4/10/17.
 */
public class CargoItemList {

    private ArrayList<CargoItem> itemList;
    private String listName;
    //standard arrayList methods
    public CargoItemList(){
        itemList = new ArrayList<>();
        listName = "";
    }
    //add another constructor
    public CargoItemList(ArrayList<CargoItem> list){
        itemList = list;
        listName = "";
    }

    public void addCargoItemToList(CargoItem newI){
        itemList.add(newI);
    }

    public CargoItem getItem(int index){
        return itemList.get(index);
    }

    /**
     * retreives number of elements stored in the list
     * @return
     */
    public int getSize(){
        return itemList.size();
    }

    @Override
    public String toString() {
        String listOfItemsString ="";
        for (CargoItem cI: itemList){
            listOfItemsString += cI.toString();
        }
        return listOfItemsString;
    }
}
