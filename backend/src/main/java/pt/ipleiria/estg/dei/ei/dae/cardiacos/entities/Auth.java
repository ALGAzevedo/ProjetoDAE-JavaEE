package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.DatatypeConverter;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "findToken",
                query = "SELECT s FROM Auth s WHERE s.token = :token" // JPQL
        )
})
public class Auth extends BaseEntity{
    @Id
    private String username;
    private String password;
    private String token;
    @NotNull
    //Nem sei se é necessário
    @Column(columnDefinition="BOOLEAN DEFAULT false")
    private boolean isActive;
    private boolean isExpired;

    public Auth() {
    }

    public Auth(String username, String token) {
        this.username = username;
        this.password = null;
        this.isActive = false;
        this.isExpired = false;
        this.token = token;
        //this.token = generateVerifyHash(username);
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }

    public String generateVerifyHash(String username){
        //Generate hash based on user email
        String verificationToken = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(username.getBytes(StandardCharsets.UTF_8));
            verificationToken = DatatypeConverter.printHexBinary(digest).toLowerCase();

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return verificationToken;
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
