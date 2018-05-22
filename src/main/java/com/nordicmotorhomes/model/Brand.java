package com.nordicmotorhomes.model;

public class Brand {

    private int id;
    private String brand;

    public Brand() {

    }

    public Brand(String brand) {
        this.brand = brand;
    }

    public Brand(int id, String brand) {
        this.id = id;
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                '}';
    }
}
