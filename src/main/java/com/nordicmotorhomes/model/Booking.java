package com.nordicmotorhomes.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

public class Booking {

    private int id;
    private int mtrhmId;
    private int userId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    private int isCancelled;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date cancellationDate;
    private int ppd;
    private int extrasPrice;
    private int pickUpDistance;
    private int dropOffDistance;
    private int dropOffKmNr;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    public Booking(int id, int mtrhmId, int userId, Date startDate, int isCancelled, Date cancellationDate, int ppd, int extrasPrice, int pickUpDistance, int dropOffDistance, int dropOffKmNr, Date endDate) {
        this.id = id;
        this.mtrhmId = mtrhmId;
        this.userId = userId;
        this.startDate = startDate;
        this.isCancelled = isCancelled;
        this.cancellationDate = cancellationDate;
        this.ppd = ppd;
        this.extrasPrice = extrasPrice;
        this.pickUpDistance = pickUpDistance;
        this.dropOffDistance = dropOffDistance;
        this.dropOffKmNr = dropOffKmNr;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMtrhmId() {
        return mtrhmId;
    }

    public void setMtrhmId(int mtrhmId) {
        this.mtrhmId = mtrhmId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getIsCancelled() {
        return isCancelled;
    }

    public void setIsCancelled(int isCancelled) {
        this.isCancelled = isCancelled;
    }

    public Date getCancellationDate() {
        return cancellationDate;
    }

    public void setCancellationDate(Date cancellationDate) {
        this.cancellationDate = cancellationDate;
    }

    public int getPpd() {
        return ppd;
    }

    public void setPpd(int ppd) {
        this.ppd = ppd;
    }

    public int getExtrasPrice() {
        return extrasPrice;
    }

    public void setExtrasPrice(int extrasPrice) {
        this.extrasPrice = extrasPrice;
    }

    public int getPickUpDistance() {
        return pickUpDistance;
    }

    public void setPickUpDistance(int pickUpDistance) {
        this.pickUpDistance = pickUpDistance;
    }

    public int getDropOffDistance() {
        return dropOffDistance;
    }

    public void setDropOffDistance(int dropOffDistance) {
        this.dropOffDistance = dropOffDistance;
    }

    public int getDropOffKmNr() {
        return dropOffKmNr;
    }

    public void setDropOffKmNr(int dropOffKmNr) {
        this.dropOffKmNr = dropOffKmNr;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", mtrhmId=" + mtrhmId +
                ", userId=" + userId +
                ", startDate=" + startDate +
                ", isCancelled=" + isCancelled +
                ", cancellationDate=" + cancellationDate +
                ", ppd=" + ppd +
                ", extrasPrice=" + extrasPrice +
                ", pickUpDistance=" + pickUpDistance +
                ", dropOffDistance=" + dropOffDistance +
                ", dropOffKmNr=" + dropOffKmNr +
                ", endDate=" + endDate +
                '}';
    }
}
