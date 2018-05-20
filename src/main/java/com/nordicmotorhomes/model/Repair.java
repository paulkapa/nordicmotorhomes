package com.nordicmotorhomes.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

public class Repair {

    private int repairId;
    private int motorhomeId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    private String problem;
    private String solution;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;


    public Repair() {

    }

    public Repair(int repairId, int motorhomeId, Date startDate, String problem, String solution, Date endDate) {
        this.repairId = repairId;
        this.motorhomeId = motorhomeId;
        this.startDate = startDate;
        this.problem = problem;
        this.solution = solution;
        this.endDate = endDate;
    }

    public int getRepairId() {
        return repairId;
    }

    public void setRepairId(int repairId) {
        this.repairId = repairId;
    }

    public int getMotorhomeId() {
        return motorhomeId;
    }

    public void setMotorhomeId(int motorhomeId) {
        this.motorhomeId = motorhomeId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Repair{" +
                "repairId=" + repairId +
                ", motorhomeId=" + motorhomeId +
                ", startDate=" + startDate +
                ", problem='" + problem + '\'' +
                ", solution='" + solution + '\'' +
                ", endDate=" + endDate +
                '}';
    }
}
