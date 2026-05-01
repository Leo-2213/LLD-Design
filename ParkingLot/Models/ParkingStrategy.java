package LLDDesigns.ParkingLot.Models;

import java.util.List;
import java.util.Optional;

public interface ParkingStrategy {
         Optional <ParkingSpot>findParkingSpot (Vehicle vehicle, List<ParkingFloor> parkingFloors);
}
