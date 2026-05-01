package LLDDesigns.BookMyShow.model;

import LLDDesigns.BookMyShow.Enum.SeatType;

public class CalculatorFactory {

    public static double Calculate(SeatType key, Integer value) {
        double sum =  switch (key){
            case Recliner ->  CalculateForRecliner.calculate(value);
            case Regular ->  CalculateForRegular.calculate(value);
            case Prime ->   CalculateForPrime.calculate(value);
        };
        return sum;
    }
}
