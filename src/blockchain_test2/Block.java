/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchain_test2;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mej
 */
public class Block {
    
    public int index = 0;
    private int difficulty = 4; //constant value SET HERE !
    private long timestamp;
    private long nonce;
    public String blockHash;
    public String previousBlockHash;
    public String merkleRoot;
    public String data;
    
    public ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    
    public Block(int index, String previousBlockHash, String hashes) throws UnsupportedEncodingException {
        this.index = index;
        System.out.println("INDEX: "+this.index);
        this.previousBlockHash = previousBlockHash;
        this.timestamp = new Date().getTime();
        this.merkleRoot = hashes;
        System.out.println("HASHES: " + this.merkleRoot);
        this.blockHash = calculateHash();
    }

    
    public String calculateHash() throws UnsupportedEncodingException {
        String calculateHash = StringHash.applySha256Hash(
                                        previousBlockHash +
                                        Long.toString(timestamp) + 
                                        merkleRoot +
                                        nonce
        );
        return calculateHash;
    }
    
    public void mineBlock() throws UnsupportedEncodingException {
        String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0" 
		while(!blockHash.substring( 0, difficulty).equals(target)) {
			nonce ++;
                        blockHash = calculateHash();
		}
    }
    
    public boolean addTransaction(Transaction transaction) throws UnsupportedEncodingException {
        if(transaction == null) return false;
            /*if(transaction.processTransaction() == true) {
                System.out.println("Transaction added!");
                transactions.add(transaction);
                return true;
            } else {
                return false;
            } */
        transactions.add(transaction);
        return true;
    }
}
