package com.logic.bankwiser.loans;

public class VehicleInformation {

     private final String TYPE_OF_FUEL;
     private final double MILEAGE;
     private final int MANUFACTURING_YEAR;

     public VehicleInformation(String typeOfFuel, double mileage, int manufactureYear) {
          this.TYPE_OF_FUEL = typeOfFuel;
          this.MILEAGE = mileage;
          this.MANUFACTURING_YEAR = manufactureYear;
     }

     public String getTypeOfFuel() {
          return TYPE_OF_FUEL;
     }

     public double getMileage() {
          return MILEAGE;
     }

     public int getManufacturingYear() {
          return MANUFACTURING_YEAR;
     }
}
