/**
 * Module: Bus Registration + Seat Management
 * Represents one bus and keeps track of which seats are taken.
 */
public class Bus {
    private final String busId;
    private final String route;
    private final String departureTime;
    private final int totalSeats;
    private final boolean[] booked; // index 0 = seat 1

    public Bus(String busId, String route, String departureTime, int totalSeats) {
        this.busId = busId;
        this.route = route;
        this.departureTime = departureTime;
        this.totalSeats = totalSeats;
        this.booked = new boolean[totalSeats];
    }

    public String getBusId() { return busId; }
    public String getRoute() { return route; }
    public String getDepartureTime() { return departureTime; }
    public int getTotalSeats() { return totalSeats; }

    public boolean isValidSeat(int seatNo) {
        return seatNo >= 1 && seatNo <= totalSeats;
    }

    public boolean isSeatAvailable(int seatNo) {
        return isValidSeat(seatNo) && !booked[seatNo - 1];
    }

    public boolean bookSeat(int seatNo) {
        if (!isSeatAvailable(seatNo)) return false;
        booked[seatNo - 1] = true;
        return true;
    }

    public boolean releaseSeat(int seatNo) {
        if (!isValidSeat(seatNo) || !booked[seatNo - 1]) return false;
        booked[seatNo - 1] = false;
        return true;
    }

    public int availableCount() {
        int count = 0;
        for (boolean b : booked) {
            if (!b) count++;
        }
        return count;
    }

    /** Prints the seat layout: 2 seats | aisle | 2 seats. Booked seats show as [XX]. */
    public void displaySeats() {
        System.out.println("\nBus " + busId + " | " + route + " | " + departureTime);
        System.out.println("Available: " + availableCount() + " / " + totalSeats);
        System.out.println("(XX = booked)\n");
        for (int i = 1; i <= totalSeats; i++) {
            System.out.print(booked[i - 1] ? "[XX]" : String.format("[%02d]", i));
            if (i % 4 == 2) System.out.print("    "); // aisle
            else if (i % 4 == 0 || i == totalSeats) System.out.println();
            else System.out.print(" ");
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return String.format("%-6s %-25s %-8s seats free: %d/%d",
                busId, route, departureTime, availableCount(), totalSeats);
    }
}
