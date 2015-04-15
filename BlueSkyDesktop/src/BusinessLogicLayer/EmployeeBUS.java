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
package BusinessLogicLayer;

import Core.Cryptography;
import DataAccessLayer.EmployeeDAO;
import DataTransferObject.Employee;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class EmployeeBUS {
    private final EmployeeDAO dao;
    
    public EmployeeBUS() {
        this.dao = new EmployeeDAO();
    }

    /**
     * Employee login support
     * 
     * @param employee Employee
     * @return boolean
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public boolean login(Employee employee)
            throws NoSuchAlgorithmException, UnsupportedEncodingException,
                    SQLException, ClassNotFoundException {
        Cryptography crypto = new Cryptography();
        
        employee.setPassword(
                crypto.getMD5(
                        "9dbh32kzkj2" +
                        crypto.getSHA1(employee.getPassword()) +
                        "32jsa9d82h"));
        
        return this.dao.login(employee);
    }
}
