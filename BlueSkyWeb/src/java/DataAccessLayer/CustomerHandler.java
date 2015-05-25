package DataAccessLayer;

import Core.Cryptography;
import Core.Provider;
import DataTransferObject.Customer;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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
                "Insert Into KhachHang(Ma, Ten, Email, MatKhau, KichHoat) Values('%s', '%s', '%s', '%s', %d)",
                customer.getID(), customer.getName(), customer.getEmail(), customer.getPassword(), 0);
        
        System.out.println(sql);
        
        return this.provider.executeNonQuery(sql)> 0;
    }
}
