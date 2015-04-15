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

import Core.Config;
import java.awt.Color;

public class Palette {
    
    /**
     * Get theme with Material Design Palette
     * 
     * @return Color
     */
    public static Color getTheme() {
        if (null != Config.theme) switch (Config.theme) {
            case "Red":
                return new Color(244, 67, 54);
            case "Pink":
                return new Color(233, 30, 99);
            case "Purple":
                return new Color(156, 39, 176);
            case "DeepPurple":
                return new Color(103, 58, 183);
            case "Blue":
                return new Color(33, 150, 243);
            case "LightBlue":
                return new Color(3, 169, 244);
            case "Cyan":
                return new Color(0, 188, 212);
            case "Teal":
                return new Color(0,150,136);
            case "Green":
                return new Color(76, 175, 80);
            case "LightGreen":
                return new Color(139, 195, 74);
            case "Lime":
                return new Color(205, 220, 57);
            case "Yellow":
                return new Color(255, 235, 59);
            case "Amber":
                return new Color(255, 193, 7);
            case "Orange":
                return new Color(255, 152, 0);
            case "DeepOrange":
                return new Color(255, 87, 34);
            case "Brown":
                return new Color(121, 85, 72);
            case "Gray":
                return new Color(158, 158, 158);
            case "BlueGray":
                return new Color(96, 125, 139);
        }
        
        return new Color(63, 81, 181);  // Indigo
    }
    
    /**
     * Get index of current theme
     *
     * @return Integer
     */
    public static int getIndex() {
        if (null != Config.theme) switch (Config.theme) {
            case "Red":
                return 0;
            case "Pink":
                return 1;
            case "Purple":
                return 2;
            case "DeepPurple":
                return 3;
            case "Blue":
                return 5;
            case "LightBlue":
                return 6;
            case "Cyan":
                return 7;
            case "Teal":
                return 8;
            case "Green":
                return 9;
            case "LightGreen":
                return 10;
            case "Lime":
                return 11;
            case "Yellow":
                return 12;
            case "Amber":
                return 13;
            case "Orange":
                return 14;
            case "DeepOrange":
                return 15;
            case "Brown":
                return 16;
            case "Gray":
                return 17;
            case "BlueGray":
                return 18;
        }
        
        return 4;  // Indigo
    }
}
