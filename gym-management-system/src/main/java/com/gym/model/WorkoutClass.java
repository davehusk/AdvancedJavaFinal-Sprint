package com.gym.model;

import java.sql.Timestamp;

public class WorkoutClass {
    private int classId;
    private String className;
    private String description;
    private Timestamp schedule;
    private int trainerId;
    
    // Getters and setters
    public int getClassId() { return classId; }
    public void setClassId(int classId) { this.classId = classId; }
    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Timestamp getSchedule() { return schedule; }
    public void setSchedule(Timestamp schedule) { this.schedule = schedule; }
    public int getTrainerId() { return trainerId; }
    public void setTrainerId(int trainerId) { this.trainerId = trainerId; }
    
    @Override
    public String toString() {
        return "Class ID: " + classId + "\n" +
               "Name: " + className + "\n" +
               "Description: " + description + "\n" +
               "Schedule: " + schedule + "\n" +
               "Trainer ID: " + trainerId;
    }
}