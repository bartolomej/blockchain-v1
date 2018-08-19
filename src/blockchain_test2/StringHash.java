/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchain_test2;

import blockchain_test1.*;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mej
 */
public class StringHash {
    
    public static String applySha256Hash (String input) throws UnsupportedEncodingException {
        MessageDigest msgDig;
        try {
            msgDig = MessageDigest.getInstance("SHA-256");
            byte[] bytes = msgDig.digest(input.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
            
            for(int i=0; i<bytes.length; i++) {
                String hex = Integer.toHexString(0xff & bytes[i]);
		if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            
            return hexString.toString();
            
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(StringHash.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
        
        
        
        
    }
}
