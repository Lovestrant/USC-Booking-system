package com.example.groupexercisebooking;

public class ModelRatingData {
    private final String regNo,date,session,id;
    private final String rating;

    public ModelRatingData(String id, String regNo, String date, String session, String rating) {
        this.regNo = regNo;
        this.date = date;
        this.session = session;
        this.id = id;
        this.rating = rating;
    }

    public String getRegNo() {
        return regNo;
    }

    public String getDate() {
        return date;
    }

    public String getSession() {
        return session;
    }

    public String getId() {
        return id;
    }

    public String getRating() {
        return rating;
    }
}
