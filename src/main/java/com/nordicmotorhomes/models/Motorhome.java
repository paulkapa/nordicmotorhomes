package com.nordicmotorhomes.models;

public class Motorhome {

    private String type;
    private String brand;
    private String model;
    private int maxCapacity;
    private int fuelTankVolume;
    private int ppd;

    public Motorhome() {

    }

    public Motorhome(String type, String brand, String model, int maxCapacity, int fuelTankVolume, int ppd) {
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.maxCapacity = maxCapacity;
        this.fuelTankVolume = fuelTankVolume;
        this.ppd = ppd;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getFuelTankVolume() {
        return fuelTankVolume;
    }

    public void setFuelTankVolume(int fuelTankVolume) {
        this.fuelTankVolume = fuelTankVolume;
    }

    public int getPpd() {
        return ppd;
    }

    public void setPpd(int ppd) {
        this.ppd = ppd;
    }

    @Override
    public String toString() {
        return "Motorhome{" +
                "type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", maxCapacity=" + maxCapacity +
                ", fuelTankVolume=" + fuelTankVolume +
                ", ppd=" + ppd +
                '}';
    }
}
