package LLDDesigns.ParkingLot.Models;

import LLDDesigns.ParkingLot.Enums.SpotType;
import LLDDesigns.ParkingLot.Enums.VehicleType;

import java.util.ArrayList;
import java.util.List;

public class Bike extends Vehicle{
    private final List<SpotType> supportedSpots =  new ArrayList<>(List.of(SpotType.SMALL, SpotType.MEDIUM));

    public Bike(String numberPlate) {
        super(numberPlate, VehicleType.BIKE);
    }

    @Override
    public List<SpotType> getSupportedSpots(){
        return supportedSpots;
    }
}
