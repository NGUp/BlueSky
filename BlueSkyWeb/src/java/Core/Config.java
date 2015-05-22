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

/**
* Configuration
*
*/
public class Config {
    
    /**
     * The database host
     * 
     */
    public static String host = "127.0.0.1";
    
    /**
     * The database port
     * 
     */
    public static String port = "3306";
    
    /**
     * The database name
     * 
     */
    public static String database = "qlcb";
    
    /**
     * Username of the Database
     * 
     */
    public static String username = "root";
    
    /**
     * Password of the Database
     * 
     */
    public static String password = "vertrigo";
    
    /**
     * Language of the application
     * 
     */
    public static String language = "en_US";
    
    /**
     * Theme of the application
     * 
     */
    public static String theme;
    
    /**
     * The connection string
     * 
     */
    public static String connectionString = String.format(
            "jdbc:mysql://%s:%s/%s?useUnicode=yes&characterEncoding=UTF-8",
            host, port, database);
}
