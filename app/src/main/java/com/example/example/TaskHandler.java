package com.example.example;

public class TaskHandler {

    private String taskName;
    private long timestamp; // Timestamp in milliseconds

    public String getTaskName() { return taskName; }

    public void setTaskName(String courseName)
    {
        this.taskName = courseName;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    // constructor
    public TaskHandler(String taskName,long timestamp)
    {
        this.taskName = taskName;
        this.timestamp = timestamp;



    }
}



