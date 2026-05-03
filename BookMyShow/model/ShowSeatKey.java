package LLDDesigns.BookMyShow.model;

import LLDDesigns.BookMyShow.Enum.SeatType;

import java.util.Objects;

/**
 * Identifies a seat for a specific show (type + id within that screen layout).
 */
public final class ShowSeatKey {
    private final SeatType seatType;
    private final int seatId;

    public ShowSeatKey(SeatType seatType, int seatId) {
        this.seatType = seatType;
        this.seatId = seatId;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public int getSeatId() {
        return seatId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ShowSeatKey that = (ShowSeatKey) o;
        return seatId == that.seatId && seatType == that.seatType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(seatType, seatId);
    }

    @Override
    public String toString() {
        return seatType + "-" + seatId;
    }
}
