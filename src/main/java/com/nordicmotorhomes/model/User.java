package com.nordicmotorhomes.model;

public class User {

    private String fullName;
    private String cprNr;

    public User() {

    }

    public User(String fullName, String cprNr) {
        this.fullName = fullName;
        this.cprNr = cprNr;
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

    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", cprNr='" + cprNr + '\'' +
                '}';
    }
}
