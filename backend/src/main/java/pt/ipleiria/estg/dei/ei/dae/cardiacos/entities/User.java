package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities;


import io.smallrye.common.constraint.Nullable;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Country;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Gender;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.MaritalStatus;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(
                name = "getWithEmail",
                query = "SELECT s FROM User s WHERE s.email = :email" // JPQL
        )
})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User extends BaseEntity{

    @Id
    @NotNull
    private String username;
    @NotNull
    private String name;
    @Column(unique=true)
    @Email
    @NotNull
    private String email;
    @NotNull
    @Enumerated(EnumType.STRING) // [by Jerry]
    private Gender gender;
    @NotNull
    private LocalDate birthdate;
    @Enumerated(EnumType.STRING)
    private Country country;
    @NotNull
    @Pattern(regexp="^[0-9]{11}$",
            message="Invalid Social Security Number")
    private String socialSecurityNumber;

    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;
    private String address;
    private String city;
    private String postalCode;
    @NotNull
    @Pattern(regexp="^[9][0-9]{8}$",
            message="Invalid Phone Number")
    private String phoneNumber;
    @Nullable
    @Pattern(regexp="^[9][0-9]{8}$",
            message="Invalid Emergency Phone Number")
    private String emergencyPhoneNumber;

    private Date lastLogin;
    private Date isDeleted;



    //CONSTRUCTORS
    public User() {
    }

    public User(String name, String username, String email,
                Gender gender, LocalDate birthdate, Country country, String socialSecurityNumber,MaritalStatus maritalStatus, String address, String city,
                String postalCode, String phoneNumber, String emergencyPhoneNumber) {

        this.name = name;
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.birthdate = birthdate;
        this.country = country;
        this.socialSecurityNumber = socialSecurityNumber;
        this.maritalStatus = maritalStatus;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.emergencyPhoneNumber = emergencyPhoneNumber;
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

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthDate) {
        this.birthdate = birthDate;
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

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
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

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmergencyPhoneNumber() {
        return emergencyPhoneNumber;
    }

    public void setEmergencyPhoneNumber(String emergencyPhoneNumber) {
        this.emergencyPhoneNumber = emergencyPhoneNumber;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Date getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Date isDeleted) {
        this.isDeleted = isDeleted;
    }


}
