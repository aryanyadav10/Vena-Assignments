import java.util.*;

//Problem 13: Flight Booking Manager (Queue, Map)
//Use a Queue for booking requests and a Map for confirmed bookings.
//        •	Process bookings FIFO and update map with seat numbers.

public class q13 {
    // Queue to store booking requests in order
    private Queue<String> bookingRequests;

    // Map to store confirmed bookings: Passenger name -> Seat number
    private Map<String, String> confirmedBookings;

    // Seat counter to assign unique seat numbers
    private int seatCounter;

    public q13() {
        bookingRequests = new LinkedList<>();
        confirmedBookings = new HashMap<>();
        seatCounter = 1;
    }

    // Method to add a booking request
    public void addBookingRequest(String passengerName) {
        bookingRequests.add(passengerName);
        System.out.println("Booking request added for: " + passengerName);
    }

    // Method to process next booking in the queue
    public void processNextBooking() {
        if (bookingRequests.isEmpty()) {
            System.out.println("No pending booking requests.");
            return;
        }

        // Get next passenger from the queue
        String passenger = bookingRequests.poll();

        // Assign seat number (e.g., S1, S2, ...)
        String seatNumber = "S" + seatCounter;
        seatCounter++;

        // Add to confirmed bookings
        confirmedBookings.put(passenger, seatNumber);

        System.out.println("Booking confirmed for " + passenger + " with seat " + seatNumber);
    }

    // Method to print all confirmed bookings
    public void printConfirmedBookings() {
        if (confirmedBookings.isEmpty()) {
            System.out.println("No confirmed bookings yet.");
            return;
        }

        System.out.println("\nConfirmed Bookings:");
        for (String passenger : confirmedBookings.keySet()) {
            String seat = confirmedBookings.get(passenger);
            System.out.println(passenger + " → " + seat);
        }
    }

    // Main method to test the system
    public static void main(String[] args) {
        q13 manager = new q13();

        // Add booking requests
        manager.addBookingRequest("Alice");
        manager.addBookingRequest("Bob");
        manager.addBookingRequest("Charlie");

        // Process bookings
        manager.processNextBooking(); // Alice
        manager.processNextBooking(); // Bob

        // Show confirmed bookings
        manager.printConfirmedBookings();

        // Add and process one more
        manager.addBookingRequest("David");
        manager.processNextBooking(); // Charlie was already in queue
        manager.processNextBooking(); // David

        // Show final confirmed bookings
        manager.printConfirmedBookings();
    }
}
