package com.company;
import java.util.*;
public class Main {

    //todo List of features we might want to add to our program:
    /** This is for both of use to use and refer to :D
     * 1) we should through an exception if an item doesn't have a weight and a value.
     *      we could maybe do this when the possibleItems list is created, cause it's fromt that moment on that both values are necessary.
     * 2) at some point we should clean up the testclass so that it can create ojects at the begining of the class rather than within each function
     *      (I probably should have done this when I first greated the testclass... sorry ! haha
     * 3) Format values as money for print statments. Assure values are in dollars? cents? figure that out
     * 4)
     * 5)
     */

    public static void main(String[] args) {
        /*
        boolean moreItems = false;
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter 1 to input a file name. Enter 2 to input cargo by hand.");
        int choice = kb.nextInt();
        CargoItemList cargoItemList;
        if (choice == 1) {
            //todo file reader
        } else if (choice == 2) {
            do {
                //user prompts
                System.out.println("Please input the name of the cargo item.");
                String name = kb.next();
                System.out.println("Please input the weight (in oz) of the cargo item.");
                int weight = kb.nextInt();
                System.out.println("Please input the value of the cargo item.");
                int val = kb.nextInt();

                //constructor and addition to cargo item list.
                CargoItem cargoItem = new CargoItem(weight, name, val);
                cargoItemList = new CargoItemList();
                cargoItemList.addCargoItemToList(cargoItem);

                System.out.println("Enter 'Y' to add another item. Enter 'DONE' when you are done");
                String addAnother = kb.next();
                if (addAnother.equalsIgnoreCase("y")) {
                    moreItems = true;
                } else if (addAnother.equalsIgnoreCase("done")) {
                    moreItems = false;
                } 
                else {
                    System.out.println("Not a valid choice.");
                    System.exit(0); //todo re-prompt?
                }
                } while (moreItems);

                System.out.println("Please enter the maximum weight capacity: ");
                int maxWeight = kb.nextInt();

                CargoListGenerator genList = new CargoListGenerator(cargoItemList);
                System.out.println("The maximum value from these items is: ");
                System.out.println(genList.calculateMaxValue(maxWeight));
                System.out.println("The items you should bring are: ");
                System.out.println(genList.generateList(cargoItemList, maxWeight));
        }
        */
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
        System.out.println(testGenerator2.calculateMaxValue(7)); //size would be four, second parameter
    }


}
