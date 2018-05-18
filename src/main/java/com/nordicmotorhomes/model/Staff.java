package com.nordicmotorhomes.model;

public class Staff {

    private int id;
    private String fullName;
    private String function;
    private String username;
    private String password;

    public Staff() {

    }

    public Staff(int id, String fullName, String function, String username, String password) {
        this.id = id;
        this.fullName = fullName;
        this.function = function;
        this.username = username;
        this.password = password;
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

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", function='" + function + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
