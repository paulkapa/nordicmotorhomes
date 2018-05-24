package com.nordicmotorhomes.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Repair {

    private int repairId;
    private int motorhomeId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    private String problem;
    private String solution;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;


    public Repair() {

    }

    public Repair(int repairId, int motorhomeId, LocalDate startDate, String problem, String solution, LocalDate endDate) {
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
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

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
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
