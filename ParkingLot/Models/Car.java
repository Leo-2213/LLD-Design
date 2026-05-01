package LLDDesigns.ParkingLot.Models;

import LLDDesigns.ParkingLot.Enums.SpotType;
import LLDDesigns.ParkingLot.Enums.VehicleType;

import java.util.ArrayList;
import java.util.List;

public class Car extends Vehicle{
    private final List<SpotType> supportedSpots = new ArrayList<>(List.of(SpotType.MEDIUM));

    public Car(String numberPlate){
        super(numberPlate, VehicleType.CAR);
    }

    @Override
    public List<SpotType> getSupportedSpots(){
        return supportedSpots;
    }
}
