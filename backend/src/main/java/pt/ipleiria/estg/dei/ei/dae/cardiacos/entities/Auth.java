package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Entity
public class Auth extends BaseEntity{
    @Id
    private String username;
    private String password;
    private String token;
    private boolean isActive;

    public Auth() {
    }

    public Auth(String username, String password, String token) {
        this.username = username;
        this.password = hashPassword(password);
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = hashPassword(password);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean state) {
        this.isActive = state;
    }



    //PASSWORD HASH

    public static String hashPassword(String password) {
        char[] encoded = null;
        try {
            ByteBuffer passwdBuffer =
                    Charset.defaultCharset().encode(CharBuffer.wrap(password));
            byte[] passwdBytes = passwdBuffer.array();
            MessageDigest mdEnc = MessageDigest.getInstance("SHA-256");
            mdEnc.update(passwdBytes, 0, password.toCharArray().length);
            encoded = new BigInteger(1, mdEnc.digest()).toString(16).toCharArray();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new String(encoded);
    }
}
