package DataAccessLayer;

import Core.Cryptography;
import Core.Provider;
import DataTransferObject.Customer;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class CustomerHandler {
    private final Provider provider;
    
    public CustomerHandler() {
        this.provider = new Provider();
    }
    
    public boolean register(Customer customer) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException, UnsupportedEncodingException {
        Cryptography crypto = new Cryptography();
        customer.setPassword(crypto.encode(customer.getPassword()));
        customer.setID(crypto.encode((new Date()).toString()));
        
        String sql = String.format(
                "Insert Into KhachHang(Ma, Ten, Email, MatKhau, KichHoat) Values('%s', N'%s', '%s', '%s', %d)",
                customer.getID(), customer.getName(), customer.getEmail(), customer.getPassword(), 1    );
        
        return this.provider.executeNonQuery(sql)> 0;
    }
    
    public String login(Customer customer) throws NoSuchAlgorithmException, UnsupportedEncodingException, SQLException, ClassNotFoundException {
        Cryptography crypto = new Cryptography();
        customer.setPassword(crypto.encode(customer.getPassword()));
        customer.setID(crypto.encode((new Date()).toString()));
        
        String sql = String.format("Select Ma From KhachHang Where Email = '%s' And MatKhau = '%s' And KichHoat = 1",
                customer.getEmail(), customer.getPassword());
        
        ResultSet data = this.provider.executeQuery(sql);
        String result = "";
        
        if (data.next()) {
            result = data.getString("Ma");
        }
        
        this.provider.closeConnection();
        
        return result;
    }
    
    public String getName(String ID) throws SQLException, ClassNotFoundException, UnsupportedEncodingException {
        String sql = String.format("Select Ten From KhachHang Where Ma = '%s' And KichHoat = 1", ID);
        
        ResultSet data = this.provider.executeQuery(sql);
        String result = "";
        
        if (data.next()) {
            result = (new String(data.getString("Ten").getBytes("8859_1"),"UTF-8"));
        }
        
        this.provider.closeConnection();
        
        return result;
    }
    
    public Customer one(String ID) throws SQLException, ClassNotFoundException, UnsupportedEncodingException {
        String sql = String.format("Select Ten, Email, SDT, DiaChi, CMND From KhachHang Where Ma = '%s' And KichHoat = 1", ID);
        
        ResultSet data = this.provider.executeQuery(sql);
        Customer customer = new Customer();
        
        if (data.next()) {
            customer.setName((new String(data.getString("Ten").getBytes("8859_1"),"UTF-8")));
            customer.setEmail(data.getString("Email"));
            customer.setPhone(data.getString("SDT"));
            customer.setAddress(data.getString("DiaChi"));
            customer.setIdentityCard(data.getString("CMND"));
        }
        
        this.provider.closeConnection();
        
        return customer;
    }
    
    public boolean updateInfo(Customer customer) throws SQLException, ClassNotFoundException {
        String sql = String.format(
                "Update KhachHang Set Ten = '%s', SDT = '%s', DiaChi = '%s', CMND = '%s' Where Ma = '%s'",
                customer.getName(), customer.getPhone(), customer.getAddress(), customer.getIdentityCard(), customer.getID());
        
        return (this.provider.executeNonQuery(sql) > 0);
    }
    
    public boolean updatePassword(String customer, String oldPassword, String newPassword) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException, UnsupportedEncodingException {
        String sql = String.format("Select MatKhau From KhachHang Where Ma = '%s'", customer);
        ResultSet data = this.provider.executeQuery(sql);
        Cryptography crypto = new Cryptography();        
        
        if (data.next()) {
            if (!(crypto.encode(oldPassword).equals(data.getString("MatKhau")))) {
                return false;
            }
        } else {
            return false;
        }
        
        sql = String.format(
                "Update KhachHang Set MatKhau = '%s' Where Ma = '%s'",
                crypto.encode(newPassword), customer);
        
        return (this.provider.executeNonQuery(sql) > 0);
    }
}
