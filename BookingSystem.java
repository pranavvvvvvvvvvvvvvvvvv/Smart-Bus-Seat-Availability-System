import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Holds all data and implements the business logic for every module:
 * Bus Registration, Seat Management, Passenger Registration, Booking, Cancellation.
 */
public class BookingSystem {
    private final Map<String, Bus> buses = new LinkedHashMap<>();
    private final Map<String, Passenger> passengers = new LinkedHashMap<>();
    private final Map<String, Booking> bookings = new LinkedHashMap<>();
    private int passengerCounter = 1;
    private int bookingCounter = 1;

    // ---------- Bus Registration ----------
    public boolean registerBus(String busId, String route, String time, int seats) {
        if (busId.isBlank() || seats < 4 || seats > 60 || buses.containsKey(busId)) return false;
        buses.put(busId, new Bus(busId, route, time, seats));
        return true;
    }

    public Bus getBus(String busId) { return buses.get(busId); }

    public List<Bus> getAllBuses() { return new ArrayList<>(buses.values()); }

    // ---------- Passenger Registration ----------
    public Passenger registerPassenger(String name, int age, String phone) {
        if (name.isBlank() || age < 1 || age > 120 || !phone.matches("\\d{10}")) return null;
        String id = String.format("P%03d", passengerCounter++);
        Passenger p = new Passenger(id, name, age, phone);
        passengers.put(id, p);
        return p;
    }

    public Passenger getPassenger(String id) { return passengers.get(id); }

    // ---------- Booking ----------
    /** Returns the new Booking, or null (reason printed by caller via the checks below). */
    public Booking bookSeat(String busId, String passengerId, int seatNo) {
        Bus bus = buses.get(busId);
        if (bus == null || !passengers.containsKey(passengerId)) return null;
        if (!bus.bookSeat(seatNo)) return null;
        String id = String.format("B%04d", bookingCounter++);
        Booking b = new Booking(id, busId, passengerId, seatNo);
        bookings.put(id, b);
        return b;
    }

    // ---------- Cancellation ----------
    public boolean cancelBooking(String bookingId) {
        Booking b = bookings.get(bookingId);
        if (b == null || !b.isActive()) return false;
        buses.get(b.getBusId()).releaseSeat(b.getSeatNo());
        b.cancel();
        return true;
    }

    public List<Booking> getBookingsForPassenger(String passengerId) {
        List<Booking> result = new ArrayList<>();
        for (Booking b : bookings.values()) {
            if (b.getPassengerId().equals(passengerId)) result.add(b);
        }
        return result;
    }

    public Booking getBooking(String id) { return bookings.get(id); }
}
