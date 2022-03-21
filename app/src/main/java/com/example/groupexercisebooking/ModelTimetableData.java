package com.example.groupexercisebooking;

public class ModelTimetableData {

    private final String dateAndDay, exercise1, exercise2, exercise3,status,id;


    public ModelTimetableData(String id,String dateAndDay, String exercise1, String exercise2,String exercise3,String status) {
        this.dateAndDay = dateAndDay;
        this.exercise1 = exercise1;
        this.exercise2 = exercise2;
        this.exercise3 = exercise3;
        this.id = id;
        this.status = status;
    }

    public String getDateAndDay() {
        return dateAndDay;
    }
    public String getId() {
        return id;
    }

    public String getExercise1() {
        return exercise1;
    }

    public String getExercise2() {
        return exercise2;
    }

    public String getExercise3() {
        return exercise3;
    }

    public String getStatus() {
        return status;
    }
}
