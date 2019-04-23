package com.hadwin.airport;

public class Passenger {

    private String identifier;
    private String name;
    private String countryCode;

    public Passenger(String identifier, String name, String countryCode) {
        this.identifier = identifier;
        this.name = name;
        this.countryCode = countryCode;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }


    @Override
    public String toString() {
        return "Passenger " + getName() + " with identifier: " + getIdentifier() + " from " + getCountryCode();
    }

    public void recordToSystem(){
        System.out.println(this + " has been recorded to the company system");
    }
}
