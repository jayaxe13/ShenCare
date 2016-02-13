package com.shencare.shencaremobile.volunteerPackage;

/**
 * Created by Administrator on 2016/2/11.
 */
public class shencareVolunteer {
    private String name;
    private String email;
    private String contact;
    private String freqOfWork;
    private String preferWorkSize;


    public shencareVolunteer(String sName, String sEmail, String sContact, String sFreqOfWork, String sPreferWorkSize){
        this.name = sName;
        this.email = sEmail;
        this.contact = sContact;
        this.freqOfWork = sFreqOfWork;
        this.preferWorkSize = sPreferWorkSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getFreqOfWork() {
        return freqOfWork;
    }

    public void setFreqOfWork(String freqOfWork) {
        this.freqOfWork = freqOfWork;
    }

    public String getPreferWorkSize() {
        return preferWorkSize;
    }

    public void setPreferWorkSize(String preferWorkSize) {
        this.preferWorkSize = preferWorkSize;
    }
}
