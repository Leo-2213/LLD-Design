package LLDDesigns.BookMyShow.model;

import LLDDesigns.BookMyShow.Enum.BookingStage;
import LLDDesigns.BookMyShow.Enum.SeatType;

public abstract class Seat {
    private final int seatId;
    private final SeatType seatType;
    private BookingStage bookingStage;
    /**
     * When a seat is held (Locked), we attach a TTL so locks auto-expire.
     * This prevents permanent locking if a user abandons payment.
     */
    private long lockUntilEpochMs = 0L;
    /**
     * Which booking currently holds this seat (only meaningful while Locked).
     * Used to ensure one booking cannot confirm/release another booking's hold.
     */
    private String lockedByBookingId = null;

    public int getSeatId() {
        return this.seatId;
    }

    public void setBookingStage(BookingStage bookingStage) {
        this.bookingStage = bookingStage;
    }

    public long getLockUntilEpochMs() {
        return lockUntilEpochMs;
    }

    public String getLockedByBookingId() {
        return lockedByBookingId;
    }

    /**
     * Acquire/refresh a hold on this seat until the given timestamp.
     * Caller must already hold the seat-level lock (see {@link Show#getSeatLock(ShowSeatKey)}).
     */
    public void lockForBooking(String bookingId, long lockUntilEpochMs) {
        this.bookingStage = BookingStage.Locked;
        this.lockedByBookingId = bookingId;
        this.lockUntilEpochMs = lockUntilEpochMs;
    }

    /**
     * Release a hold (used on expiry or cancellation).
     * Caller must already hold the seat-level lock.
     */
    public void releaseLock() {
        this.bookingStage = BookingStage.Available;
        this.lockedByBookingId = null;
        this.lockUntilEpochMs = 0L;
    }

    /**
     * If a seat is Locked and the TTL has passed, it is automatically released.
     * Caller must already hold the seat-level lock.
     */
    public void expireLockIfNeeded(long nowEpochMs) {
        if (this.bookingStage == BookingStage.Locked && nowEpochMs > this.lockUntilEpochMs) {
            releaseLock();
        }
    }

    /**
     * Mark seat as booked permanently after successful payment.
     * Caller must already hold the seat-level lock.
     */
    public void markBooked() {
        this.bookingStage = BookingStage.Booked;
        this.lockedByBookingId = null;
        this.lockUntilEpochMs = 0L;
    }

    public SeatType getSeatType() {
        return this.seatType;
    }
    public BookingStage getBookingStage(){
        return this.bookingStage;
    }

    public Seat(int seatId, SeatType seatType) {
        this.seatId = seatId;
        this.seatType = seatType;
        this.bookingStage = BookingStage.Available;
    }

    @Override
    public String toString() {
        return seatType + "-" + seatId;
    }

}
