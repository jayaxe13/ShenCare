package com.shencare.shencaremobile.eventPackage;


import android.graphics.Color;
import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/6.
 */
public class eventsManager {
    private Map<Date, List<individualEvent>> theEvents;
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());

    public eventsManager(){
        theEvents = new HashMap<>();
    }

    public void addEvents(){
        Date firstRecord = formatDate("2016-02-05");
        theEvents.put(firstRecord, createEventList());
    }
    public List<individualEvent> createEventList(){
        List<individualEvent> temp = new ArrayList<>();
        individualEvent event1 = new individualEvent("SMU Patron's Day", formatDate("2016-02-05"),"14:00 - 18:00","Marina Barrage", "Have fun there." );
        temp.add(event1);
        individualEvent event2 = new individualEvent("SMU Welfare", formatDate("2016-02-05"),"11:00 - 14:00","SMU", "Use ur matric card to get Welfare." );
        temp.add(event2);
        individualEvent event3 = new individualEvent("SMU HAH 1", formatDate("2016-02-05"),"11:00 - 14:00","City Hall", "Use ur matric card to get Welfare." );
        temp.add(event3);
        individualEvent event4 = new individualEvent("SMU Azuki", formatDate("2016-02-05"),"11:00 - 14:00","Japan", "Use ur matric card to get Welfare." );
        temp.add(event4);
        individualEvent event5 = new individualEvent("SMU SIS DAY", formatDate("2016-02-05"),"11:00 - 14:00","SIS", "Use ur matric card to get Welfare." );
        temp.add(event5);
        individualEvent event6 = new individualEvent("SMU Wonderland", formatDate("2016-02-05"),"11:00 - 14:00","Sentosa", "Use ur matric card to get Welfare." );
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

    public Map<Date, List<individualEvent>> getMap(){
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
