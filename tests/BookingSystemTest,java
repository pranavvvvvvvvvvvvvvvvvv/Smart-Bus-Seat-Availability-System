/**
 * Simple automated tests (no libraries needed).
 * Exits with code 1 if any check fails, so GitHub Actions marks the build red.
 */
public class BookingSystemTest {
    private static int failures = 0;

    private static void check(String name, boolean condition) {
        System.out.println((condition ? "PASS  " : "FAIL  ") + name);
        if (!condition) failures++;
    }

    public static void main(String[] args) {
        BookingSystem s = new BookingSystem();

        // Bus registration
        check("register bus", s.registerBus("T1", "A - B", "10:00", 20));
        check("reject duplicate bus", !s.registerBus("T1", "A - B", "10:00", 20));
        check("reject too few seats", !s.registerBus("T2", "A - B", "10:00", 2));

        // Passenger registration
        Passenger p = s.registerPassenger("Ravi", 19, "9876543210");
        check("register passenger", p != null && p.getPassengerId().equals("P001"));
        check("reject bad phone", s.registerPassenger("X", 20, "123") == null);
        check("reject bad age", s.registerPassenger("X", 0, "9876543210") == null);

        // Booking
        Booking b = s.bookSeat("T1", "P001", 5);
        check("book seat", b != null && b.isActive());
        check("seat now unavailable", !s.getBus("T1").isSeatAvailable(5));
        check("available count drops", s.getBus("T1").availableCount() == 19);
        check("reject double booking", s.bookSeat("T1", "P001", 5) == null);
        check("reject invalid seat", s.bookSeat("T1", "P001", 99) == null);
        check("reject unknown passenger", s.bookSeat("T1", "P999", 6) == null);

        // Cancellation
        check("cancel booking", s.cancelBooking(b.getBookingId()));
        check("seat free after cancel", s.getBus("T1").isSeatAvailable(5));
        check("reject second cancel", !s.cancelBooking(b.getBookingId()));
        check("rebook after cancel", s.bookSeat("T1", "P001", 5) != null);

        System.out.println(failures == 0 ? "\nAll tests passed." : "\n" + failures + " test(s) FAILED.");
        if (failures > 0) System.exit(1);
    }
}
