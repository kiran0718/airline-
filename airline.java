import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Passenger class
class Passenger {
    private String name;
    private String passportNumber;

    public Passenger(String name, String passportNumber) {
        this.name = name;
        this.passportNumber = passportNumber;
    }

    public String getName() {
        return name;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    @Override
    public String toString() {
        return name + " (" + passportNumber + ")";
    }
}

// Flight class
class Flight {
    private String flightNumber;
    private String origin;
    private String destination;
    private int totalSeats;
    private int availableSeats;
    private List<Passenger> passengers;

    public Flight(String flightNumber, String origin, String destination, int totalSeats) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.passengers = new ArrayList<>();
    }

    public boolean bookSeat(Passenger passenger) {
        if (availableSeats > 0) {
            passengers.add(passenger);
            availableSeats--;
            return true;
        } else {
            return false;
        }
    }

    public boolean cancelSeat(Passenger passenger) {
        if (passengers.remove(passenger)) {
            availableSeats++;
            return true;
        } else {
            return false;
        }
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }
}

// ReservationSystem class
class ReservationSystem {
    private List<Flight> flights;

    public ReservationSystem() {
        this.flights = new ArrayList<>();
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public boolean bookFlight(String flightNumber, Passenger passenger) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight.bookSeat(passenger);
            }
        }
        return false;
    }

    public boolean cancelReservation(String flightNumber, Passenger passenger) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight.cancelSeat(passenger);
            }
        }
        return false;
    }

    public void displayFlights() {
        System.out.println("Available Flights:");
        for (Flight flight : flights) {
            System.out.println(flight.getFlightNumber() + ": " + flight.getOrigin() + " to " + flight.getDestination() +
                    ", Available Seats: " + flight.getAvailableSeats());
        }
        System.out.println();
    }

    public void displayPassengers(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                System.out.println("Passengers on Flight " + flightNumber + ":");
                for (Passenger passenger : flight.getPassengers()) {
                    System.out.println("- " + passenger);
                }
                return;
            }
        }
        System.out.println("Flight not found.");
    }
}

// Main class
public class AirlineReservationSystem {
    public static void main(String[] args) {
        ReservationSystem system = new ReservationSystem();

        // Adding some sample flights
        Flight flight1 = new Flight("F001", "New York", "Los Angeles", 50);
        Flight flight2 = new Flight("F002", "Chicago", "Miami", 40);
        Flight flight3 = new Flight("F003", "San Francisco", "Seattle", 30);

        system.addFlight(flight1);
        system.addFlight(flight2);
        system.addFlight(flight3);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("1. Book a Flight");
            System.out.println("2. Cancel Reservation");
            System.out.println("3. View Flights");
            System.out.println("4. View Passengers");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter Flight Number:");
                    String flightNumber = scanner.nextLine();
                    System.out.println("Enter Passenger Name:");
                    String passengerName = scanner.nextLine();
                    System.out.println("Enter Passport Number:");
                    String passportNumber = scanner.nextLine();

                    Passenger passenger = new Passenger(passengerName, passportNumber);
                    if (system.bookFlight(flightNumber, passenger)) {
                        System.out.println("Booking successful!");
                    } else {
                        System.out.println("Booking failed. No available seats.");
                    }
                    break;
                case 2:
                    System.out.println("Enter Flight Number:");
                    flightNumber = scanner.nextLine();
                    System.out.println("Enter Passenger Name:");
                    passengerName = scanner.nextLine();
                    System.out.println("Enter Passport Number:");
                    passportNumber = scanner.nextLine();

                    passenger = new Passenger(passengerName, passportNumber);
                    if (system.cancelReservation(flightNumber, passenger)) {
                        System.out.println("Cancellation successful!");
                    } else {
                        System.out.println("Cancellation failed. Passenger not found or flight not found.");
                    }
                    break;
                case 3:
                    system.displayFlights();
                    break;
                case 4:
                    System.out.println("Enter Flight Number:");
                    flightNumber = scanner.nextLine();
                    system.displayPassengers(flightNumber);
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
            System.out.println();
        }

        scanner.close();
    }
}
