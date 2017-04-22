package com.company;
import java.io.FileNotFoundException;
import java.util.*;
//import java.io.IOException;
import java.io.File;
//import java.io.BufferedReader;
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

    public static void main(String[] args){
        //new scanner
        Scanner kb = new Scanner(System.in);

        //new plane and trip
        Plane p;
        Trip t;
        CargoItemList cargoItemList = new CargoItemList();

        //prompt user for plane information
        System.out.println("Please specify the type of plane you wish to fill: ");
        String typeOfPlane = kb.nextLine();

        System.out.println("Please specify the maximum weight capacity of the plane:");
        int maxWeight = kb.nextInt();

        //constructing the plane
        p = new Plane(typeOfPlane, maxWeight);

        //prompt user for trip information
        System.out.println("Please specify the distance you are flying: ");
        double dist = kb.nextDouble();

        //constructing the trip
        t = new Trip(p, dist);

        //boolean for the do-while loop
        boolean moreItems = false;


        //loop for user input. 1 to use the file reader, 2 to enter items by hand.
        System.out.println("Enter 1 to input a file name. Enter 2 to input cargo by hand.");
        int choice = kb.nextInt();
        if (choice == 1) {
            System.out.println("Please input the file name: ");
            String fileName = kb.next();

            Scanner sc = null;
            try {
                sc = new Scanner(new File(fileName));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            while (sc.hasNextLine()){
                String name = sc.nextLine();
                int weight = sc.nextInt();
                int val = sc.nextInt();
                CargoItem cargoItem = new CargoItem(weight, name, val);
                cargoItemList.addCargoItemToList(cargoItem);
            }
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
                    System.exit(0); //todo re-prompt
                }
                } while (moreItems);
        }

        System.out.println();
        System.out.println(t.loadAirplane(cargoItemList));
    }
}