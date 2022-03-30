package com.example.windows;

import java.time.LocalDate;
import java.util.Date;

public class Occupancy {

    String OccupancyNumber;
    String ProcessedBy;
    LocalDate DateOccupied;
    String ProcessedFor;
    String RoomOccupied;
    Double RateApplied;
    Double PhoneUse;

    public Occupancy(String occupancyNumber, String processedBy, LocalDate dateOccupied, String processedFor, String roomOccupied, Double rateApplied, Double phoneUse) {
        OccupancyNumber = occupancyNumber;
        ProcessedBy = processedBy;
        DateOccupied = dateOccupied;
        ProcessedFor = processedFor;
        RoomOccupied = roomOccupied;
        RateApplied = rateApplied;
        PhoneUse = phoneUse;
    }

    public String getOccupancyNumber() {
        return OccupancyNumber;
    }

    public void setOccupancyNumber(String occupancyNumber) {
        OccupancyNumber = occupancyNumber;
    }

    public String getProcessedBy() {
        return ProcessedBy;
    }

    public void setProcessedBy(String processedBy) {
        ProcessedBy = processedBy;
    }

    public LocalDate getDateOccupied() {
        return DateOccupied;
    }

    public void setDateOccupied(LocalDate dateOccupied) {
        DateOccupied = dateOccupied;
    }

    public String getProcessedFor() {
        return ProcessedFor;
    }

    public void setProcessedFor(String processedFor) {
        ProcessedFor = processedFor;
    }

    public String getRoomOccupied() {
        return RoomOccupied;
    }

    public void setRoomOccupied(String roomOccupied) {
        RoomOccupied = roomOccupied;
    }

    public Double getRateApplied() {
        return RateApplied;
    }

    public void setRateApplied(Double rateApplied) {
        RateApplied = rateApplied;
    }

    public Double getPhoneUse() {
        return PhoneUse;
    }

    public void setPhoneUse(Double phoneUse) {
        PhoneUse = phoneUse;
    }
}
