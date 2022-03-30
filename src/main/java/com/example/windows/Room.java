package com.example.windows;

public class Room {
    String RoomNumber;
    String RoomType;
    String BedType;
    Double Rate;
    String Status;

    public Room(String roomNumber, String roomType, String bedType, Double rate, String status) {
        RoomNumber = roomNumber;
        RoomType = roomType;
        BedType = bedType;
        Rate = rate;
        Status = status;
    }

    public String getRoomNumber() {
        return RoomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        RoomNumber = roomNumber;
    }

    public String getRoomType() {
        return RoomType;
    }

    public void setRoomType(String roomType) {
        RoomType = roomType;
    }

    public String getBedType() {
        return BedType;
    }

    public void setBedType(String bedType) {
        BedType = bedType;
    }

    public Double getRate() {
        return Rate;
    }

    public void setRate(Double rate) {
        Rate = rate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
