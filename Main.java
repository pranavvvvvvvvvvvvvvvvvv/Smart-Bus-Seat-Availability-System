import java.util.List;
import java.util.Scanner;

/**
 * Smart Bus Seat Availability System - console menu.
 * Run: javac *.java  then  java Main
 */
public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final BookingSystem system = new BookingSystem();

    public static void main(String[] args) {
        // Sample data so the demo works immediately
        system.registerBus("KA01", "Bengaluru - Hyderabad", "21:00", 20);
        system.registerBus("TS07", "Hyderabad - Vijayawada", "07:30", 16);

        while (true) {
            printMenu();
            int choice = readInt("Choose an option: ");
            switch (choice) {
                case 1 -> registerBus();
                case 2 -> listBuses();
                case 3 -> viewSeats();
                case 4 -> registerPassenger();
                case 5 -> bookSeat();
                case 6 -> cancelBooking();
                case 7 -> viewMyBookings();
                case 0 -> {
                    System.out.println("Thank you. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n===== SMART BUS SEAT AVAILABILITY SYSTEM =====");
        System.out.println("1. Register a bus");
        System.out.println("2. List all buses");
        System.out.println("3. View available seats of a bus");
        System.out.println("4. Register passenger");
        System.out.println("5. Book a seat");
        System.out.println("6. Cancel a booking");
        System.out.println("7. View my bookings");
        System.out.println("0. Exit");
    }

    private static void registerBus() {
        String id = readLine("Bus ID (e.g. KA05): ").toUpperCase();
        String route = readLine("Route (e.g. Chennai - Madurai): ");
        String time = readLine("Departure time (HH:MM): ");
        int seats = readInt("Total seats (4-60): ");
        System.out.println(system.registerBus(id, route, time, seats)
                ? "Bus registered." : "Could not register (duplicate ID or invalid details).");
    }

    private static void listBuses() {
        List<Bus> buses = system.getAllBuses();
        if (buses.isEmpty()) {
            System.out.println("No buses registered.");
            return;
        }
        buses.forEach(System.out::println);
    }

    private static void viewSeats() {
        Bus bus = system.getBus(readLine("Bus ID: ").toUpperCase());
        if (bus == null) System.out.println("Bus not found.");
        else bus.displaySeats();
    }

    private static void registerPassenger() {
        String name = readLine("Name: ");
        int age = readInt("Age: ");
        String phone = readLine("Phone (10 digits): ");
        Passenger p = system.registerPassenger(name, age, phone);
        if (p == null) System.out.println("Invalid details. Check name, age and 10-digit phone.");
        else System.out.println("Registered. Your Passenger ID is " + p.getPassengerId());
    }

    private static void bookSeat() {
        String busId = readLine("Bus ID: ").toUpperCase();
        Bus bus = system.getBus(busId);
        if (bus == null) {
            System.out.println("Bus not found.");
            return;
        }
        bus.displaySeats();
        String pid = readLine("Your Passenger ID: ").toUpperCase();
        if (system.getPassenger(pid) == null) {
            System.out.println("Passenger not found. Register first (option 4).");
            return;
        }
        int seat = readInt("Seat number: ");
        Booking b = system.bookSeat(busId, pid, seat);
        System.out.println(b == null
                ? "Booking failed. Seat is invalid or already taken."
                : "Booked! " + b);
    }

    private static void cancelBooking() {
        String id = readLine("Booking ID: ").toUpperCase();
        System.out.println(system.cancelBooking(id)
                ? "Booking cancelled. Seat is available again."
                : "Could not cancel (not found or already cancelled).");
    }

    private static void viewMyBookings() {
        String pid = readLine("Your Passenger ID: ").toUpperCase();
        List<Booking> list = system.getBookingsForPassenger(pid);
        if (list.isEmpty()) System.out.println("No bookings found.");
        else list.forEach(System.out::println);
    }

    // ---------- input helpers ----------
    private static String readLine(String prompt) {
        System.out.print(prompt);
        return in.nextLine().trim();
    }

    private static int readInt(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readLine(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
