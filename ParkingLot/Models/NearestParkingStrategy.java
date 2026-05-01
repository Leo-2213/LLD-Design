package LLDDesigns.ParkingLot.Models;

import java.util.List;
import java.util.Optional;

public class NearestParkingStrategy implements ParkingStrategy{
    @Override
    public Optional<ParkingSpot> findParkingSpot(Vehicle vehicle, List<ParkingFloor> parkingFloors) {
        return parkingFloors.stream()
                .flatMap(floor -> floor.getParkingSpotsMap().values().stream().flatMap(List::stream))
                .filter(parkingSpot -> parkingSpot.canFitVehicle(vehicle))
                .filter(parkingSpot -> parkingSpot.tryAssignVehicle(vehicle))
                .findFirst();
    }
}
