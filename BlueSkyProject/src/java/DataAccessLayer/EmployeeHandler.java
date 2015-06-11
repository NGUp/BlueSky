package DataAccessLayer;

import Core.Cryptography;
import Core.Provider;
import DataTransferObject.Customer;
import DataTransferObject.Employee;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class EmployeeHandler {
    private final Provider provider;
    
    public EmployeeHandler() {
        this.provider = new Provider();
    }
    
    public String login(Employee employee) throws NoSuchAlgorithmException, UnsupportedEncodingException, SQLException, ClassNotFoundException {
        Cryptography crypto = new Cryptography();
        employee.setPassword(crypto.encode(employee.getPassword()));
        
        String sql = String.format("Select Ma From NhanVien Where TenDangNhap = '%s' And MatKhau = '%s' And TrangThai = 1",
                employee.getUsername(), employee.getPassword());
        
        ResultSet data = this.provider.executeQuery(sql);
        String result = "";
        
        if (data.next()) {
            result = data.getString("Ma");
        }
        
        this.provider.closeConnection();
        
        return result;
    }
    
    public String getName(String ID) throws SQLException, ClassNotFoundException, UnsupportedEncodingException {
        String sql = String.format("Select Ten From NhanVien Where Ma = '%s' And TrangThai = 1", ID);
        
        ResultSet data = this.provider.executeQuery(sql);
        String result = "";
        
        if (data.next()) {
            result = (new String(data.getString("Ten").getBytes("8859_1"),"UTF-8"));
        }
        
        this.provider.closeConnection();
        
        return result;
    }
}
