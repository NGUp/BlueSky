package DataAccessLayer;

import Core.Cryptography;
import Core.Provider;
import DataTransferObject.Employee;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    
    public String getPermission(String ID) throws SQLException, ClassNotFoundException, UnsupportedEncodingException {
        String sql = String.format("Select MaLoai From NhanVien Where Ma = '%s' And TrangThai = 1", ID);
        
        ResultSet data = this.provider.executeQuery(sql);
        String result = "";
        
        if (data.next()) {
            result = data.getString("MaLoai");
        }
        
        this.provider.closeConnection();
        
        return result;
    }
    
    public Employee one(String ID) throws SQLException, ClassNotFoundException, UnsupportedEncodingException {
        String sql = String.format("Select * From NhanVien Where Ma = '%s' And TrangThai = 1", ID);
        
        ResultSet result = this.provider.executeQuery(sql);
        Employee employee = new Employee();
        
        while (result.next()) {
            employee.setID(result.getString("Ma"));
            employee.setIdentityCard(result.getString("CMND"));
            employee.setName((new String(result.getString("Ten").getBytes("8859_1"),"UTF-8")));
            employee.setEmail(result.getString("Email"));
            //employee.setPermission(result.getString("TenLoai"));
            employee.setBirthday(new Date(result.getDate("NgSinh").getTime()));
            employee.setUsername(result.getString("TenDangNhap"));
            employee.setGender(result.getString("GioiTinh"));
            employee.setAddress((new String(result.getString("DiaChi").getBytes("8859_1"),"UTF-8")));            
            employee.setPhone(result.getString("DienThoai"));
            employee.setState(result.getInt("TrangThai"));
        }
        
        return employee;
    }
    
    public boolean updateInfo(Employee employee) throws SQLException, ClassNotFoundException {
        String sql = String.format(
                "Update NhanVien Set Ten = '%s', Email = '%s', NgSinh = '%s', DienThoai = '%s', DiaChi = '%s', CMND = '%s' Where Ma = '%s'",
                employee.getName(), employee.getEmail(), new SimpleDateFormat("yyyy-MM-dd").format(employee.getBirthday()), employee.getPhone(), employee.getAddress(), employee.getIdentityCard(), employee.getID());
        
        return (this.provider.executeNonQuery(sql) > 0);
    }
    
    public boolean updatePassword(String employee, String oldPassword, String newPassword) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException, UnsupportedEncodingException {
        String sql = String.format("Select MatKhau From NhanVien Where Ma = '%s'", employee);
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
                "Update NhanVien Set MatKhau = '%s' Where Ma = '%s'",
                crypto.encode(newPassword), employee);
        
        return (this.provider.executeNonQuery(sql) > 0);
    }
    
    public boolean create(Employee employee) throws SQLException, ClassNotFoundException
    {
        String sql = String.format(
                "INSERT INTO `nhanvien`(`Ma`, `Ten`, `MaLoai`, `CMND`, `TenDangNhap`, `Email`, `MatKhau`, `NgSinh`, `GioiTinh`, `DiaChi`, `DienThoai`) VALUES ('%s','%s', 'EMPLOYEE','%s','%s','%s','%s','%s','%s','%s','%s')",
                employee.getID(), employee.getName(), employee.getIdentityCard(), employee.getUsername(), employee.getEmail(), employee.getPassword(),
                new SimpleDateFormat("yyyy-MM-dd").format(employee.getBirthday()), employee.getGender(), employee.getAddress(), employee.getPhone());
        
        System.out.print(sql);
        
        return (this.provider.executeNonQuery(sql) > 0);
    }
    
    public ArrayList<Employee> limit(int page)
            throws SQLException, ClassNotFoundException, UnsupportedEncodingException {
        String sql = String.format(
                "Select Ma, Ten, Email, CMND, TenDangNhap, TrangThai, NgSinh, GioiTinh, DiaChi, DienThoai, TenLoai " +
                "From NhanVien NV join LoaiNV loai on NV.MaLoai = loai.MaLoai " + 
                "Where NV.MALOAI != 'ADMIN' Limit %d, 20", (page - 1) * 20);
        
        ResultSet result = this.provider.executeQuery(sql);
        
        ArrayList<Employee> employees = new ArrayList<>();
        
        while (result.next()) {
            
            Employee employee = new Employee();
            employee.setID(result.getString("Ma"));
            employee.setIdentityCard(result.getString("CMND"));
            employee.setName((new String(result.getString("Ten").getBytes("8859_1"),"UTF-8")));
            employee.setEmail(result.getString("Email"));
            employee.setPermission(result.getString("TenLoai"));
            employee.setBirthday(new Date(result.getDate("NgSinh").getTime()));
            employee.setUsername(result.getString("TenDangNhap"));
            employee.setGender(result.getString("GioiTinh"));
            employee.setAddress((new String(result.getString("DiaChi").getBytes("8859_1"),"UTF-8")));            
            employee.setPhone(result.getString("DienThoai"));
            employee.setState(result.getInt("TrangThai"));
            employees.add(employee);
        }
        
        this.provider.closeConnection();
        
        return employees;
    }
    
    public boolean delete(String ID) throws SQLException, ClassNotFoundException {
        String sql = String.format("Delete From `NhanVien` Where Ma = '%s'", ID);
        return (this.provider.executeNonQuery(sql) > 0);
    }
    
    public boolean disable(String ID) throws SQLException, ClassNotFoundException {
        String sql = String.format("Update `NhanVien` Set TrangThai = 0 Where Ma = '%s'", ID);
        return (this.provider.executeNonQuery(sql) > 0);
    }
    
    public boolean enable(String ID) throws SQLException, ClassNotFoundException {
        String sql = String.format("Update `NhanVien` Set TrangThai = 1 Where Ma = '%s'", ID);
        return (this.provider.executeNonQuery(sql) > 0);
    }
}
