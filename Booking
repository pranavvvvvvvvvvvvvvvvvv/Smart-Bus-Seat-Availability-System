/**
 * Module: Booking (one confirmed seat reservation)
 */
public class Booking {
    private final String bookingId;
    private final String busId;
    private final String passengerId;
    private final int seatNo;
    private boolean active = true;

    public Booking(String bookingId, String busId, String passengerId, int seatNo) {
        this.bookingId = bookingId;
        this.busId = busId;
        this.passengerId = passengerId;
        this.seatNo = seatNo;
    }

    public String getBookingId() { return bookingId; }
    public String getBusId() { return busId; }
    public String getPassengerId() { return passengerId; }
    public int getSeatNo() { return seatNo; }
    public boolean isActive() { return active; }
    public void cancel() { active = false; }

    @Override
    public String toString() {
        return bookingId + " | Bus " + busId + " | Seat " + seatNo
                + " | Passenger " + passengerId + " | " + (active ? "CONFIRMED" : "CANCELLED");
    }
}
