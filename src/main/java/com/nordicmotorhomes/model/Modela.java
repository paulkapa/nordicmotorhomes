package com.nordicmotorhomes.model;

public class Modela {

    private int id;
    private String model;
    private int maxCapacity;
    private int fuelTankVolume;
    private int ppd;

    public Modela() {

    }

    public Modela(String model, int maxCapacity, int fuelTankVolume, int ppd) {
        this.model = model;
        this.maxCapacity = maxCapacity;
        this.fuelTankVolume = fuelTankVolume;
        this.ppd = ppd;
    }

    public Modela(int id, String model, int maxCapacity, int fuelTankVolume, int ppd) {
        this.id = id;
        this.model = model;
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

    public String toString() {
        return "Modela{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", maxCapacity=" + maxCapacity +
                ", fuelTankVolume=" + fuelTankVolume +
                ", ppd=" + ppd +
                '}';
    }
}
