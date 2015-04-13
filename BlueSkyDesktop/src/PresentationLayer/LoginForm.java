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

import Components.FlatButton;
import Components.SpringUtilities;
import ResourceBundle.Language;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class LoginForm extends JFrame {
    
    private final JLabel titleLabel;
    private final JLabel usernameLabel;
    private final JLabel passwordLabel;
    private final JLabel errorLabel;
    
    private final JPanel headerPanel;
    private final JPanel bodyPanel;
    private final JPanel loginPanel;
    
    private final JTextField usernameTextField;
    private final JTextField passwordTextField;
    
    private final FlatButton loginButton;
    
    private final Font bodyLabelFont;
    private final Font errorFont;
    
    private final Dimension headerDimension;
    
    private Color theme;
    
    private ResourceBundle bundle;
    
    public LoginForm() {
        super("BlueSky");
        
        this.titleLabel = new JLabel();
        this.usernameLabel = new JLabel();
        this.passwordLabel = new JLabel();
        this.errorLabel = new JLabel();
        
        this.headerPanel = new JPanel();
        this.bodyPanel = new JPanel();
        this.loginPanel = new JPanel();
        
        this.usernameTextField = new JTextField(40);
        this.passwordTextField = new JPasswordField(40);
        
        this.loginButton = new FlatButton();
        
        this.headerDimension = new Dimension();
        
        this.bodyLabelFont = new Font("Segoe UI", Font.PLAIN, 16);
        this.errorFont = new Font("Segoe UI", Font.PLAIN, 12);
    }
    
    public void build() {
        this.initialize();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getMaximumWindowBounds().getSize());
        this.setVisible(true);
    }
    
    private void initialize() {
        this.theme = Palette.getTheme();
        
        this.bundle = ResourceBundle.getBundle(
                "ResourceBundle.LoginForm", Language.getLanguage());
        
        this.titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 56));
        this.titleLabel.setForeground(Color.WHITE);
        this.titleLabel.setLocation(90, 115);
        this.titleLabel.setSize(800, 100);
        this.titleLabel.setText(
                "<html><body>" +
                    "BlueSky - " +
                    "<span style=\"font-size: 32px; font-weight: 400;\">" +
                        this.bundle.getString("lblTitle") +
                    "</span>" +
                "</body></html>"
        );
        
        this.usernameLabel.setFont(this.bodyLabelFont);
        this.usernameLabel.setText(
                this.bundle.getString("lblUsername") + ": ");
        
        this.passwordLabel.setFont(this.bodyLabelFont);
        this.passwordLabel.setText(
                this.bundle.getString("lblPassword") + ": ");
        
        this.errorLabel.setFont(this.errorFont);
        this.errorLabel.setForeground(Color.RED);

        this.loginButton.setText(this.bundle.getString("btnLogin"));
        this.loginButton.addActionListener(new loginListener());
        
        this.headerDimension.width = (int) GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getMaximumWindowBounds()
                .getWidth();
        this.headerDimension.height = 250;
        
        this.headerPanel.add(this.titleLabel);
        this.headerPanel.setLayout(new BorderLayout());
        this.headerPanel.setBackground(this.theme);
        this.headerPanel.setPreferredSize(this.headerDimension);
        
        this.loginPanel.setLayout(new SpringLayout());
        this.loginPanel.setBackground(Color.WHITE);
        this.loginPanel.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
        this.loginPanel.setPreferredSize(new Dimension(400, 200));
        this.loginPanel.add(this.usernameLabel);
        this.loginPanel.add(this.usernameTextField);
        this.loginPanel.add(this.passwordLabel);
        this.loginPanel.add(this.passwordTextField);
        this.loginPanel.add(this.errorLabel);
        this.loginPanel.add(this.loginButton);
        SpringUtilities.makeGrid(this.loginPanel,
                         3, 2, //rows, cols
                         0, 0, //initialX, initialY
                         8, 8);//xPad, yPad
        
        this.bodyPanel.setBackground(Color.WHITE);
        this.bodyPanel.add(this.loginPanel);
        
        this.setLayout(new BorderLayout());
        this.add(this.headerPanel, BorderLayout.NORTH);
        this.add(this.bodyPanel, BorderLayout.CENTER);
    }
    
    private class loginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String usernamePattern = "^[a-zA-Z0-9]{4,}$";
            String passwordPattern = "^[a-zA-Z0-9!@#$%^&*?_~]{7,}$";
            
            Pattern regex = Pattern.compile(usernamePattern);
            Matcher matcher = regex.matcher(usernameTextField.getText());
            
            if (matcher.matches() == false) {   // Check regex for username
                errorLabel.setText(bundle.getString("errUsername"));
            } else {
                regex = Pattern.compile(passwordPattern);
                matcher = regex.matcher(passwordTextField.getText());
                
                if (matcher.matches() == false) {   // Check regex for password
                    errorLabel.setText(bundle.getString("errPassword"));
                } else {
                    AdminForm app = new AdminForm();
                    app.build();
                    dispose();
                }
            }
        }
    }
}