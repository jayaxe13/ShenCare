package com.shencare.shencaremobile.ServiceItems;



/**
 * Created by Administrator on 2016/2/26.
 */
public class ClinicService extends ServiceItem{

    private String clinicAddr;
    private String clinicContact;
    private String clinicTime;

    public ClinicService(String serviceID, String serviceName, String serviceType, String clinicAddr, String clinicContact, String clinicTime){
        super(serviceID, serviceName, serviceType);
        this.clinicAddr = clinicAddr;
        this.clinicContact = clinicContact;
        this.clinicTime = clinicTime;
    }

    public String getClinicAddr() {
        return clinicAddr;
    }

    public void setClinicAddr(String clinicAddr) {
        this.clinicAddr = clinicAddr;
    }

    public String getClinicContact() {
        return clinicContact;
    }

    public void setClinicContact(String clinicContact) {
        this.clinicContact = clinicContact;
    }

    public String getClinicTime() {
        return clinicTime;
    }

    public void setClinicTime(String clinicTime) {
        this.clinicTime = clinicTime;
    }
}
