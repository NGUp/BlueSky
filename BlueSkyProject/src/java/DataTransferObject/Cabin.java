package DataTransferObject;

public class Cabin {
    private String ID;
    private String name;
    private int columns;
    private int rows;
    private String plane;
    
    public Cabin() {}
    
    public Cabin(String ID, String name, String plane, int columns, int rows) {
        this.ID = ID;
        this.name = name;
        this.plane = plane;
        this.columns = columns;
        this.rows = rows;
    }
    
    public Cabin(Cabin cabin) {
        this.ID = cabin.ID;
        this.name = cabin.name;
        this.plane = cabin.plane;
        this.columns = cabin.columns;
        this.rows = cabin.rows;
    }
    
    public void setID(String ID) {
        this.ID = ID;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setPlane(String plane) {
        this.plane = plane;
    }
    
    public void setColumns(int columns) {
        this.columns = columns;
    }
    
    public void setRows(int rows) {
        this.rows = rows;
    }
    
    public String getID() {
        return this.ID;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getPlane() {
        return this.plane;
    }
    
    public int getColumns() {
        return this.columns;
    }
    
    public int getRows() {
        return this.rows;
    }
}
