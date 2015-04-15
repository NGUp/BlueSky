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

import Components.FeaturePanel;
import ResourceBundle.Language;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AdminForm extends JFrame {
    
    private final JLabel titleLabel;
    
    private final JPanel headerPanel;
    private final JPanel bodyPanel;
    
    private final Dimension headerDimension;
    
    private Color theme;
    
    private ResourceBundle bundle;
    
    public AdminForm() {
        super("BlueSky");
        
        this.titleLabel = new JLabel();
        
        this.headerPanel = new JPanel();
        this.bodyPanel = new JPanel();
        
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
                "ResourceBundle.AdminForm", Language.getLanguage());
        
        GridLayout bodyLayout = new GridLayout();
        bodyLayout.setColumns(2);
        bodyLayout.setRows(0);
        bodyLayout.setHgap(100);
        bodyLayout.setVgap(30);
        
        this.titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 36));
        this.titleLabel.setForeground(Color.WHITE);
        this.titleLabel.setLocation(90, 115);
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
        
        ImageIcon privacyIcon = new ImageIcon("lib/form/privacy.png");
        ImageIcon employeeIcon = new ImageIcon("lib/form/employee.png");
        ImageIcon userIcon = new ImageIcon("lib/form/user.png");
        ImageIcon configIcon = new ImageIcon("lib/form/config.png");
        
        FeaturePanel employeePanel = new FeaturePanel(
                this.bundle.getString("lblEmployee"), employeeIcon.getImage());
        employeePanel.addMouseListener(new employeeListener());
        
        FeaturePanel privacyPanel = new FeaturePanel(
                this.bundle.getString("lblPrivacy"), privacyIcon.getImage());
        privacyPanel.addMouseListener(new privacyListener());
        
        FeaturePanel userPanel = new FeaturePanel(
                this.bundle.getString("lblUser"), userIcon.getImage());
        userPanel.addMouseListener(new userListener());
        
        FeaturePanel configPanel = new FeaturePanel(
                this.bundle.getString("lblConfig"), configIcon.getImage());
        configPanel.addMouseListener(new configListener());
        
        JPanel bodyContentPanel = new JPanel();
        
        this.headerDimension.width = (int) GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getMaximumWindowBounds()
                .getWidth();
        this.headerDimension.height = 250;
        
        this.headerPanel.add(this.titleLabel);
        this.headerPanel.setLayout(new BorderLayout());
        this.headerPanel.setBackground(this.theme);
        this.headerPanel.setPreferredSize(this.headerDimension);
        
        bodyContentPanel.setBorder(BorderFactory.createEmptyBorder(16, 0, 0, 0));
        bodyContentPanel.setLayout(bodyLayout);
        bodyContentPanel.setBackground(Color.WHITE);
        bodyContentPanel.setSize(700, 300);
        bodyContentPanel.add(privacyPanel);
        bodyContentPanel.add(configPanel);
        bodyContentPanel.add(employeePanel);
        bodyContentPanel.add(userPanel);
        
        this.bodyPanel.setLayout(new FlowLayout());
        this.bodyPanel.setBackground(Color.WHITE);
        this.bodyPanel.add(bodyContentPanel);
        
        this.setLayout(new BorderLayout());
        this.add(this.headerPanel, BorderLayout.NORTH);
        this.add(this.bodyPanel, BorderLayout.CENTER);
        this.addWindowListener(new windowListener());
        
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("lib/form/favicon.png"));
    }
    
    private class windowListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            LoginForm form = new LoginForm();
            form.build();
            dispose();
        }
    }
    
    private class employeeListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            EmployeeForm form = new EmployeeForm();
            form.build();
            dispose();
        }
    }
    
    private class privacyListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            PrivacyForm form = new PrivacyForm();
            form.build();
            dispose();
        }
    }
    
    private class userListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            UserForm form = new UserForm();
            form.build();
            dispose();
        }
    }
    
    private class configListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            ConfigForm form = new ConfigForm();
            form.build();
            dispose();
        }
    }
}
