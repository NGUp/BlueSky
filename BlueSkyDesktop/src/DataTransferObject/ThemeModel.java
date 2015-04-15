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
package DataTransferObject;

import ResourceBundle.Language;
import java.util.ResourceBundle;
import javax.swing.DefaultComboBoxModel;

public class ThemeModel extends DefaultComboBoxModel {
    private ResourceBundle bundle;
    
    public ThemeModel() {
        this.bundle = ResourceBundle.getBundle(
                "ResourceBundle.ThemeModel", Language.getLanguage());
        
        this.addElement(new ItemComboBox("Red", this.bundle.getString("colorRed")));
        this.addElement(new ItemComboBox("Pink", this.bundle.getString("colorPink")));
        this.addElement(new ItemComboBox("Purple", this.bundle.getString("colorPurple")));
        this.addElement(new ItemComboBox("DeepPurple", this.bundle.getString("colorDeepPurple")));
        this.addElement(new ItemComboBox("Indigo", this.bundle.getString("colorIndigo")));
        this.addElement(new ItemComboBox("Blue", this.bundle.getString("colorBlue")));
        this.addElement(new ItemComboBox("LightBlue", this.bundle.getString("colorLightBlue")));
        this.addElement(new ItemComboBox("Cyan", this.bundle.getString("colorCyan")));
        this.addElement(new ItemComboBox("Teal", this.bundle.getString("colorTeal")));
        this.addElement(new ItemComboBox("Green", this.bundle.getString("colorGreen")));
        this.addElement(new ItemComboBox("LightGreen", this.bundle.getString("colorLightGreen")));
        this.addElement(new ItemComboBox("Lime", this.bundle.getString("colorLime")));
        this.addElement(new ItemComboBox("Yellow", this.bundle.getString("colorYellow")));
        this.addElement(new ItemComboBox("Amber", this.bundle.getString("colorAmber")));
        this.addElement(new ItemComboBox("Orange", this.bundle.getString("colorOrange")));
        this.addElement(new ItemComboBox("DeepOrange", this.bundle.getString("colorDeepOrange")));
        this.addElement(new ItemComboBox("Brown", this.bundle.getString("colorBrown")));
        this.addElement(new ItemComboBox("Gray", this.bundle.getString("colorGray")));
        this.addElement(new ItemComboBox("BlueGray", this.bundle.getString("colorBlueGray")));
    }
}
