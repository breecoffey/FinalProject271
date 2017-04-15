package com.company;

/**
 * Created by julianareider on 4/10/17.
 */
public class Trip {
    private Plane planeForTrip;
    private double distance;
    private CargoListGenerator tripsListGenerator;
    //todo constructors


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
     * @pre there must be a plane stored int the planeForTrip variable. Weight must be in Oz
     */
    public void loadAirplane(CargoItemList possibleItems){

        int maxWeight = 10; //call calculateCapactiy
        int maxVal = tripsListGenerator.generateList(possibleItems, maxWeight);
        //creates Cargo List Generator, calls it's methods.
        //planeForTrip.setItemsToTake(maxVal);
        //take returned list and store in plane
    }

    public double calculateCapacity (){
        return 0.0;
    } //todo write note about this method- it's a symbolic one.... or we could just have a setter

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
