/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Security;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 *
 * @author Niyonkuru Moise
 */
public class passwordEncode {
    public String password_encoding(String password){
        Base64.Encoder bs=Base64.getEncoder();
        
        return bs.encodeToString(password.getBytes(StandardCharsets.UTF_8));
    }
    public String password_decoding(String password){
        Base64.Decoder bs=Base64.getDecoder();
        
        return new String(bs.decode(password));
    }
    
}
