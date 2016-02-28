package com.shencare.shencaremobile.EventPackage;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/6.
 */
public class EventsManager {
    private Map<Date, List<IndividualEvent>> theEvents;
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());

    public EventsManager(){
        theEvents = new HashMap<>();
    }

    public void addEvents(){
        Date firstRecord = formatDate("2016-02-10");
        theEvents.put(firstRecord, createEventList());
    }
    public List<IndividualEvent> createEventList(){
        List<IndividualEvent> temp = new ArrayList<>();
        IndividualEvent event1 = new IndividualEvent("Origami folding Event", formatDate("2016-02-10"),"10:00 - 16:00","National Library", "Have a day of fun learning how to fold classic origami pieces and get to meet people" );
        temp.add(event1);
        IndividualEvent event2 = new IndividualEvent("Campus Walk", formatDate("2016-02-10"),"11:00 - 14:00","SMU", "Use ur matric card to get Welfare." );
        temp.add(event2);
        IndividualEvent event3 = new IndividualEvent("Massive Dance", formatDate("2016-02-10"),"11:00 - 14:00","City Hall", "Use ur matric card to get Welfare." );
        temp.add(event3);
        IndividualEvent event4 = new IndividualEvent("ChingGay", formatDate("2016-02-10"),"11:00 - 14:00","Orchard", "Use ur matric card to get Welfare." );
        temp.add(event4);
        IndividualEvent event5 = new IndividualEvent("Bungee Jump", formatDate("2016-02-10"),"11:00 - 14:00","Sentosa", "Use ur matric card to get Welfare." );
        temp.add(event5);
        IndividualEvent event6 = new IndividualEvent("USS Visiting", formatDate("2016-02-10"),"11:00 - 14:00","Sentosa", "Use ur matric card to get Welfare." );
        temp.add(event6);
        return temp;
    }

    public Date formatDate(String dateInString){
        Date date = null;
        try {
            date = formatter.parse(dateInString);
            //System.out.println(date);
           //System.out.println(formatter.format(date));


        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public Map<Date, List<IndividualEvent>> getMap(){
        return theEvents;
    }

    /* For setting all the timing to midnight, but no need now
      * public Date dateTrim(Date date){
      *  currentCalender.setTime(date);
      *  currentCalender.set(Calendar.MILLISECOND, 0);
      *  currentCalender.set(Calendar.SECOND, 0);
      *  currentCalender.set(Calendar.MINUTE, 0);
      *  currentCalender.set(Calendar.HOUR, 0);

      *  return currentCalender.getTime();
    }*/
}
