#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_SEATS 40
#define MAX_BUSES 5

typedef struct {
    char name[50];
    int isBooked;
} Passenger;

typedef struct {
    char busNumber[10];
    int availableSeats;
    Passenger seats[MAX_SEATS];
} Bus;

// Pre-registering a bus for demonstration
Bus buses[MAX_BUSES] = {
    {"B101", MAX_SEATS, {{"", 0}}}
};

// Reads a whole number safely. Asks again if the user types letters.
int readInt(const char *prompt) {
    int value;
    printf("%s", prompt);
    while (scanf("%d", &value) != 1) {
        int c;
        while ((c = getchar()) != '\n' && c != EOF) { }   // throw away the bad input
        if (c == EOF) exit(0);                            // input ended, stop the program
        printf("Please enter a valid number: ");
    }
    return value;
}

int loginSystem() {
    char user[20], pwd[20];
    printf("User: ");
    scanf("%19s", user);      // %19s stops long input from overflowing the array
    printf("Pass: ");
    scanf("%19s", pwd);
    // Default login: user=admin, pass=123
    return (strcmp(user, "admin") == 0 && strcmp(pwd, "123") == 0);
}

// Displays a visual grid of the seats
void displaySeats(int bIdx) {
    printf("\n--- Seat Layout (XX = Booked) ---\n");
    for (int i = 0; i < MAX_SEATS; i++) {
        if (buses[bIdx].seats[i].isBooked) {
            printf("[ XX ] ");
        } else {
            printf("[ %02d ] ", i + 1);
        }

        // Print 4 seats per row to simulate a bus layout
        if ((i + 1) % 4 == 0) {
            printf("\n");
        }
    }
    printf("---------------------------------\n");
}

void bookSeat() {
    int seat;
    char name[50];
    int bIdx = 0; // Using first bus for demo

    if (buses[bIdx].availableSeats == 0) {
        printf("Bus is full!\n");
        return;
    }

    // Show the visual layout before asking for seat number
    displaySeats(bIdx);

    seat = readInt("Enter Seat (1-40): ");

    if (seat < 1 || seat > MAX_SEATS) {
        printf("Invalid seat number!\n");
        return;
    }
    if (buses[bIdx].seats[seat-1].isBooked) {
        printf("Seat already booked!\n");
        return;
    }

    printf("Passenger Name: ");
    scanf(" %49[^\n]", name);   // reads a full name with spaces, max 49 characters

    buses[bIdx].seats[seat-1].isBooked = 1;
    strcpy(buses[bIdx].seats[seat-1].name, name);
    buses[bIdx].availableSeats--;

    printf("Seat %d successfully Booked!\n", seat);
}

void cancelSeat() {
    int seat;
    int bIdx = 0;

    seat = readInt("Enter Seat to Cancel (1-40): ");

    if (seat < 1 || seat > MAX_SEATS) {
        printf("Invalid seat number!\n");
        return;
    }
    if (!buses[bIdx].seats[seat-1].isBooked) {
        printf("Seat is not booked yet!\n");
        return;
    }

    buses[bIdx].seats[seat-1].isBooked = 0;
    strcpy(buses[bIdx].seats[seat-1].name, "");
    buses[bIdx].availableSeats++;

    printf("Seat %d successfully Canceled!\n", seat);
}

void checkStatus() {
    int bIdx = 0;
    printf("\nBus: %s\n", buses[bIdx].busNumber);
    printf("Available Seats: %d\n", buses[bIdx].availableSeats);
    displaySeats(bIdx); // Show layout when checking status too
}

int main() {
    printf("--- Smart Bus System (Roll No: 26R21A0516) ---\n");
    if (!loginSystem()) {
        printf("Login Failed!\n");
        return 0;
    }

    int choice;
    while (1) {
        printf("\nMenu: 1.Book  2.Cancel  3.Check Status  4.Exit\n");
        choice = readInt("Choice: ");

        if (choice == 1) bookSeat();
        else if (choice == 2) cancelSeat();
        else if (choice == 3) checkStatus();
        else if (choice == 4) {
            printf("Exiting... Thank you!\n");
            break;
        }
        else printf("Invalid choice!\n");
    }
    return 0;
}
