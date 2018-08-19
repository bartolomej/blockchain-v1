/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchain_test2;

import com.google.gson.GsonBuilder;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import static org.bouncycastle.crypto.tls.CipherType.block;

/**
 *
 * @author mej
 */
public class Chain {
    
    Block block;
    
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    
    public static float minimumTransaction = 0.1f;
    
    public static Wallet coinbase;
    public static Wallet wallet;
    
    public static Block genesisB;
    
    public static Transaction genesisT;
    
    public static void main(String[] args) {
        
        JFrame frame = new Frame1();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public static void addBlock(Block block) throws UnsupportedEncodingException {
        block.mineBlock();
        blockchain.add(block);
    }
    
    
}
