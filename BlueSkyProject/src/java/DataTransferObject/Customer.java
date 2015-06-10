/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataTransferObject;

/**
 *
 * @author namvh
 */
public class Customer {
    private String ID;
    private String Name;
    private String Email;
    private String Password;
    private String IdentityCard;
    private String Phone;
    private String Address;
    private int Enabled;
    
    public Customer() {}
    
    public Customer(String id, String name, String email, String identityCard, String phone, String address, String password, int enabled) {
        this.ID = id;
        this.Name = name;
        this.Email = email;
        this.Password = password;
        this.IdentityCard = identityCard;
        this.Phone = phone;
        this.Address = address;
        this.Enabled = enabled;
    }
    
    public Customer(Customer customer) {
        this.ID = customer.ID;
        this.Name = customer.Name;
        this.Email = customer.Email;
        this.Password = customer.Password;
        this.IdentityCard = customer.IdentityCard;
        this.Phone = customer.Phone;
        this.Address = customer.Address;
        this.Enabled = customer.Enabled;
    }
    
    public void setID(String id) {
        this.ID = id;
    }
    
    public void setName(String name) {
        this.Name = name;
    }
    
    public void setEmail(String email) {
        this.Email = email;
    }
    
    public void setPassword(String password) {
        this.Password = password;
    }
    
    public void setIdentityCard(String identityCard) {
        this.IdentityCard = identityCard;
    }
    
    public void setPhone(String phone) {
        this.Phone = phone;
    }
    
    public void setAddress(String address) {
        this.Address = address;
    }
    
    public void setEnable(int enable) {
        this.Enabled = enable;
    }
    
    public String getID() {
        return this.ID;
    }
    
    public String getName() {
        return this.Name;
    }
    
    public String getEmail() {
        return this.Email;
    }
    
    public String getPassword() {
        return this.Password;
    }
    
    public String getIdentityCard() {
        return this.IdentityCard;
    }
    
    public String getPhone() {
        return this.Phone;
    }
    
    public String getAddress() {
        return this.Address;
    }
    
    public int getEnable() {
        return this.Enabled;
    }
}
