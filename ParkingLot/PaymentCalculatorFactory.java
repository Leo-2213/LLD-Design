package LLDDesigns.ParkingLot;

import LLDDesigns.ParkingLot.Enums.VehicleType;
import LLDDesigns.ParkingLot.Models.Vehicle;

public class PaymentCalculatorFactory {
    public static PaymentCalculator getPaymentCalculator(VehicleType vehicleType){
        return switch (vehicleType){
            case BIKE ->  new BikePaymentCalculator();
            case CAR -> new CarPaymentCalculator();
        };
    }
}
