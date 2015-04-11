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

import DataAccessLayer.ConfigurationDAO;
import DataTransferObject.Connection;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class ConfigurationBUS {
    private final ConfigurationDAO config;
    
    public ConfigurationBUS() {
        this.config = new ConfigurationDAO();
    }
    
    public ConfigurationBUS(File file) {
        this.config = new ConfigurationDAO(file);
    }
    
    public void readConfig() throws ParserConfigurationException, IOException, SAXException {
        this.config.readConfiguration();
    }
    
    public void writeConfig(Connection connection)
            throws Exception {
        
        if (!"".equals(connection.getPort()) &&
                !"".equals(connection.getHost()) &&
                !"".equals(connection.getDatabase()) &&
                !"".equals(connection.getUsername()) &&
                !"".equals(connection.getPassword())) {
        } else {
            throw new NullPointerException("Variables must be not left blank.");
        }
        
        if (config.isConnected(connection)== false) {
            throw new Exception("Configuration is wrong");
        }
        
        this.config.writeConfigurationToFile(connection);
    }
}
