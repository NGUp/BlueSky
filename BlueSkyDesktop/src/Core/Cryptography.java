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
package Core;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Cryptography {
    
    public Cryptography() {
        
    }
    
    /**
     * MD5 hash generator
     * 
     * @param hash String PlainText
     * @return String CypherText
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException 
     */
    public String getMD5(String hash) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        byte[] bytesOfHash = hash.getBytes("UTF-8");
        byte[] theDigest = digest.digest(bytesOfHash);
        
        StringBuffer hexString = new StringBuffer();
        for (int index = 0; index < theDigest.length; index++) {
            String hex = Integer.toHexString(0xff & theDigest[index]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        
        return hexString.toString();
    }
    
    /**
     * SHA-1 hash generator
     * 
     * @param hash String PlainText
     * @return String CypherText
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException 
     */
    public String getSHA1(String hash) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        byte[] bytesOfHash = hash.getBytes("UTF-8");
        byte[] theDigest = digest.digest(bytesOfHash);
        
        StringBuffer hexString = new StringBuffer();
        for (int index = 0; index < theDigest.length; index++) {
            String hex = Integer.toHexString(0xff & theDigest[index]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        
        return hexString.toString();
    }
}
