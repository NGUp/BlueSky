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
package Application;

import BusinessLogicLayer.ConfigurationBUS;
import PresentationLayer.ConfigurationForm;
import PresentationLayer.LoginForm;
import java.io.File;
import java.io.IOException;
import javax.swing.SwingUtilities;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * Main Control
 * 
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        
        /**
         * Read configuration file
         */
        File file = new File("../config.xml");
        
        if (!(file.exists() && !file.isDirectory())) {  // Check if not exists
            ConfigurationForm form = new ConfigurationForm();
            form.build();
        } else {
            ConfigurationBUS config = new ConfigurationBUS(file);
            config.readConfig();

            /**
             * Display GUI
             */
            SwingUtilities.invokeLater(new Runnable(){
                @Override
                public void run() {
                    LoginForm app = new LoginForm();
                    app.build();
                }
            });
        }
    }
}
