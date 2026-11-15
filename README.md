# Smart Bus Seat Availability System

![Build](../../actions/workflows/ci.yml/badge.svg)

A C console application that shows which bus seats are available before boarding, and lets passengers book and cancel seats.

## Problem statement

Passengers may find it difficult to identify available seats before boarding.

## Features

| Feature | What it does |
|---|---|
| Login | Simple admin login before the menu opens |
| Seat layout | Visual grid of all 40 seats; booked seats show as `XX` |
| Book a seat | Choose a free seat and enter the passenger name; double booking is rejected |
| Cancel a seat | Cancel a booked seat so it becomes available again |
| Check status | Shows the bus number, available seats and the seat layout |

Default login: user `admin`, password `123`.

## How to run

Requires `gcc` (on Windows, install MinGW-w64 or MSYS2).

```bash
gcc -Wall -Wextra -std=c11 -o bus_seat_system bus_seat_system.c
./bus_seat_system          # on Windows: bus_seat_system.exe
```

## Sample

```
--- Seat Layout (XX = Booked) ---
[ 01 ] [ 02 ] [ 03 ] [ 04 ]
[ XX ] [ 06 ] [ 07 ] [ 08 ]
```

## Concepts used

Structs, arrays, functions, string handling, input validation, `scanf` safety.

## Future improvements

- Support several buses with a bus-selection menu
- Save bookings to a file so they survive restarts
- Hide the password while typing

## License

MIT
