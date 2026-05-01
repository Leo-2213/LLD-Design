package LLDDesigns.BookMyShow.Strategy;

import LLDDesigns.BookMyShow.Enum.SeatType;
import LLDDesigns.BookMyShow.model.CalculatorFactory;
import LLDDesigns.BookMyShow.model.ReclinerSeat;
import LLDDesigns.BookMyShow.model.Seat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentCalculator {
    public static double calculate(List<Seat> seats){
        double totalAmount = 0.0;
        Map<SeatType, Integer> seatTypeIntegerMap = new HashMap<>();

        for(Seat seat : seats){
            seatTypeIntegerMap.putIfAbsent(seat.getSeatType(), 0);
            seatTypeIntegerMap.put(seat.getSeatType(), seatTypeIntegerMap.get(seat.getSeatType())+1);
        }

        for(Map.Entry<SeatType, Integer> seatTypeIntegerEntry: seatTypeIntegerMap.entrySet()){
            totalAmount += CalculatorFactory.Calculate(seatTypeIntegerEntry.getKey(), seatTypeIntegerEntry.getValue());
        }
        return totalAmount;
    }
}
