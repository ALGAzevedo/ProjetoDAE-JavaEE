package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities;


import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Country;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Gender;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.MaritalStatus;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User extends BaseEntity{

    @Id
    private String username;
    @NotNull
    private String name;
    @Column(unique=true)
    @Email
    @NotNull
    private String email;
    @NotNull
    private Gender gender;
    @NotNull
    private Date birthDate;
    @NotNull
    private Country country;
    @NotNull
    @Pattern(regexp="^[0-9]{11}$",
            message="{invalid.social_security_number}")
    private String socialSecurityNumber;

    @NotNull
    private String password;

    private MaritalStatus maritalStatus;
    private String address;
    private String city;
    private String postalCode;
    private String phoneNumber;
    private String emergencyPhoneNumber;

    private Date lastLogin;
    private Date isDeleted;

    //CONSTRUCTORS
    public User() {
    }

    public User(String name, String username, String email,
                Gender gender, Date birthDate, Country country, String social_security_number,
                String password, MaritalStatus maritalStatus, String address, String city,
                String postal_code, String phone_number, String emergency_phone_number) {

        this.name = name;
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.birthDate = birthDate;
        this.country = country;
        this.socialSecurityNumber = social_security_number;
        this.password = hashPassword(password);
        this.maritalStatus = maritalStatus;
        this.address = address;
        this.city = city;
        this.postalCode = postal_code;
        this.phoneNumber = phone_number;
        this.emergencyPhoneNumber = emergency_phone_number;
    }

    //GETTERS AND SETTERS

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String social_security_number) {
        this.socialSecurityNumber = social_security_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postal_code) {
        this.postalCode = postal_code;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phone_number) {
        this.phoneNumber = phone_number;
    }

    public String getEmergencyPhoneNumber() {
        return emergencyPhoneNumber;
    }

    public void setEmergencyPhoneNumber(String emergency_phone_number) {
        this.emergencyPhoneNumber = emergency_phone_number;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date last_login) {
        this.lastLogin = last_login;
    }

    public Date getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Date is_deleted) {
        this.isDeleted = is_deleted;
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
