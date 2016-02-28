package com.shencare.shencaremobile.ServiceItems;

/**
 * Created by Administrator on 2016/2/26.
 */
public class VolunteerService extends ServiceItem{
    private String volLocation;
    private String volAddr;
    private String volEmail;

    public VolunteerService(String volID, String volName, String volType, String volLocation, String volAddr, String volEmail){
        super(volID, volName,volType);
        this.volLocation = volLocation;
        this.volAddr = volAddr;
        this.volEmail = volEmail;
    }

}
