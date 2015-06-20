package DataTransferObject;

import java.util.Date;

public class Bill {
    
    private String ID;
    private Date time;
    private float total;
    private String customer;
    
    public Bill() {}
    
    public Bill(String ID, Date time, float total, String customer) {
        this.ID = ID;
        this.time = time;
        this.total = total;
        this.customer = customer;
    }
    
    public Bill(Bill bill) {
        this.ID = bill.ID;
        this.time = bill.time;
        this.total = bill.total;
        this.customer = bill.customer;
    }
    
    public void setID(String ID) {
        this.ID = ID;
    }
    
    public void setTime(Date time) {
        this.time = time;
    }
    
    public void setTotal(float total) {
        this.total = total;
    }
    
    public void setCustomer(String customer) {
        this.customer = customer;
    }
    
    public String getID() {
        return this.ID;
    }
    
    public Date getTime() {
        return this.time;
    }
    
    public float getTotal() {
        return this.total;
    }
    
    public String getCustomer() {
        return this.customer;
    }
}
