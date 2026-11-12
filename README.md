# Smart Bus Seat Availability System

![Build and test](../../actions/workflows/ci.yml/badge.svg)

A Java console application that shows which bus seats are available before boarding, and lets passengers book and cancel seats.

## Problem statement

Passengers may find it difficult to identify available seats before boarding.

## Features (modules)

| Module | What it does |
|---|---|
| Bus Registration | Add a bus with its route, departure time and number of seats |
| Seat Management | View a live seat map; booked seats show as `[XX]` |
| Passenger Registration | Register a passenger (name, age, 10-digit phone) and get a Passenger ID |
| Booking | Book a free seat; double booking is rejected |
| Cancellation | Cancel a booking; the seat becomes available again |

## Project structure

| File | Purpose |
|---|---|
| `Main.java` | Console menu |
| `BookingSystem.java` | Business logic for all modules |
| `Bus.java` | Bus and seat availability |
| `Passenger.java` | Passenger details |
| `Booking.java` | A single seat reservation |
| `tests/BookingSystemTest.java` | Automated tests |
| `.github/workflows/ci.yml` | Builds and tests on every push |

## How to run

Requires JDK 17 or newer.

```bash
javac *.java
java Main
```

## How to run the tests

```bash
javac -d out *.java tests/BookingSystemTest.java
java -cp out BookingSystemTest
```

## Sample

```
Bus KA01 | Bengaluru - Hyderabad | 21:00
Available: 19 / 20
(XX = booked)

[01] [02]    [XX] [04]
[05] [06]    [07] [08]
```

## Future improvements

- Save data to a file so bookings survive restarts
- Graphical interface (Swing or JavaFX)
- Search buses by route

## License

MIT
