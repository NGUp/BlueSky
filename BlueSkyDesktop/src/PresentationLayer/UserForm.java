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

import ResourceBundle.Language;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserForm extends JFrame {
    private final JLabel titleLabel;
    
    private final JPanel headerPanel;
    
    private final Dimension headerDimension;
    
    private Color theme;
    
    private ResourceBundle bundle;
    
    public UserForm() {
        super("BlueSky");
        
        this.titleLabel = new JLabel();
        
        this.headerPanel = new JPanel();
        
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
                "ResourceBundle.UserForm", Language.getLanguage());
        
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
        
        this.headerDimension.width = (int) GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getMaximumWindowBounds()
                .getWidth();
        this.headerDimension.height = 64;
        
        this.headerPanel.add(this.titleLabel);
        this.headerPanel.setLayout(new BorderLayout());
        this.headerPanel.setBackground(this.theme);
        this.headerPanel.setPreferredSize(this.headerDimension);
        
        this.setLayout(new BorderLayout());
        this.add(this.headerPanel, BorderLayout.NORTH);
        this.addWindowListener(new windowListener());
        
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("lib/form/favicon.png"));
    }
    
    private class windowListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            AdminForm form = new AdminForm();
            form.build();
            dispose();
        }
    }
}
