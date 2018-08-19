/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchain_test2;

import java.security.PublicKey;

/**
 *
 * @author mej
 */
public class Outputs {
    
    transient PublicKey addressPub;
    public String address = "";
    public Float value;
    
    public Outputs(PublicKey address, Float value) {
        this.addressPub = address;
        this.address = StringUtil.getStringFromKey(address);
        this.value = value*-1;
    }
    
    public PublicKey getAddress() {
        return this.addressPub;
    }
    
    public boolean addrEqual(PublicKey address2) {
        if(getAddress().equals(address2)) {
            return true;
        } else {
            return false;
        }
    }
}
