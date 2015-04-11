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
package IO;

import Core.Config;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import static org.junit.Assert.*;
import org.junit.Test;
import org.xml.sax.SAXException;

/**
 * Testing read XML file
 * 
 */
public class XMLSpec {
    private final Configuration config;
    
    public XMLSpec() {
        File file = new File("E:/Hoc tap/CNTT/PTUDQL_2/Do an/BlueSky/config.xml");
        this.config = new Configuration(file);
    }
    
    /**
     * Read configuration file
     * 
     */
    @Test
    public void readConfigFileSpec() {
        try {
            this.config.readConfiguration();
            
            assertEquals(Config.host, "127.0.0.1");
            assertEquals(Config.port, "3306");
            assertEquals(Config.database, "qlcb");
            assertEquals(Config.username, "root");
            assertEquals(Config.password, "vertrigo");
            assertEquals(Config.language, "vi_VN");
            assertEquals(Config.theme, "Indigo");
        } catch (ParserConfigurationException | IOException | SAXException ex) {
            Logger.getLogger(XMLSpec.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
