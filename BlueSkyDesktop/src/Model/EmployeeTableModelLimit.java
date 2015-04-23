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
package Model;

import DataAccessLayer.EmployeeDAO;
import DataTransferObject.Employee;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

public class EmployeeTableModelLimit extends AbstractTableModel {
    private final Vector<Vector<String>> rows;
    private final Vector<String> columnHeader;
    private final EmployeeDAO dao;
        
    public EmployeeTableModelLimit(int page) throws SQLException, ClassNotFoundException {
        this.rows = new Vector<>();
        this.columnHeader = new Vector<>();
        
        this.dao = new EmployeeDAO();
        
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        
        for (Employee employee : this.dao.limit(page)) {
            Vector<String> row = new Vector<>();
            row.add(employee.getID());
            row.add(employee.getName());
            row.add(employee.getPermission());
            row.add(employee.getIdentityCard());
            row.add(employee.getUsername());
            row.add(formatter.format(employee.getBirthday()));
            row.add(employee.getSex());
            row.add(employee.getPhone());
            
            rows.add(row);
        }
        
        this.columnHeader.add("Mã");
        this.columnHeader.add("Họ tên");
        this.columnHeader.add("Quyền");
        this.columnHeader.add("CMND");
        this.columnHeader.add("Tên đăng nhập");
        this.columnHeader.add("Ngày sinh");
        this.columnHeader.add("Giới tính");
        this.columnHeader.add("Điện thoại");
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return columnHeader.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rows.elementAt(rowIndex).elementAt(columnIndex);
    }
    
    @Override
    public String getColumnName(int column){
        return columnHeader.elementAt(column);
    }
}
