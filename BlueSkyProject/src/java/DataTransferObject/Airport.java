package DataTransferObject;

public class Airport {
    private String ID;
    private String name;
    
    public Airport() {}
    
    public Airport(String ID, String name) {
        this.ID = ID;
        this.name = name;
    }
    
    public Airport(Airport airport) {
        this.ID = airport.ID;
        this.name = airport.name;
    }
    
    public void setID(String ID) {
        this.ID = ID;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getID() {
        return this.ID;
    }
    
    public String getName() {
        return this.name;
    }
}
