package LLDDesigns.ParkingLot.Models;

import LLDDesigns.ParkingLot.Enums.SpotType;
import LLDDesigns.ParkingLot.Enums.VehicleType;

import java.util.List;

public abstract class Vehicle {
    private final String numberPlate;
    private final VehicleType vehicleType;
    public Vehicle(String numberPlate, VehicleType vehicleType){
        this.numberPlate = numberPlate;
        this.vehicleType = vehicleType;
    }

    public String getNumberPlate(){
        return numberPlate;
    }
    public VehicleType getVehicleType(){
        return vehicleType;
    }
    public abstract List<SpotType> getSupportedSpots();
}
