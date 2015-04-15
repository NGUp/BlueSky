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
import java.security.NoSuchAlgorithmException;
import org.junit.Test;
import static org.junit.Assert.*;

public class CryptographySpec {
    private final Cryptography crypto;
    
    public CryptographySpec() {
        this.crypto = new Cryptography();
    }
    
    /**
     * Testing MD5 hash generator
     * 
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException 
     */
    @Test
    public void getMD5Spec() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String plainText = "9dbh32kzkj29de0941e85e63d16574f31fc3e38b43d8fb4827a32jsa9d82h";
        String cypherText = "d82e19712ce493b0df734368482b93bf";
        assertEquals(cypherText, this.crypto.getMD5(plainText));
    }
    
    /**
     * Testing SHA-1 hash generator
     * 
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException 
     */
    @Test
    public void getSHA1Spec() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String plainText = "bluesky";
        String cypherText = "9de0941e85e63d16574f31fc3e38b43d8fb4827a";
        assertEquals(cypherText, this.crypto.getSHA1(plainText));
    }
}
