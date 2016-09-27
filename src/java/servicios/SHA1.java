/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

/**
 *
 * @author usuario
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import org.eclipse.jdt.internal.compiler.batch.Main;

public class SHA1 {
    private MessageDigest md;
    private byte[] buffer, digest;
    private String hash = "";

    public String getHash(String message) throws NoSuchAlgorithmException {
        buffer = message.getBytes();
        md = MessageDigest.getInstance("SHA1");
        md.update(buffer);
        digest = md.digest();

        for(byte aux : digest) {
            int b = aux & 0xff;
            if (Integer.toHexString(b).length() == 1) hash += "0";
            hash += Integer.toHexString(b);
        }
     return hash;
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException, NoSuchAlgorithmException {
        SHA1 s = new SHA1();
        String pass="123456";
         
         System.out.println(s.getHash(s.getHash(pass)));
    }

}

