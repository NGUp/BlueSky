package DataTransferObject;

public class Cabin {
    private String ID;
    private String name;
    
    public Cabin() {}
    
    public Cabin(String ID, String name) {
        this.ID = ID;
        this.name = name;
    }
    
    public Cabin(Cabin cabin) {
        this.ID = cabin.ID;
        this.name = cabin.name;
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
