package com.company;

import java.util.ArrayList;

/**
 * Created by julianareider on 4/10/17.
 */
public class CargoListGenerator {

    private CargoItemList potentialItems;
    private CargoItemList actualItems;
    private CargoItemList itemsLeftBehind;

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

    private int calculateMaxValue(){
        return 0;
    }

    private CargoItemList findSelectedItems(){
        return actualItems;
    }
    
    private int max(int first, int second){ //rename parameters to reflect what we intend the values to represent
        return 0;
    }

    //POSSIBLE INNER CLASS OF TRIP.


}
