package com.hadwin.airport;

public class Main {
    public static void main(String[] args) {
        Passenger passenger = new Passenger("123-45-6789", "John Smith", "UK");
        passenger.recordToSystem();

        Flight flight = new Flight("BA884", 100);
        flight.setOrigin("London");
        flight.setDestination("Bucharest");
        flight.sellTicket();
        flight.takeOff();
        flight.land();
    }
}
