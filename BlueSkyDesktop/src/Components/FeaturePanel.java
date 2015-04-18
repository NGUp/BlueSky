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
package Components;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FeaturePanel extends JPanel {
    private final JLabel title;
    
    private final FlowLayout layout;
    
    private final ImagePanel imagePanel;
    
    public FeaturePanel(String title, Image imageIcon) {
        this.imagePanel = new ImagePanel(imageIcon);
        this.layout = new FlowLayout();
        
        this.title = new JLabel();
        this.title.setText(title);
        this.title.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        this.title.setForeground(new Color(33, 33, 33));
        this.layout.setAlignment(FlowLayout.LEFT);
        this.setBackground(Color.WHITE);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setLayout(this.layout);
        this.setComponentOrientation(
                ComponentOrientation.LEFT_TO_RIGHT);
        this.add(this.imagePanel);
        this.add(this.title);
    }
}