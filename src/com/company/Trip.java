package com.company;

/**
 * Created by julianareider on 4/10/17.
 */
public class Trip {
    private Plane planeForTrip;
    private double distance;
    private CargoListGenerator tripsListGenerator;
    private String itemsInPlaneAndTheirTotalValAndAbandonedItems;


    public Trip() {
        planeForTrip = new Plane();
        distance = 0.0;
        tripsListGenerator = new CargoListGenerator();
    }

    public Trip(Plane pft, double d) {
        planeForTrip = pft;
        distance = d;
        tripsListGenerator = new CargoListGenerator();
    }

    /**
     * Airplane will be loaded with the maximum value of cargo that is below the max
     * @pre there must be a plane stored int the planeForTrip variable. plane must have stored a max weight attribute (in Oz).
     *
     */
    public String loadAirplane(CargoItemList possibleItems){

        int maxWeight = planeForTrip.getMaxOzWeight();
        //get string of what to bring, what to leave behind, and total value of what we're bringing
        itemsInPlaneAndTheirTotalValAndAbandonedItems = tripsListGenerator.generateList(possibleItems, maxWeight);
        //grab the actual list generated, and stuff those things into the airplane
        planeForTrip.setItemsToTake(tripsListGenerator.getActualItems()); //take cargo selected by cargoListGenerator object and store in plane

        return "Airplane was loaded. \n" + itemsInPlaneAndTheirTotalValAndAbandonedItems;

    }

    public Plane getPlaneForTrip() {
        return planeForTrip;
    }

    public void setPlaneForTrip(Plane pft) {
        planeForTrip = pft;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double d) {
        distance = d;
    }

}
