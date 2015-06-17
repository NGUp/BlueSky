package DataTransferObject;

import java.util.Date;

public class Flight {
    private String ID;
    private Date departure;
    private Date arrival;
    private String plane;
    private String trip;
    private String mainPilot;
    private String vicePilot;
    private String mainStewardess;
    
    public Flight() {}
    
    public Flight(String ID, Date departure, Date arrival, String plane, String trip, String mainPilot, String vicePilot, String mainStewardess) {
        this.ID = ID;
        this.departure = departure;
        this.arrival = arrival;
        this.plane = plane;
        this.trip = trip;
        this.mainPilot = mainPilot;
        this.vicePilot = vicePilot;
        this.mainStewardess = mainStewardess;
    }
    
    public Flight(Flight flight) {
        this.ID = flight.ID;
        this.departure = flight.departure;
        this.arrival = flight.arrival;
        this.plane = flight.plane;
        this.trip = flight.trip;
        this.mainPilot = flight.mainPilot;
        this.vicePilot = flight.vicePilot;
        this.mainStewardess = flight.mainStewardess;
    }
    
    public void setID(String ID) {
        this.ID = ID;
    }
    
    public void setDeparture(Date departure) {
        this.departure = departure;
    }
    
    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }
    
    public void setPlane(String plane) {
        this.plane = plane;
    }
    
    public void setTrip(String trip) {
        this.trip = trip;
    }
    
    public void setMainPilot(String mainPilot) {
        this.mainPilot = mainPilot;
    }
    
    public void setVicePilot(String vicePilot) {
        this.vicePilot = vicePilot;
    }
    
    public void setMainStewardess(String mainStewardess) {
        this.mainStewardess = mainStewardess;
    }
    
    public String getID() {
        return this.ID;
    }
    
    public Date getDeparture() {
        return this.departure;
    }
    
    public Date getArrival() {
        return this.arrival;
    }
    
    public String getPlane() {
        return this.plane;
    }
    
    public String getTrip() {
        return this.trip;
    }
    
    public String getMainPilot() {
        return this.mainPilot;
    }
    
    public String getVicePilot() {
        return this.vicePilot;
    }
    
    public String getMainStewardess() {
        return this.mainStewardess;
    }
}
