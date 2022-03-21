package com.example.groupexercisebooking;

public class ModelBookingsData {

    private final String regNo,date,numOfSessions,status,id;

    public ModelBookingsData(String id, String regNo, String date, String numOfSessions, String status) {
        this.id = id;
        this.regNo = regNo;
        this.date = date;
        this.numOfSessions = numOfSessions;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getRegNo() {
        return regNo;
    }

    public String getDate() {
        return date;
    }

    public String getNumOfSessions() {
        return numOfSessions;
    }

    public String getStatus() {
        return status;
    }
}
