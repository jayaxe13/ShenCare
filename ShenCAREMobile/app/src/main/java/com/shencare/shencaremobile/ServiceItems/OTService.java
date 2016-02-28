package com.shencare.shencaremobile.ServiceItems;

/**
 * Created by Administrator on 2016/2/26.
 */
public class OTService extends ServiceItem{

    private String otLoc;
    private String otSpec;
    private String otAddr;
    private String otEmail;
    private String otContact;

    public OTService(String serviceID, String serviceName, String serviceType, String otLoc, String otSpec, String otAddr, String otEmail, String otContact){
        super(serviceID, serviceName,serviceType);
        this.otLoc = otLoc;
        this.otSpec = otSpec;
        this.otAddr = otAddr;
        this.otEmail = otEmail;
        this.otContact = otContact;
    }

        public String getOtLoc() {
            return otLoc;
        }

        public void setOtLoc(String otLoc) {
            this.otLoc = otLoc;
        }

        public String getOtSpec() {
            return otSpec;
        }

        public void setOtSpec(String otSpec) {
            this.otSpec = otSpec;
        }

        public String getOtAddr() {
            return otAddr;
        }

        public void setOtAddr(String otAddr) {
            this.otAddr = otAddr;
        }

        public String getOtEmail() {
            return otEmail;
        }

        public void setOtEmail(String otEmail) {
            this.otEmail = otEmail;
        }

        public String getOtContact() {
            return otContact;
        }

        public void setOtContact(String otContact) {
            this.otContact = otContact;
        }
}
