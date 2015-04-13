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
package Core;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
* Provider
*
*/
public class Provider {
    private Connection connection;
    private Statement statement;
    
    /**
     * Check has MySQL JDBC driver
     *
     * @throws ClassNotFoundException
     */
    private void hasDriver() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch(ClassNotFoundException exception) {
            throw new ClassNotFoundException("Invalid driver!");
        }
    }
    
    /**
     * Open Connection
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private Connection openConnection() throws ClassNotFoundException, SQLException {
        try {
            this.hasDriver();
            
            if (this.connection == null) {
                this.connection = DriverManager.getConnection(Config.connectionString, Config.username, Config.password);
            }
        } catch(ClassNotFoundException exception) {
            throw exception;
        } catch(SQLException exception) {
            throw new SQLException("Connect failed to database.");
        }
        
        return this.connection;
    }
    
    /**
     * Close connection
     *
     * @throws SQLException
     */
    public void closeConnection() throws SQLException {
        try {
            if (this.statement != null && !this.statement.isClosed()) {
                this.statement.close();
                this.statement = null;
            }
        } catch (SQLException exception) {
            throw new SQLException("Cannot close statement.");
        }
        
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
                this.connection = null;
            }
        } catch(SQLException exception) {
            throw new SQLException("Cannot close connection.");
        }
    }
    
    /**
     * Check if connected to database
     * 
     * @param connectionObj DataTransferObject.Connection
     * @return boolean      is Connected to database
     * @throws Exception 
     */
    public boolean isConnected(DataTransferObject.Connection connectionObj) throws Exception {
        String connectionString = String.format(
            "jdbc:mysql://%s:%s/%s?useUnicode=yes&characterEncoding=UTF-8",
            connectionObj.getHost(), connectionObj.getPort(), connectionObj.getDatabase());
        boolean reachable = false;
        Connection _connection;
        
        try {
            this.hasDriver();
            _connection = DriverManager.getConnection(connectionString, connectionObj.getUsername(), connectionObj.getPassword());

            reachable = _connection.isValid(10);
            _connection.close();
        } catch(Exception exception) {
            throw exception;
        }
        
        return reachable;
    }
    
    /**
     * Get a statement.
     * 
     * @return Statement
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    private Statement getStatement() throws SQLException, ClassNotFoundException {
        try {
            if (this.statement == null ? true : this.statement.isClosed()) {
                this.statement = this.openConnection().createStatement();
            }
        } catch(SQLException exception) {
            throw new SQLException("Cannot create new statement.");
        } catch(ClassNotFoundException exception) {
            throw exception;
        }
        
        return this.statement;
    }
    
    /**
     * Execute script for get data from database.
     *
     * @param sql T-SQL script
     * @return ResultSet
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ResultSet executeQuery(String sql) throws SQLException, ClassNotFoundException {
        return this.getStatement().executeQuery(sql);
    }
    
    /**
     * Execute script for Insert/Update/Delete.
     * 
     * @param sql T-SQL script
     * @throws SQLException
     * @throws ClassNotFoundException
     * @return Integer
     */
    public int executeNonQuery(String sql) throws SQLException, ClassNotFoundException {
        
        int result = 0;
        
        try {
            result = this.getStatement().executeUpdate(sql);
        } catch(SQLException | ClassNotFoundException exception) {
            throw exception;
        } finally {
            this.closeConnection();
        }
        
        return result;
    }
    
    /**
     * Execute Stored Procedure for get data from database.
     * 
     * @param storeProcedureName Stored Procedure Name
     * @param args Array of arguments
     * @return ResultSet
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ResultSet query(String storeProcedureName, Object[] args) throws SQLException, ClassNotFoundException {
        String sql = "{ call " + storeProcedureName + "(";
        
        if (args != null) {
            for (Object argument : args) {
                if (argument instanceof Integer) {
                    sql += ((Integer)argument).toString() + ",";
                } else if (argument instanceof String) {
                    sql += "'" + (String)argument + "',";
                } else if (argument instanceof Float) {
                    sql += ((Float)argument).toString() + ",";
                }
            }
            
            sql = sql.substring(0, sql.length() - 1);
        }
        
        sql += ") }";
        
        CallableStatement callableStatement = this.openConnection().prepareCall(sql);
        return callableStatement.executeQuery();
    }
    
    /**
     * Execute script for Insert/Delete/Update data using Stored Procedure.
     *
     * @param storeProcedureName Stored Procedure Name
     * @param args Array of arguments
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void non(String storeProcedureName, Object[] args) throws SQLException, ClassNotFoundException {
        String sql = "{ call " + storeProcedureName + "(";
        
        if (args != null) {
            for (Object argument : args) {
                if (argument instanceof Integer) {
                    sql += ((Integer)argument).toString() + ",";
                } else if (argument instanceof String) {
                    sql += "'" + (String)argument + "',";
                } else if (argument instanceof Float) {
                    sql += ((Float)argument).toString() + ",";
                } else if (argument instanceof Date) {
                    sql += "'" + new SimpleDateFormat("yyyy/MM/dd").format(argument) + "',";
                }
            }
            
            sql = sql.substring(0, sql.length() - 1);
        }
        
        sql += ") }";
        
        try {
            CallableStatement callableStatement = this.openConnection().prepareCall(sql);
            callableStatement.execute();
        } catch(SQLException | ClassNotFoundException exception) {
            throw exception;
        } finally {
            this.closeConnection();
        }
    }
}
