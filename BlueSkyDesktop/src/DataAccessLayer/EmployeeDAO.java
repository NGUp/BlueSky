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
package DataAccessLayer;

import Core.Provider;
import Core.Session;
import DataTransferObject.Employee;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO {
    private final Provider provider;
    
    public EmployeeDAO() {
        this.provider = new Provider();
    }
    
    /**
     * The employee login into the system
     * 
     * @param employee  Employee
     * @return boolean
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public boolean login(Employee employee)
            throws SQLException, ClassNotFoundException {
        String sql = String.format(
                "Select MaNV, MaLoai From NhanVien Where TenDangNhap = '%s' And MatKhau = '%s'",
                employee.getUsername(), employee.getPassword());
        
        ResultSet data = this.provider.executeQuery(sql);
        boolean result = false;
        
        if (data.next()) {
            Session.USER_ID = data.getString("MaNV");
            Session.USER_PERMISSION = data.getString("MaLoai");
            
            result = true;
        }
        
        this.provider.closeConnection();
        
        return result;
    }
    
    public boolean checkPassword(String password)
            throws SQLException, ClassNotFoundException {
        String sql = String.format(
                "Select MatKhau From NhanVien Where MaNV = '%s'",
                Session.USER_ID);
        
        ResultSet data = this.provider.executeQuery(sql);
        
        if (data.next()) {
            if (password == null ? data.getString("MatKhau") == null : password.equals(data.getString("MatKhau"))) {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean changePassword(String password)
            throws SQLException, ClassNotFoundException {
        String sql = String.format(
                "Update NhanVien Set MatKhau = '%s' Where MaNV = '%s'",
                password, Session.USER_ID
        );
        
        if (this.provider.executeNonQuery(sql) == 1) {
            return true;
        }
        
        return false;
    }
}
