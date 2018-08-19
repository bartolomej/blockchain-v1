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
public class HiddenInputs {
    
    private PublicKey addressPub;
    
    public HiddenInputs(PublicKey address) {
        this.addressPub = address;
    }
    
    public PublicKey getAddressPub() {
        return this.addressPub;
    }
    
    public boolean addrEqual(PublicKey address2) {
        if(getAddressPub().equals(address2)) {
            return true;
        } else {
            return false;
        }
    }
    
}
