package com.shencare.shencaremobile.userPackage;

/**
 * Created by Administrator on 2016/2/11.
 */
public class ShencareUser {
    private String username;
    private String surname;
    private String firstname;
    private String email;
    private String password;
    private String contactNumber;
    private String gender;
    private String address;
    private String pot;
    private String mpl;

    public ShencareUser(){
    }


    public ShencareUser(String sUsername, String sSurname, String sFirstname, String sEmail, String sPassword,
                        String sContact, String sGender, String sAddress, String sPot, String sMpl){
        this.username = sUsername;
        this.surname = sSurname;
        this.firstname = sFirstname;
        this.email = sEmail;
        this.password = sPassword;
        this.contactNumber = sContact;
        this.gender = sGender;
        this.address = sAddress;
        this.pot = sPot;
        this.mpl = sMpl;
    }

    //For user registration since at the registration stage the user is not required to input their gender and address
    public ShencareUser(String sUsername, String sSurname, String sFirstname, String sEmail, String sPassword, String sContact,
                        String sPot, String sMpl){
        this(sUsername, sSurname, sFirstname, sEmail, sPassword, sContact, null, null, sPot, sMpl);
    }

    public ShencareUser(String sUsername, String sSurname, String sFirstname, String sEmail, String sContact){
        this(sUsername,sSurname,sFirstname, sEmail, null,sContact,null,null,null,null);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPot() {
        return pot;
    }

    public void setPot(String pot) {
        this.pot = pot;
    }

    public String getMpl() {
        return mpl;
    }

    public void setMpl(String mpl) {
        this.mpl = mpl;
    }
}
