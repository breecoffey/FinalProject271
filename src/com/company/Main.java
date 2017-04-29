package com.company;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;
public class Main {

    public static void main(String[] args){
        //new scanner
        Scanner kb = new Scanner(System.in);

        //new plane and trip
        Plane userPlane;
        Trip trip;
        CargoItemList cargoItemList = new CargoItemList();

        //prompt user for plane information
        System.out.println("Please specify the type of plane you wish to fill: ");
        String typeOfPlane = kb.nextLine();

        int maxWeight = 0;
        while (maxWeight <= 0){
            System.out.println("Please specify the maximum weight capacity of the plane:");
            maxWeight = kb.nextInt();
            if (maxWeight <= 0) {
                System.out.println("Max weight cannot be 0 or less. Try again.");
            }
        }

        //constructing the plane

            userPlane = new Plane(typeOfPlane, maxWeight);
            //constructing the trip
            trip = new Trip(userPlane);




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

            while (sc.hasNextLine()) {
                String name = sc.nextLine();
                int weight = sc.nextInt();
                int val = sc.nextInt();
                try {
                    CargoItem cargoItem = new CargoItem(weight, name, val);
                    cargoItemList.addCargoItemToList(cargoItem);
                } catch (InvalidInputException e) {
                    System.err.println("InvalidInputException: " + e.getMessage());
                }
                if (sc.hasNextLine()){
                    sc.nextLine();}

            }
        } else if (choice == 2) {
            do {
                //user prompts
                System.out.println("Please input the name of the cargo item.");
                kb.nextLine();
                String name = kb.nextLine();
                System.out.println("Please input the weight (in oz) of the cargo item.");
                int weight = kb.nextInt();
                System.out.println("Please input the dollar value of the cargo item.");
                int val = kb.nextInt();

                //constructor and addition to cargo item list.
                try {
                    CargoItem cargoItem = new CargoItem(weight, name, val);
                    cargoItemList.addCargoItemToList(cargoItem);
                }
                catch (InvalidInputException e) {
                    System.err.println("InvalidInputException: " + e.getMessage());
                }
                System.out.println("Enter 'Y' to add another item. Enter 'DONE' when you are done");
                String addAnother = kb.next();
                if (addAnother.equalsIgnoreCase("y")) {
                    moreItems = true;
                } else if (addAnother.equalsIgnoreCase("done")) {
                    moreItems = false;
                } 
                else {
                    System.out.println("Not a valid choice.");
                    System.exit(0);
                }
                } while (moreItems);
        }

        System.out.println();
        System.out.println(trip.loadAirplane(cargoItemList));
    }
}