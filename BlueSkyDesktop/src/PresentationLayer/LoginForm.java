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
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.Locale;
import java.util.ResourceBundle;
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
    
    private final Dimension headerDimension;
    
    private static final Locale[] supportedLocales = {
        new Locale("vi", "VN")
    };
    private final ResourceBundle bundle;
    
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
        
        this.bundle = ResourceBundle.getBundle(
                "ResourceBundle.LoginForm", LoginForm.supportedLocales[0]);
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
        
        this.usernameLabel.setFont(bodyLabelFont);
        this.usernameLabel.setText(
                this.bundle.getString("lblUsername") + ": ");
        
        this.passwordLabel.setFont(bodyLabelFont);
        this.passwordLabel.setText(
                this.bundle.getString("lblPassword") + ": ");
        
        this.errorLabel.setFont(bodyLabelFont);
        this.errorLabel.setForeground(Color.RED);
        
        this.loginButton.setText(this.bundle.getString("btnLogin"));
        
        this.headerDimension.width = (int) GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getMaximumWindowBounds()
                .getWidth();
        this.headerDimension.height = 250;
        
        this.headerPanel.add(this.titleLabel);
        this.headerPanel.setLayout(new BorderLayout());
        this.headerPanel.setBackground(new Color(63, 81, 181));
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
}