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
package PresentationLayer;

import BusinessLogicLayer.ConfigurationBUS;
import Components.FlatButton;
import Components.SpringUtilities;
import DataTransferObject.Connection;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class ConfigurationForm extends JFrame {
    
    private final JTextField hostTextField;
    private final JTextField portTextField;
    private final JTextField databaseTextField;
    private final JTextField usernameTextField;
    private final JPasswordField passwordTextField;
    
    public ConfigurationForm() {
        super("Configuration");
        
        this.hostTextField = new JTextField();
        this.portTextField = new JTextField();
        this.databaseTextField = new JTextField();
        this.usernameTextField = new JTextField();
        this.passwordTextField = new JPasswordField();
    }
    
    public void build() {
        this.initialize();
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(640, 480);
        this.setResizable(false);
        this.setVisible(true);
    }
    
    private void initialize() {
        JLabel titleLabel = new JLabel();
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setText(
                "<html><body>" +
                    "<label style=\"font-weight: 400; font-size: 24px;\">" +
                        "Configuration" +
                    "</label>" +
                "</body></html>");
        
        Font font = new Font("Segoe UI", Font.PLAIN, 16);
        
        JLabel hostLabel = new JLabel("Host: ");
        hostLabel.setFont(font);
        
        JLabel portLabel = new JLabel("Port: ");
        portLabel.setFont(font);
        
        JLabel databaseLabel = new JLabel("Database: ");
        databaseLabel.setFont(font);
        
        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setFont(font);
        
        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setFont(font);
        
        FlatButton acceptButton = new FlatButton("Accept");
        acceptButton.setBackground(Color.BLACK);
        acceptButton.setForeground(Color.WHITE);
        acceptButton.addActionListener(new acceptListener());
        
        FlatButton cancelButton = new FlatButton("Cancel");
        cancelButton.addActionListener(new closeListener());
        
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.BLACK);
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(640, 72));
        headerPanel.add(titleLabel);
        
        JPanel configPanel = new JPanel();
        configPanel.setBackground(Color.WHITE);
        configPanel.setBorder(BorderFactory.createEmptyBorder(80, 0, 0, 0));
        configPanel.setPreferredSize(new Dimension(400, 300));
        configPanel.setLayout(new SpringLayout());
        configPanel.add(hostLabel);
        configPanel.add(this.hostTextField);
        configPanel.add(portLabel);
        configPanel.add(this.portTextField);
        configPanel.add(databaseLabel);
        configPanel.add(this.databaseTextField);
        configPanel.add(usernameLabel);
        configPanel.add(this.usernameTextField);
        configPanel.add(passwordLabel);
        configPanel.add(this.passwordTextField);
        configPanel.add(cancelButton);
        configPanel.add(acceptButton);
        SpringUtilities.makeGrid(configPanel,
                         6, 2, //rows, cols
                         0, 0, //initialX, initialY
                         8, 8);//xPad, yPad
        
        JPanel bodyPanel = new JPanel();
        bodyPanel.setBackground(Color.WHITE);
        bodyPanel.add(configPanel);
        
        this.setLayout(new BorderLayout());
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(bodyPanel, BorderLayout.CENTER);
    }
    
    private class closeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    
    private class acceptListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ConfigurationBUS bus = new ConfigurationBUS();
            
            try {
                Connection connection = new Connection();
                connection.setHost(hostTextField.getText());
                connection.setPort(portTextField.getText());
                connection.setDatabase(databaseTextField.getText());
                connection.setUsername(usernameTextField.getText());
                connection.setPassword(passwordTextField.getText());
                
                bus.writeConfig(connection);
                
                LoginForm app = new LoginForm();
                app.build();
                dispose();
                
            } catch (ParserConfigurationException | TransformerException | NullPointerException exception) {
                JOptionPane.showMessageDialog(null,
                    exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                Logger.getLogger(ConfigurationForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
