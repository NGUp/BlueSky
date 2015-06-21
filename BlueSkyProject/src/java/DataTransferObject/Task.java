package DataTransferObject;

import java.util.Date;

public class Task {
    private String flightID;
    private String flightName;
    private Date startTime;
    private String cabin;
    
    public Task() { }
    
    public Task(String flightID, String flightName, Date startTime, String cabin) {
        this.flightID = flightID;
        this.flightName = flightName;
        this.startTime = startTime;
        this.cabin = cabin;
    }
    
    public Task(Task task) {
        this.flightID = task.flightID;
        this.flightName = task.flightName;
        this.startTime = task.startTime;
        this.cabin = task.cabin;
    }
    
    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }
    
    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }
    
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    
    public void setCabin(String cabin) {
        this.cabin = cabin;
    }
    
    public String getFlightID() {
        return this.flightID;
    }
    
    public String getFlightName() {
        return this.flightName;
    }
    
    public Date getStartTime() {
        return this.startTime;
    }
    
    public String getCabin() {
        return this.cabin;
    }
}
