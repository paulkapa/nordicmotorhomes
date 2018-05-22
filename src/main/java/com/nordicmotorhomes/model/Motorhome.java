package com.nordicmotorhomes.model;

public class Motorhome {

    private int id;
    private String type;
    private String brand;
    private String model;
    private int isAvailable;
    private int maxCapacity;
    private int fuelTankVolume;
    private int ppd;

    public Motorhome() {

    }

    public Motorhome(String type, String brand, String model, int isAvailable, int maxCapacity, int fuelTankVolume, int ppd) {
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.isAvailable = isAvailable;
        this.maxCapacity = maxCapacity;
        this.fuelTankVolume = fuelTankVolume;
        this.ppd = ppd;
    }

    public Motorhome(int id, String type, String brand, String model, int isAvailable, int maxCapacity, int fuelTankVolume, int ppd) {
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.isAvailable = isAvailable;
        this.maxCapacity = maxCapacity;
        this.fuelTankVolume = fuelTankVolume;
        this.ppd = ppd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(int isAvailable) {
        this.isAvailable = isAvailable;
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

    public String toString() {
        return "Motorhome{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", isAvailable=" + isAvailable +
                ", maxCapacity=" + maxCapacity +
                ", fuelTankVolume=" + fuelTankVolume +
                ", ppd=" + ppd +
                '}';
    }
}
