package com.nordicmotorhomes.model;

public class User {

    private int id;
    private String fullName;
    private String cprNr;

    public User() {

    }

    public User(int id, String fullName, String cprNr) {
        this.id = id;
        this.fullName = fullName;
        this.cprNr = cprNr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCprNr() {
        return cprNr;
    }

    public void setCprNr(String cprNr) {
        this.cprNr = cprNr;
    }

    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", cprNr='" + cprNr + '\'' +
                '}';
    }
}
