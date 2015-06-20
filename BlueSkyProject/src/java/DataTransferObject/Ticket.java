package DataTransferObject;

import java.util.Date;

public class Ticket {

    private String ID;
    private String cabin;
    private Date time;
    private String flight;
    private String seat;
    private String customer;
    private String bill;
    private String price;
    
    public Ticket() {};
    
    public Ticket(String ID, String cabin, Date time, String flight, String seat, String customer, String bill, String price) {
        this.ID = ID;
        this.cabin = cabin;
        this.time = time;
        this.flight = flight;
        this.seat = seat;
        this.customer = customer;
        this.bill = bill;
        this.price = price;
    }
    
    public Ticket(Ticket ticket) {
        this.ID = ticket.ID;
        this.cabin = ticket.cabin;
        this.time = ticket.time;
        this.flight = ticket.flight;
        this.seat = ticket.seat;
        this.customer = ticket.customer;
        this.bill = ticket.bill;
        this.price = ticket.price;
    }
    
    public void setID(String ID) {
        this.ID = ID;
    }
    
    public void setCabin(String cabin) {
        this.cabin = cabin;
    }
    
    public void setTime(Date time) {
        this.time = time;
    }
    
    public void setFlight(String flight) {
        this.flight = flight;
    }
    
    public void setSeat(String seat) {
        this.seat = seat;
    }
    
    public void setCustomer(String customer) {
        this.customer = customer;
    }
    
    public void setBill(String bill) {
        this.bill = bill;
    }
    
    public void setPrice(String price) {
        this.price = price;
    }
    
    public String getID() {
        return this.ID;
    }
    
    public String getCabin() {
        return this.cabin;
    }
    
    public Date getTime() {
        return this.time;
    }
    
    public String getFlight() {
        return this.flight;
    }
    
    public String getSeat() {
        return this.seat;
    }
    
    public String getCustomer() {
        return this.customer;
    }
    
    public String getBill() {
        return this.bill;
    }
    
    public String getPrice() {
        return this.price;
    }
}
