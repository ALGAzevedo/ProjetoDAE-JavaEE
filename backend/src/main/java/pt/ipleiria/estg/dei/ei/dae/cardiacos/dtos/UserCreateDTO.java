package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Country;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Gender;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.MaritalStatus;

import java.util.Date;
import java.util.GregorianCalendar;

public class UserCreateDTO implements DTO{

    private String name;
    private String username;
    private String email;
    private Gender gender;
    private Date birthDate;
    private Country country;
    private String socialSecurityNumber;
    private String password;
    private MaritalStatus maritalStatus;
    private String address;
    private String city;
    private String postalCode;
    private String phoneNumber;
    private String emergencyPhoneNumber;
    private int birthDateYear;
    private int birthDateMonth;
    private int birthDateDay;



    public UserCreateDTO() {
    }

    public UserCreateDTO(String name, String username, String email, Gender gender,
                         int birthDateYear, int birthDateMonth, int birthDateDay, Country country, String social_security_number,
                         String password, MaritalStatus maritalStatus, String address, String city,
                         String postal_code, String phone_number, String emergency_phone_number){


        this.name = name;
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.birthDateDay = birthDateDay;
        this.birthDateMonth = birthDateMonth;
        this.birthDateYear = birthDateYear;
        this.birthDate = new Date(1959, 5, 9);
        this.birthDate = setBirthDate(birthDateYear, birthDateMonth, birthDateDay);
        this.country = country;
        this.socialSecurityNumber = social_security_number;
        this.password = password;
        this.maritalStatus = maritalStatus;
        this.address = address;
        this.city = city;
        this.postalCode = postal_code;
        this.phoneNumber = phone_number;
        this.emergencyPhoneNumber = emergency_phone_number;

    }

    public int getBirthDateYear() {
        return birthDateYear;
    }

    public void setBirthDateYear(int birthDateYear) {
        this.birthDateYear = birthDateYear;
    }

    public int getBirthDateMonth() {
        return birthDateMonth;
    }

    public void setBirthDateMonth(int birthDateMonth) {
        this.birthDateMonth = birthDateMonth;
    }

    public int getBirthDateDay() {
        return birthDateDay;
    }

    public void setBirthDateDay(int birthDateDay) {
        this.birthDateDay = birthDateDay;
    }

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

    public static Date setBirthDate(int year, int month, int day) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.getTime();
        cal.set(year, month, day);

        return cal.getTime();

    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getSocial_security_number() {
        return socialSecurityNumber;
    }

    public void setSocial_security_number(String social_security_number) {
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

    public String getPostal_code() {
        return postalCode;
    }

    public void setPostal_code(String postal_code) {
        this.postalCode = postal_code;
    }

    public String getPhone_number() {
        return phoneNumber;
    }

    public void setPhone_number(String phone_number) {
        this.phoneNumber = phone_number;
    }

    public String getEmergency_phone_number() {
        return emergencyPhoneNumber;
    }

    public void setEmergency_phone_number(String emergency_phone_number) {
        this.emergencyPhoneNumber = emergency_phone_number;
    }


}
