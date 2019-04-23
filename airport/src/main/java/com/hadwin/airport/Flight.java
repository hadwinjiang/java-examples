package com.hadwin.airport;

public class Flight {

    private String flightNumber;
    private int places;
    private int passengers;
    private String origin;
    private String destination;
    private boolean flying;

    public Flight(String flightNumber, int places) {
        this.flightNumber = flightNumber;
        this.places = places;
        this.passengers = 0;
        this.flying = false;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public int getPassengers() {
        return passengers;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public boolean isFlying() {
        return flying;
    }

    @Override
    public String toString() {
        return "Flight " + getFlightNumber() + " from " + getOrigin() + " to " + getDestination();
    }

    public void sellTicket() {
        System.out.println("Ticket for " + this + " sold");
        passengers++;
    }

    public void takeOff() {
        System.out.println(this + " is taking off");
        flying = true;
    }

    public void land() {
        System.out.println(this + " is landing");
        flying = false;
    }

}
