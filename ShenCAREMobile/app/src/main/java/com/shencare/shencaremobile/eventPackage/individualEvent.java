package com.shencare.shencaremobile.EventPackage;

import java.util.Date;

/**
 * Created by Administrator on 2016/2/6.
 */
public class IndividualEvent {

    private String eventName;
    private Date eventDate;
    private String eventDuration;
    private String eventVenue;
    private String eventInfo;

    public IndividualEvent(String name, Date date, String duration, String venue, String info){
        this.eventName = name;
        this.eventDate = date;
        this.eventDuration = duration;
        this.eventVenue = venue;
        this.eventInfo = info;
    }


    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventDuration() {
        return eventDuration;
    }

    public void setEventDuration(String eventDuration) {
        this.eventDuration = eventDuration;
    }

    public String getEventInfo() {
        return eventInfo;
    }

    public void setEventInfo(String eventInfo) {
        this.eventInfo = eventInfo;
    }

    public String getEventVenue() {
        return eventVenue;
    }

    public void setEventVenue(String eventVenue) {
        this.eventVenue = eventVenue;
    }
}
