package com.shencare.shencaremobile.ServiceItems;

/**
 * Created by Administrator on 2016/2/26.
 */
public class ServiceItem {
    public String serviceID;
    public String serviceName;
    public String serviceType;

    public ServiceItem(String serviceID, String serviceName, String serviceType){
        this.serviceID = serviceID;
        this.serviceName = serviceID;
        this.serviceType = serviceType;
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }


}
