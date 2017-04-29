package com.company;

/**
 * Created by julianareider on 4/10/17.
 * Represents a single trip of a cargo aircraft.
 */
public class Trip {
    /** planeForTrip is the aircraft object that will be making the cargo trip */
    private Plane planeForTrip;
    /** the generator that puts together a list of ideal cargo items to transport on this trip (maximizes value, within weight limit) */
    private CargoListGenerator tripsListGenerator;
    /** A string with essential information about list of cargo Items, abandoned items and the plane for the trip*/
    private String cargoAndPlaneInfoString;

    //constructors
    /**
     * Constructor that initialize all the attributes of this Trip object.
     */
    public Trip() {
        planeForTrip = new Plane();
        tripsListGenerator = new CargoListGenerator();
    }

    /**
     * Constructor that initialize all the attributes of this Trip object.
     * Sets planeForTrip and distance to relevant parameters.
     * @param pft Plane object to be the plane for the trip
     */
    public Trip(Plane pft) {
        planeForTrip = pft;
        tripsListGenerator = new CargoListGenerator();
    }

    /**
     * Airplane will be loaded with the maximum value of cargo that is below the max cargo weight
     * @return a string stating that the airplane was properly loaded was returned.
     * @pre there must be a plane stored int the planeForTrip variable. plane must have stored a max weight attribute (in Oz).
     */
    public String loadAirplane(CargoItemList possibleItems){

        int maxWeight = planeForTrip.getMaxOzWeight();
        //get string of what to bring, what to leave behind, and total value of what we're bringing
        cargoAndPlaneInfoString = tripsListGenerator.generateList(possibleItems, maxWeight);
        //grab the actual list generated, and stuff those things into the airplane
        planeForTrip.setItemsToTake(tripsListGenerator.getActualItems()); //take cargo selected by cargoListGenerator object and store in plane

        return "Airplane was loaded. \n\n" +
                "Here are the details of your trip: \n\n" +
                "Type of aircraft: " + planeForTrip.getTypeOfPlane() +
                "\nWeight Limit: " + planeForTrip.getMaxOzWeight() + " ounces " +
                "\n" + cargoAndPlaneInfoString;

    }

    public Plane getPlaneForTrip(){//for testing purposes
        return planeForTrip;
    }

    public CargoListGenerator getCargoListGenerator(){ //for testing purposes only
        return tripsListGenerator;
    }

}
