class Flight:
    def __init__(self, flight_number, origin, destination, seats):
        self.flight_number = flight_number
        self.origin = origin
        self.destination = destination
        self.seats = seats
        self.available_seats = seats
        self.passengers = []

    def book_seat(self, passenger):
        if self.available_seats > 0:
            self.passengers.append(passenger)
            self.available_seats -= 1
            return True
        else:
            return False

    def cancel_seat(self, passenger):
        if passenger in self.passengers:
            self.passengers.remove(passenger)
            self.available_seats += 1
            return True
        else:
            return False

class Passenger:
    def __init__(self, name, passport_number):
        self.name = name
        self.passport_number = passport_number

class ReservationSystem:
    def __init__(self):
        self.flights = []
        self.passengers = []

    def add_flight(self, flight):
        self.flights.append(flight)

    def add_passenger(self, passenger):
        self.passengers.append(passenger)

    def book_flight(self, flight_number, passenger):
        for flight in self.flights:
            if flight.flight_number == flight_number:
                if flight.book_seat(passenger):
                    return "Booking successful!"
                else:
                    return "No available seats."
        return "Flight not found."

    def cancel_reservation(self, flight_number, passenger):
        for flight in self.flights:
            if flight.flight_number == flight_number:
                if flight.cancel_seat(passenger):
                    return "Cancellation successful!"
                else:
                    return "Passenger not found."
        return "Flight not found."

    def view_flights(self):
        for flight in self.flights:
            print(f"Flight {flight.flight_number}: {flight.origin} to {flight.destination}, Available seats: {flight.available_seats}")

    def view_passengers(self, flight_number):
        for flight in self.flights:
            if flight.flight_number == flight_number:
                print(f"Passengers on flight {flight_number}:")
                for passenger in flight.passengers:
                    print(f"{passenger.name} ({passenger.passport_number})")
                return
        print("Flight not found.")

# Example usage
system = ReservationSystem()

# Adding flights
flight1 = Flight("A123", "New York", "Los Angeles", 5)
flight2 = Flight("B456", "Boston", "Miami", 3)
system.add_flight(flight1)
system.add_flight(flight2)

# Adding passengers
passenger1 = Passenger("John Doe", "P123456")
passenger2 = Passenger("Jane Smith", "P789101")
system.add_passenger(passenger1)
system.add_passenger(passenger2)

# Booking flights
print(system.book_flight("A123", passenger1))  # Booking successful!
print(system.book_flight("A123", passenger2))  # Booking successful!

# Viewing flights
system.view_flights()

# Viewing passengers on a flight
system.view_passengers("A123")

# Cancelling reservation
print(system.cancel_reservation("A123", passenger1))  # Cancellation successful!

# Viewing passengers on a flight after cancellation
system.view_passengers("A123")
