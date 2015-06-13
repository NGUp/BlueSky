package DataTransferObject;

import java.util.Date;

public class Plane {
    private String ID;
    private String name;
    private String manufacturer;
    private Date start;
    
    public Plane() { }
    
    public Plane(String ID, String name, String manufacturer, Date start) {
        this.ID = ID;
        this.name = name;
        this.manufacturer = manufacturer;
        this.start = start;
    }
    
    public Plane(Plane plane) {
        this.ID = plane.ID;
        this.name = plane.name;
        this.manufacturer = plane.manufacturer;
        this.start = plane.start;
    }
    
    public void setID(String ID) {
        this.ID = ID;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    
    public void setStart(Date start) {
        this.start = start;
    }
    
    public String getID() {
        return this.ID;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getManufacturer() {
        return this.manufacturer;
    }
    
    public Date getStart() {
        return this.start;
    }
}
