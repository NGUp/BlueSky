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
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class PrivacyForm extends JFrame {
    private final JLabel titleLabel;
    private final JLabel newPasswordLabel;
    private final JLabel confirmPasswordLabel;
    private final JLabel oldPasswordLabel;
    
    private final JPanel headerPanel;
    private final JPanel privacyPanel;
    private final JPanel bodyPanel;
    
    private final JTextField oldPaswordTextField;
    private final JTextField newPaswordTextField;
    private final JTextField confirmPaswordTextField;
    
    private final FlatButton cancelButton;
    private final FlatButton okButton;
    
    private final Font font;
    
    private final Dimension headerDimension;
    
    private Color theme;
    
    private ResourceBundle bundle;
    
    public PrivacyForm() {
        super("BlueSky");
        
        this.titleLabel = new JLabel();
        this.oldPasswordLabel = new JLabel();
        this.newPasswordLabel = new JLabel();
        this.confirmPasswordLabel = new JLabel();
        
        this.headerPanel = new JPanel();
        this.privacyPanel = new JPanel();
        this.bodyPanel = new JPanel();
        
        this.oldPaswordTextField = new JTextField();
        this.newPaswordTextField = new JTextField();
        this.confirmPaswordTextField = new JTextField();
        
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
                "ResourceBundle.PrivacyForm", Language.getLanguage());
        
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
        
        this.oldPasswordLabel.setFont(font);
        this.oldPasswordLabel.setText(
                this.bundle.getString("lblOldPassword") + ": ");
        
        this.newPasswordLabel.setFont(font);
        this.newPasswordLabel.setText(
                this.bundle.getString("lblNewPassword") + ": ");
        
        this.confirmPasswordLabel.setFont(font);
        this.confirmPasswordLabel.setText(
                this.bundle.getString("lblConfirmPassword") + ": ");
        
        this.cancelButton.setText(
                this.bundle.getString("btnCancel") + ": ");
        this.cancelButton.addActionListener(new cancelListener());
        
        this.okButton.setText(
                this.bundle.getString("btnOK") + ": ");
        
        this.headerDimension.width = (int) GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getMaximumWindowBounds()
                .getWidth();
        this.headerDimension.height = 64;
        
        this.headerPanel.add(this.titleLabel);
        this.headerPanel.setLayout(new BorderLayout());
        this.headerPanel.setBackground(this.theme);
        this.headerPanel.setPreferredSize(this.headerDimension);
        
        this.privacyPanel.setLayout(new SpringLayout());
        this.privacyPanel.setBackground(Color.WHITE);
        this.privacyPanel.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
        this.privacyPanel.setPreferredSize(new Dimension(500, 250));
        this.privacyPanel.add(this.oldPasswordLabel);
        this.privacyPanel.add(this.oldPaswordTextField);
        this.privacyPanel.add(this.newPasswordLabel);
        this.privacyPanel.add(this.newPaswordTextField);
        this.privacyPanel.add(this.confirmPasswordLabel);
        this.privacyPanel.add(this.confirmPaswordTextField);
        this.privacyPanel.add(this.cancelButton);
        this.privacyPanel.add(this.okButton);
        SpringUtilities.makeGrid(this.privacyPanel,
                         4, 2, //rows, cols
                         0, 0, //initialX, initialY
                         8, 8);//xPad, yPad
        
        this.bodyPanel.add(privacyPanel);
        this.bodyPanel.setBackground(Color.WHITE);
        
        this.setLayout(new BorderLayout());
        this.add(this.headerPanel, BorderLayout.NORTH);
        this.add(this.bodyPanel, BorderLayout.CENTER);
        this.addWindowListener(new windowListener());
        
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("lib/form/favicon.png"));
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
}
