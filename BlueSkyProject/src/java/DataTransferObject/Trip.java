package DataTransferObject;

public class Trip {
    private String ID;
    private String name;
    private String from;
    private String to;
    
    public Trip() {}
    
    public Trip(String ID, String name, String from, String to) {
        this.ID = ID;
        this.name = name;
        this.from = from;
        this.to = to;
    }
    
    public Trip(Trip trip) {
        this.ID = trip.ID;
        this.name = trip.name;
        this.from = trip.from;
        this.to = trip.to;
    }
    
    public void setID(String ID) {
        this.ID = ID;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setFrom(String from) {
        this.from = from;
    }
    
    public void setTo(String to) {
        this.to = to;
    }
    
    public String getID() {
        return this.ID;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getFrom() {
        return this.from;
    }
    
    public String getTo() {
        return this.to;
    }
}
