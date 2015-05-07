/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 - 110001NP Development Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE
 */
package DataTransferObject;

import java.util.Date;

/**
 * The employee
 *
 */
public class Employee {
    private String ID;
    private String name;
    private String permission;
    private String identityCard;
    private String username;
    private String password;
    private int state;
    private Date birthday;
    private String sex;
    private String address;
    private String phone;
    
    /**
     * Default constructor
     * 
     */
    public Employee() { }
    
    /**
     * Full constructor
     * 
     * @param ID            The employee ID
     * @param name          The full-name of the employee
     * @param permission    The permission of the employee
     * @param identityCard  The identity card number
     * @param username      The username of the account
     * @param password      The password of the account 
     * @param state         The state of the employee
     * @param birthday      The birthday
     * @param sex           The sex
     * @param address       The address
     * @param phone         The phone number
     */
    public Employee(
            String ID, String name, String permission, String identityCard,
            String username, String password, int state, Date birthday, String sex,
            String address, String phone) {
        this.ID = ID;
        this.name = name;
        this.permission = permission;
        this.identityCard = identityCard;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.state = state;
        this.sex = sex;
        this.address = address;
        this.phone = phone;
    }
    
    /**
     * Copy constructor
     * 
     * @param employee Employee
     */
    public Employee(Employee employee) {
        this.ID = employee.ID;
        this.name = employee.name;
        this.permission = employee.permission;
        this.identityCard = employee.identityCard;
        this.username = employee.username;
        this.password = employee.password;
        this.state = employee.state;
        this.birthday = employee.birthday;
        this.sex = employee.sex;
        this.address = employee.address;
        this.phone = employee.phone;
    }
    
    /**
     * Set ID number
     * 
     * @param ID String
     */
    public void setID(String ID) {
        this.ID = ID;
    }
    
    /**
     * Set full-name
     * 
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Set the permission
     * 
     * @param permission String
     */
    public void setPermission(String permission) {
        this.permission = permission;
    }
    
    /**
     * Set the identity card number
     * 
     * @param identityCard String
     */
    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }
    
    /**
     * Set the username
     * 
     * @param username String
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * Set the password
     * 
     * @param password String
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Set the state
     * 
     * @param state Integer
     */
    public void setState(int state) {
        this.state = state;
    }
    
    /**
     * Set the birthday
     * 
     * @param birthday java.util.Date
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    /**
     * Set the sex
     * 
     * @param sex String
     */
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    /**
     * Set the address
     * 
     * @param address String
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
    /**
     * Set the phone number
     * 
     * @param phone String
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    /**
     * Get ID
     * 
     * @return String
     */
    public String getID() {
        return this.ID;
    }
    
    /**
     * Get full-name
     * 
     * @return String
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Get permission of this employee
     * 
     * @return String
     */
    public String getPermission() {
        return this.permission;
    }
    
    /**
     * Get identity card number
     * 
     * @return String
     */
    public String getIdentityCard() {
        return this.identityCard;
    }
    
    /**
     * Get the username
     * 
     * @return String
     */
    public String getUsername() {
        return this.username;
    }
    
    /**
     * Get the password
     * 
     * @return String
     */
    public String getPassword() {
        return this.password;
    }
    
    /**
     * Get the state
     * 
     * @return Integer
     */
    public int getState() {
        return this.state;
    }
    
    /**
     * Get the birthday of this employee
     * 
     * @return java.util.Date
     */
    public Date getBirthday() {
        return this.birthday;
    }
    
    /**
     * Get address
     * 
     * @return String
     */
    public String getAddress() {
        return this.address;
    }
    
    /**
     * Get the phone number
     * 
     * @return String
     */
    public String getPhone() {
        return this.phone;
    }
    
    /**
     * Get employee's sex
     * 
     * @return String
     */
    public String getSex() {
        return this.sex;
    }
}