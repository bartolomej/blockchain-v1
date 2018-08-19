/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchain_test2;

import java.io.Serializable;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.ECGenParameterSpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mej
 */
public class Wallet implements Serializable{
    
    public PublicKey publicKey;
    public PrivateKey privateKey;
    
    public Wallet() {
        generateKeyPair();
    }
    
    
    /**
     * generate key pair with ECDSA algorithm (smaller signature, private key)
     * KeyPairGenerator generates keys using eliptic curve cryptography
     */
    
    public void generateKeyPair() {
		try {
                    Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");//"ECDSA","BC");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			//ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
			keyGen.initialize(256, random); 
	        	KeyPair keyPair = keyGen.generateKeyPair();
	        	this.privateKey = keyPair.getPrivate();
	        	this.publicKey = keyPair.getPublic();
		}catch(NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
        }
    
    public void getBalance() {
        
    }
    
}
