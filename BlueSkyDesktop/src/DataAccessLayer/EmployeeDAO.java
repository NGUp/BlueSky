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
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
    
    /**
     * Check user's password is valid
     * 
     * @param password String
     * @return boolean
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
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
        
        this.provider.closeConnection();
        
        return false;
    }
    
    /**
     * Change user's password
     * 
     * @param password String
     * @return boolean
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
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
    
    public int countAll() throws SQLException, ClassNotFoundException {
        String sql = "Select Count(MaNV) As SoLuong From NhanVien";
        
        ResultSet data = this.provider.executeQuery(sql);
        int count = 0;
        
        if (data.next()) {
            count = data.getInt("SoLuong");
        }
        
        this.provider.closeConnection();
        
        return count;
    }
    
    public boolean insert(Employee employee) throws SQLException, ClassNotFoundException {
        String sql = String.format(
                "INSERT INTO `nhanvien`(`MaNV`, `TenNV`, `MaLoai`, `CMND`, `TenDangNhap`, `MatKhau`, `NgSinh`, `GioiTinh`, `DiaChi`, `DienThoai`) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
                employee.getID(), employee.getName(), employee.getPermission(), employee.getIdentityCard(), employee.getUsername(), employee.getPassword(),
                new SimpleDateFormat("yyyy/MM/dd").format(employee.getBirthday()), employee.getSex(), employee.getAddress(), employee.getPhone());
        
        return (this.provider.executeNonQuery(sql) == 1);
    }
    
    public ArrayList<Employee> importEmployees(File file)
            throws ParserConfigurationException, SAXException,
                    IOException, ParseException {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(file);
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("employee");
        
        ArrayList<Employee> lists = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        
        for (int index = 0; index < nodeList.getLength(); index++) {
            Node node = nodeList.item(index);
        
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                Employee employee = new Employee();

                NodeList idElementList = element.getElementsByTagName("id");
                Element idElement = (Element) idElementList.item(0);
                NodeList id = idElement.getChildNodes();
                employee.setID(((Node) id.item(0)).getNodeValue());

                NodeList nameElementList = element.getElementsByTagName("name");
                Element nameElement = (Element) nameElementList.item(0);
                NodeList name = nameElement.getChildNodes();
                employee.setName(((Node) name.item(0)).getNodeValue());

                NodeList permissionElementList = element.getElementsByTagName("permission");
                Element permissionElement = (Element) permissionElementList.item(0);
                NodeList permission = permissionElement.getChildNodes();
                employee.setPermission(((Node) permission.item(0)).getNodeValue());

                NodeList identityElementList = element.getElementsByTagName("identity");
                Element identityElement = (Element) identityElementList.item(0);
                NodeList identity = identityElement.getChildNodes();
                employee.setIdentityCard(((Node) identity.item(0)).getNodeValue());
                
                NodeList usernameElementList = element.getElementsByTagName("username");
                Element usernameElement = (Element) usernameElementList.item(0);
                NodeList username = usernameElement.getChildNodes();
                employee.setUsername(((Node) username.item(0)).getNodeValue());

                NodeList passwordElementList = element.getElementsByTagName("password");
                Element passwordElement = (Element) passwordElementList.item(0);
                NodeList password = passwordElement.getChildNodes();
                employee.setPassword(((Node) password.item(0)).getNodeValue());

                NodeList birthdayElementList = element.getElementsByTagName("birthday");
                Element birthdayElement = (Element) birthdayElementList.item(0);
                NodeList birthday = birthdayElement.getChildNodes();
                employee.setBirthday(formatter.parse(((Node) birthday.item(0)).getNodeValue()));

                NodeList sexElementList = element.getElementsByTagName("sex");
                Element sexElement = (Element) sexElementList.item(0);
                NodeList sex = sexElement.getChildNodes();
                employee.setSex(((Node) sex.item(0)).getNodeValue());
                
                NodeList addressElementList = element.getElementsByTagName("address");
                Element addressElement = (Element) addressElementList.item(0);
                NodeList address = addressElement.getChildNodes();
                employee.setAddress(((Node) address.item(0)).getNodeValue());
                
                NodeList phoneElementList = element.getElementsByTagName("phone");
                Element phoneElement = (Element) phoneElementList.item(0);
                NodeList phone = phoneElement.getChildNodes();
                employee.setPhone(((Node) phone.item(0)).getNodeValue());
                
                lists.add(employee);
            }
        }
        
        return lists;
    }
    
    public ArrayList<Employee> limit(int page)
            throws SQLException, ClassNotFoundException {
        String sql = String.format("Select * From NhanVien Limit %d, 15", page * 15);
        
        ResultSet result = this.provider.executeQuery(sql);
        
        ArrayList<Employee> employees = new ArrayList<>();
        
        while (result.next()) {
            Employee employee = new Employee();
            employee.setAddress(result.getString("DiaChi"));
            employee.setBirthday(new Date(result.getDate("NgSinh").getTime()));
            employee.setID(result.getString("MaNV"));
            employee.setIdentityCard(result.getString("CMND"));
            employee.setName(result.getString("TenNV"));
            employee.setPermission(result.getString("MaLoai"));
            employee.setPhone(result.getString("DienThoai"));
            employee.setSex(result.getString("GioiTinh"));
            employee.setUsername(result.getString("TenDangNhap"));
            employees.add(employee);
        }
        
        this.provider.closeConnection();
        
        return employees;
    }
}
