package DataTransferObject;

import java.util.Date;

public class Ticket {
    private String ID;
    private Date startTime;
    private Date endTime;
    private float price;
    private String flight;
    private String cabin;
    
    public Ticket() {}
    
    public Ticket(String ID, Date startTime, Date endTime, float price, String flight, String cabin) {
        this.ID = ID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.flight = flight;
        this.cabin = cabin;
    }
    
    public Ticket(Ticket ticket) {
        this.ID = ticket.ID;
        this.startTime = ticket.startTime;
        this.endTime = ticket.endTime;
        this.price = ticket.price;
        this.flight = ticket.flight;
        this.cabin = ticket.cabin;
    }
    
    public void setID(String ID) {
        this.ID = ID;
    }
    
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    
    public void setPrice(float price) {
        this.price = price;
    }
    
    public void setFlight(String flight) {
        this.flight = flight;
    }
    
    public void setCabin(String cabin) {
        this.cabin = cabin;
    }
    
    public String getID() {
        return this.ID;
    }
    
    public Date getStartTime() {
        return this.startTime;
    }
    
    public Date getEndTime() {
        return this.endTime;
    }
    
    public float getPrice() {
        return this.price;
    }
    
    public String getFlight() {
        return this.flight;
    }
    
    public String getCabin() {
        return this.cabin;
    }
}
