package LLDDesigns.ParkingLot;

public class ParkingLot {
    private ParkingController parkingController;

    private ParkingLot(){};

    public static ParkingLot getInstance(){
        return Loader.PARKING_LOT;
    }
    public void setParkingController(ParkingController parkingController){
        this.parkingController = parkingController;
    }
    public ParkingController getParkingController(){
        return parkingController;
    }
    private static class Loader{
        private static final ParkingLot PARKING_LOT = new ParkingLot();
    }
}
