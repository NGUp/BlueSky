package DataTransferObject;

import java.util.Date;

public class Employee {
    private String ID;
    private String name;
    private String permission;
    private String identityCard;
    private String userName;
    private String email;
    private String passWord;
    private Date birthday;
    private String gender;
    private String address;
    private String phone;
    
    public Employee() {}
    
    public Employee(String ID, String name, String permission, String identityCard, String userName, String email, String password, Date birthday, String gender, String phone, String address) {
        this.ID = ID;
        this.name = name;
        this.identityCard = identityCard;
        this.userName = userName;
        this.permission = permission;
        this.email = email;
        this.passWord = password;
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
    }
    
    public Employee(Employee employee) {
        this.ID = employee.ID;
        this.name = employee.name;
        this.identityCard = employee.identityCard;
        this.userName = employee.userName;
        this.permission = employee.permission;
        this.email = employee.email;
        this.passWord = employee.passWord;
        this.birthday = employee.birthday;
        this.gender = employee.gender;
        this.phone = employee.phone;
    }
    
    public void setID(String ID) {
        this.ID = ID;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setPermission(String permission) {
        this.permission = permission;
    }
    
    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }
    
    public void setUsername(String userName) {
        this.userName = userName;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPassword(String password) {
        this.passWord = password;
    }
    
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getID() {
        return this.ID;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getPassword() {
        return this.passWord;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public Date getBirthday() {
        return this.birthday;
    }
    
    public String getPhone() {
        return this.phone;
    }
    
    public String getPermission() {
        return this.permission;
    }
    
    public String getAddress() {
        return this.address;
    }
    
    public String getGender() {
        return this.gender;
    }
    
    public String getIdentityCart() {
        return this.identityCard;
    }
    
    public String getUsername() {
        return this.userName;
    }
}
