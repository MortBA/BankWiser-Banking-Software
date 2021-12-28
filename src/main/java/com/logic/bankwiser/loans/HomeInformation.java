package com.logic.bankwiser.loans;

public class HomeInformation {

    private final String PROPERTY_ADDRESS;
    private final String PROPERTY_TYPE;
    private final double PROPERTY_SIZE;
    private final int PROPERTY_FLOORS;

    public HomeInformation (String propertyAddress, String propertyType, double propertySize, int propertyFloors){
        this.PROPERTY_ADDRESS = propertyAddress;
        this.PROPERTY_TYPE = propertyType;
        this.PROPERTY_SIZE = propertySize;
        this.PROPERTY_FLOORS = propertyFloors;
    }

    public String getPropertyAddress() {
        return PROPERTY_ADDRESS;
    }

    public String getPropertyType() {
        return PROPERTY_TYPE;
    }

    public double getPropertySize() {
        return PROPERTY_SIZE;
    }

    public int getPropertyFloors() {
        return PROPERTY_FLOORS;
    }
}
