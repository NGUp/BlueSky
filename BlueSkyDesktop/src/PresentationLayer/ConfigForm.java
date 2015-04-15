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
import Core.Config;
import DataTransferObject.Connection;
import DataTransferObject.ItemComboBox;
import DataTransferObject.LanguageModel;
import DataTransferObject.ThemeModel;
import ResourceBundle.Language;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class ConfigForm extends JFrame {
    private final JLabel titleLabel;
    private final JLabel hostLabel;
    private final JLabel portLabel;
    private final JLabel databaseLabel;
    private final JLabel usernameLabel;
    private final JLabel passwordLabel;
    private final JLabel themeLabel;
    private final JLabel languageLabel;
    
    private final JPanel headerPanel;
    private final JPanel configPanel;
    private final JPanel bodyPanel;
    
    private final JTextField hostTextField;
    private final JTextField portTextField;
    private final JTextField databaseTextField;
    private final JTextField usernameTextField;
    private final JPasswordField passwordTextField;
    
    private final JComboBox themeComboBox;
    private final JComboBox languageComboBox;
    
    private final FlatButton cancelButton;
    private final FlatButton okButton;
    
    private final Dimension headerDimension;
    
    private Color theme;
    
    private final Font font;
    
    private ResourceBundle bundle;
    
    public ConfigForm() {
        super("BlueSky");
        
        this.titleLabel = new JLabel();
        this.hostLabel = new JLabel();
        this.portLabel = new JLabel();
        this.databaseLabel = new JLabel();
        this.usernameLabel = new JLabel();
        this.passwordLabel = new JLabel();
        this.themeLabel = new JLabel();
        this.languageLabel = new JLabel();
        
        this.headerPanel = new JPanel();
        this.configPanel = new JPanel();
        this.bodyPanel = new JPanel();
        
        this.hostTextField = new JTextField();
        this.portTextField = new JTextField();
        this.databaseTextField = new JTextField();
        this.usernameTextField = new JTextField();
        this.passwordTextField = new JPasswordField();
        
        this.themeComboBox = new JComboBox();
        this.languageComboBox = new JComboBox();
        
        this.cancelButton = new FlatButton();
        this.okButton = new FlatButton();
        
        this.font = new Font("Segoe UI", Font.PLAIN, 16);
        
        this.headerDimension = new Dimension();
    }
    
    public void build() {
        this.initialize();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getMaximumWindowBounds().getSize());
        this.setVisible(true);
    }
    
    public void initialize() {
        this.theme = Palette.getTheme();
        
        this.bundle = ResourceBundle.getBundle(
                "ResourceBundle.ConfigForm", Language.getLanguage());
        
        this.titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 36));
        this.titleLabel.setForeground(Color.WHITE);
        this.titleLabel.setLocation(90, 0);
        this.titleLabel.setSize(800, 60);
        this.titleLabel.setText(
                "<html><body>" +
                    "<label style=\"font-weight: 400; font-size: 24px;\">" +
                        "BlueSky - " +
                        "<span style=\"font-weight: 400;\">" +
                            this.bundle.getString("lblTitle") +
                        "</span>" +
                    "</label>" +
                "</body></html>"
        );
        
        this.hostLabel.setFont(this.font);
        this.hostLabel.setText(this.bundle.getString("lblHost"));
        
        this.portLabel.setFont(this.font);
        this.portLabel.setText(this.bundle.getString("lblPort"));
        
        this.databaseLabel.setFont(this.font);
        this.databaseLabel.setText(this.bundle.getString("lblDatabase"));
        
        this.usernameLabel.setFont(this.font);
        this.usernameLabel.setText(this.bundle.getString("lblUsername"));
        
        this.passwordLabel.setFont(this.font);
        this.passwordLabel.setText(this.bundle.getString("lblPassword"));
        
        this.themeLabel.setFont(this.font);
        this.themeLabel.setText(this.bundle.getString("lblTheme"));
        
        this.languageLabel.setFont(this.font);
        this.languageLabel.setText(this.bundle.getString("lblLanguage"));
        
        this.themeComboBox.setModel(new ThemeModel());
        this.themeComboBox.setSelectedIndex(Palette.getIndex());
        this.themeComboBox.setBackground(Color.WHITE);
        
        this.languageComboBox.setModel(new LanguageModel());
        this.languageComboBox.setSelectedIndex(Language.getIndex());
        this.languageComboBox.setBackground(Color.WHITE);
        
        this.cancelButton.setText(this.bundle.getString("btnCancel"));
        this.cancelButton.addActionListener(new cancelListener());
        
        this.okButton.setText(this.bundle.getString("btnOk"));
        this.okButton.addActionListener(new okListener());
        
        this.hostTextField.setText(Config.host);
        this.portTextField.setText(Config.port);
        this.databaseTextField.setText(Config.database);
        this.usernameTextField.setText(Config.username);
        this.passwordTextField.setText(Config.password);
        
        this.headerDimension.width = (int) GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getMaximumWindowBounds()
                .getWidth();
        this.headerDimension.height = 64;
        
        this.headerPanel.add(this.titleLabel);
        this.headerPanel.setLayout(new BorderLayout());
        this.headerPanel.setBackground(this.theme);
        this.headerPanel.setPreferredSize(this.headerDimension);
        
        this.configPanel.setLayout(new SpringLayout());
        this.configPanel.setBackground(Color.WHITE);
        this.configPanel.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
        this.configPanel.setPreferredSize(new Dimension(500, 400));
        this.configPanel.add(this.hostLabel);
        this.configPanel.add(this.hostTextField);
        this.configPanel.add(this.portLabel);
        this.configPanel.add(this.portTextField);
        this.configPanel.add(this.databaseLabel);
        this.configPanel.add(this.databaseTextField);
        this.configPanel.add(this.usernameLabel);
        this.configPanel.add(this.usernameTextField);
        this.configPanel.add(this.passwordLabel);
        this.configPanel.add(this.passwordTextField);
        this.configPanel.add(this.themeLabel);
        this.configPanel.add(this.themeComboBox);
        this.configPanel.add(this.languageLabel);
        this.configPanel.add(this.languageComboBox);
        this.configPanel.add(this.cancelButton);
        this.configPanel.add(this.okButton);
        SpringUtilities.makeGrid(this.configPanel,
                         8, 2, //rows, cols
                         0, 0, //initialX, initialY
                         8, 8);//xPad, yPad
        
        this.bodyPanel.setBackground(Color.WHITE);
        this.bodyPanel.add(this.configPanel);
        
        this.setLayout(new BorderLayout());
        this.add(this.headerPanel, BorderLayout.NORTH);
        this.add(this.bodyPanel, BorderLayout.CENTER);
        this.addWindowListener(new windowListener());
    }
    
    private void exit() {
        AdminForm form = new AdminForm();
        form.build();
        dispose();
    }
    
    private class windowListener extends WindowAdapter {
        
        @Override
        public void windowClosing(WindowEvent e) {
            exit();
        }
    }
    
    private class cancelListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            exit();
        }
    }
    
    private class okListener implements ActionListener {

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
                connection.setLanguage(((ItemComboBox) languageComboBox.getSelectedItem()).getValueMember());
                connection.setTheme(((ItemComboBox) themeComboBox.getSelectedItem()).getValueMember());
                
                bus.writeConfig(connection);
                
                exit();
                
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null,
                    exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
