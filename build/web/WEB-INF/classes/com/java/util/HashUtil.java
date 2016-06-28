/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author Pinkesh
 */
public class HashUtil {

    public static String getHash(String password, String salt) throws Exception {
        String str = DigestUtils.sha256Hex(salt + password);
        return(str);
    }
    
    public static String getSalt(int size) throws Exception {
        return(RandomStringUtils.randomAscii(size));
    }
    
    public static String getRandomString(int size) throws Exception {
        return(RandomStringUtils.randomAlphanumeric(size));
    }
    
    public static String getHash(String input) throws Exception {
        String str = DigestUtils.sha256Hex(input);
        return(str);
    }
}
