/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchain_test2;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author mej
 */

class TransactionVar {
    
}

class Coinbase {
    static Wallet coinbaseWallet = new Wallet();
    public static PublicKey coinbaseAddress = coinbaseWallet.publicKey;
    private PrivateKey privateKey = coinbaseWallet.privateKey;
    static PublicKey recieverAddr;
}

public class Transaction {
    
    transient public static PublicKey senderAddr;
    transient public static PublicKey recieverAddr;
    transient static byte[] signature;
    
    public static int tx_count= 0;
    public String tx_hash;
    public float tx_value;
    public String senderAddress;
    public String recieverAddress;
    public String sig;
    public Outputs outputs;
    public Inputs inputs;
    
    
    // COINBASE
    //public Inputs coinbase_input;
    
    /**
     *
     * @param from
     * @param to
     * @param value
     */
    public Transaction(PublicKey from, PublicKey to, float value, String type) {
        if(type.equals("coinbase")) {
            Coinbase.recieverAddr = to;
            this.recieverAddress = StringUtil.getStringFromKey(to);
            tx_value = value;
        } else if(type.equals("standard")) {
            this.senderAddr = from;
            this.senderAddress = StringUtil.getStringFromKey(from);
            //System.out.println("FROM: "+from);
            this.recieverAddr = to;
            this.recieverAddress = StringUtil.getStringFromKey(to);
            //System.out.print("TO: "+to);
            this.tx_value = value;
        }
    }
    
    public String calculateHash() { 
        tx_count++;
        return StringUtil.applySha256(
				StringUtil.getStringFromKey(this.senderAddr) +
				StringUtil.getStringFromKey(this.recieverAddr) +
				Float.toString(tx_value) + 
                                tx_count
                            );
    }
    
    public String calculateCoinbaseHash() {
        tx_count++;
        return StringUtil.applySha256(
				StringUtil.getStringFromKey(Coinbase.coinbaseAddress) +
				StringUtil.getStringFromKey(Coinbase.recieverAddr) +
				Float.toString(tx_value) + 
                                tx_count
                            );
    }
    
    /*
     generates signature with private key
     signature(privateKey, sender + reciever + value)
    */
    public void generateSignature(PrivateKey privateKey) throws UnsupportedEncodingException {
        //byte[] signature;
        String data = StringUtil.getStringFromKey(this.senderAddr) + 
                StringUtil.getStringFromKey(this.recieverAddr) + 
                Float.toString(tx_value)	;
        this.signature = StringUtil.applyECDSASig(privateKey, data);
        this.sig = new String(this.signature, "UTF-8");
        //System.out.println(this.sig);
    }
    
    public void generateCoinbaseSig() throws UnsupportedEncodingException {
        String data = StringUtil.getStringFromKey(Coinbase.coinbaseAddress) + 
                this.recieverAddress + 
                Float.toString(tx_value)	;
        this.signature = StringUtil.applyECDSASig(Coinbase.coinbaseWallet.privateKey, data);
        this.sig = new String(this.signature, "UTF-8");
        //System.out.println(this.sig);
    }
    
    /* 
     verifies signature with public key (sender)
     verify(sender, sender + reciever + value, signature)
     used to check if this transaction was created by original creator
    */
    public boolean verifySignature() throws UnsupportedEncodingException {
        String data = StringUtil.getStringFromKey(this.senderAddr) + 
                StringUtil.getStringFromKey(this.recieverAddr) + 
                Float.toString(tx_value);
        //byte[] signature = sig.getBytes("UTF-8");
        return StringUtil.verifyECDSASig(this.senderAddr, data, this.signature);
    }
    
    
    public boolean processTransaction() throws UnsupportedEncodingException {
		
	if(verifySignature() == false) {
		System.out.println("####Transaction Signature failed to verify");
		return false;
        }
        
        System.out.println("Transaction signature verified");
        //output = new Output(this.senderAddr, this.tx_value);
        //outputs.put(StringUtil.getStringFromKey(senderAddr), tx_value);
        outputs = new Outputs(this.senderAddr, this.tx_value);
        //outputs.add(outputs1);
        //System.out.println("PROCESSTR ... senderAddr: "+TransactionVar.senderAddr);
        inputs = new Inputs(this.recieverAddr, this.tx_value);
        //System.out.println("PROCESSTR ... senderAddr: "+TransactionVar.recieverAddr);
        //inputs.put(StringUtil.getStringFromKey(recieverAddr), tx_value);
        //inputs.add(new Inputs(this.recieverAddr, this.tx_value));
        
        tx_hash = calculateHash();
        
	return true;
    }
    
    public void coinbaseTransaction() {
        inputs = new Inputs(Coinbase.recieverAddr, tx_value);
        tx_hash = calculateCoinbaseHash();
    }
    
}
 