package LLDDesigns.ParkingLot.Models;

import LLDDesigns.ParkingLot.Enums.SpotType;

import java.util.*;

public class ParkingFloor {
    private final int floorNo;
    private final Map<SpotType, List<ParkingSpot>> parkingSpotsMap;

    public ParkingFloor(int floorNo, Map<SpotType,List<ParkingSpot>>parkingSpotsMap) {
        this.floorNo = floorNo;
        this.parkingSpotsMap = parkingSpotsMap;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public Map<SpotType, List<ParkingSpot>> getParkingSpotsMap() {
        return Collections.unmodifiableMap(parkingSpotsMap);
    }
    public static class Builder{
        private final int floorNo;
        private final  Map<SpotType, Integer> spotsTypeCountMap = new HashMap<>();

        public Builder(int floorNo) {
            this.floorNo = floorNo;
        }

        public Builder addSpot(SpotType spotType, int count ){
            if(count <= 0){
                System.out.println(" Spots should be > 0");
                return null;
            }
            spotsTypeCountMap.put(spotType, count);
            return this;
        }

        public ParkingFloor build(){
            if(spotsTypeCountMap.isEmpty()){
                System.out.println("Floor should have at least 1 parking spot");
                return null;
            }

            Map<SpotType, List<ParkingSpot>> parkingFloor = new HashMap<>();

            for(Map.Entry<SpotType, Integer> spotCount : spotsTypeCountMap.entrySet()){
                SpotType tempSpotType = spotCount.getKey();
                int count = spotCount.getValue();
                List<ParkingSpot> parkingSpots = new ArrayList<>();
                for(int i = 1; i <= count; i++){
                    parkingSpots.add(new ParkingSpot(i, tempSpotType, floorNo));
                }
                parkingFloor.put(tempSpotType, parkingSpots);
            }

            return new ParkingFloor(floorNo,parkingFloor);

        }
    }
}
