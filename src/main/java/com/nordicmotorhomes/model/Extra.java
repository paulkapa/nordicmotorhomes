package com.nordicmotorhomes.model;

public class Extra {

    private int id;
    private String extra;
    private int price;

    public Extra() {

    }

    public Extra(String extra, int price) {
        this.extra = extra;
        this.price = price;
    }

    public Extra(int id, String extra, int price) {
        this.id = id;
        this.extra = extra;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String toString() {
        return "Extra{" +
                "id=" + id +
                ", extra='" + extra + '\'' +
                ", price=" + price +
                '}';
    }
}
