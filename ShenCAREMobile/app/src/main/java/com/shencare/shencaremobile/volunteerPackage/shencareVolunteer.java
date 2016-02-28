package com.shencare.shencaremobile.VolunteerPackage;

/**
 * Created by Administrator on 2016/2/11.
 */
public class ShencareVolunteer {
    private String name;
    private String email;
    private String contact;
    private String freqOfWork;
    private String preferWorkSize;
    private String volMsg;

    //initiate an empty constructor
    public ShencareVolunteer(){
    }


    public ShencareVolunteer(String sName, String sEmail, String sContact, String sFreqOfWork, String sPreferWorkSize, String sVolMsg){
        this.name = sName;
        this.email = sEmail;
        this.contact = sContact;
        this.freqOfWork = sFreqOfWork;
        this.preferWorkSize = sPreferWorkSize;
        this.volMsg = sVolMsg;
    }

    public ShencareVolunteer(String sName, String sEmail,String sContact, String sFreqOfWork, String sPreferWorkSize){
        this(sName, sEmail, sContact,  sFreqOfWork, sPreferWorkSize,null);
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

    public String getVolMsg() {
        return volMsg;
    }

    public void setVolMsg(String volMsg) {
        this.volMsg = volMsg;
    }
}
