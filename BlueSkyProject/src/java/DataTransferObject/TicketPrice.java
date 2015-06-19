package DataTransferObject;

import java.util.Date;

public class TicketPrice {
    private String ID;
    private Date startTime;
    private Date endTime;
    private float price;
    private String flight;
    private String cabin;
    
    public TicketPrice() {}
    
    public TicketPrice(String ID, Date startTime, Date endTime, float price, String flight, String cabin) {
        this.ID = ID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.flight = flight;
        this.cabin = cabin;
    }
    
    public TicketPrice(TicketPrice ticketPrice) {
        this.ID = ticketPrice.ID;
        this.startTime = ticketPrice.startTime;
        this.endTime = ticketPrice.endTime;
        this.price = ticketPrice.price;
        this.flight = ticketPrice.flight;
        this.cabin = ticketPrice.cabin;
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
