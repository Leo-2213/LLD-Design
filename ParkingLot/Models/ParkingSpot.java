package LLDDesigns.ParkingLot.Models;

import LLDDesigns.ParkingLot.Enums.SpotType;

import java.util.concurrent.atomic.AtomicBoolean;

public class ParkingSpot {
    private final int spotId;
    private final SpotType spotType;
    private final int floorNo;
    private  Vehicle vehicle;


    private final AtomicBoolean isOccupied = new AtomicBoolean(false);

    public ParkingSpot( int spotId, SpotType spotType , int floorNo) {
        this.spotId = spotId;
        this.spotType = spotType;
        this.floorNo = floorNo;
    }

    public int getSpotId() {
        return spotId;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public boolean isOccupied(){
        return isOccupied.get();
    }

    public void removeVehicle() {
        this.vehicle = null;
        isOccupied.set(false);
    }

    public boolean canFitVehicle(Vehicle vehicle) {
        return !isOccupied.get() && vehicle.getSupportedSpots().contains(this.spotType);
    }

    public boolean tryAssignVehicle(Vehicle vehicle) {
        if(isOccupied.compareAndSet(false, true)){
            this.vehicle = vehicle;
            return true;
        }
      return false;
    }
}
