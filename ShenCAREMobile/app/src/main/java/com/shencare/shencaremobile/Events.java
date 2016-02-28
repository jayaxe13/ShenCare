package com.shencare.shencaremobile;

import android.content.Intent;
import android.graphics.Color;
import android.widget.AdapterView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.shencare.shencaremobile.CalendarPackage.CompactCalendarView;
import com.shencare.shencaremobile.CalendarPackage.domain.CalendarDayEvent;
import com.shencare.shencaremobile.Util.SessionManager;
import com.shencare.shencaremobile.EventPackage.EventListItemAdapter;
import com.shencare.shencaremobile.EventPackage.EventsManager;
import com.shencare.shencaremobile.EventPackage.IndividualEvent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class Events extends Navigation_drawer {
    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());
    //using Hashmap to manage the booking records, while the date is the key and a list of booking is the value
    private EventsManager eventsMgr = new EventsManager();
    private Map<Date, List<IndividualEvent>> events = new HashMap<>();
    private SessionManager session;
    private SimpleDateFormat eventDetailFormatter = new SimpleDateFormat("yyyy-MMM-dd");
    private EventListItemAdapter adapter;

    private TextView dateDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_events, frameLayout);

        /**
         * Setting title and itemChecked
         */
        //mDrawerList.setItemChecked(position, true);
        setTitle("Senior Living");


        session = new SessionManager(getApplicationContext());

        if(session.isLoggedIn()){
            //User is already logged in. Hide the login button
            menuCondition = "UserLogin";
        }else{
            menuCondition = "ElderlyEvents";
        }

        //final List<String> mutableEvents = new ArrayList<>();
        final ArrayList<IndividualEvent> mutableEvents = new ArrayList<>();
        final ListView eventsListView = (ListView) findViewById(R.id.bookings_listview);
        final Button showPreviousMonthBut = (Button) findViewById(R.id.prev_button);
        final Button showNextMonthBut = (Button) findViewById(R.id.next_button);

        adapter = new EventListItemAdapter(mutableEvents,getApplicationContext());
        eventsListView.setAdapter(adapter);

        //eventsListView.setTextFilterEnabled(true);
        //String value = (String)adapter.getItemAtPosition(position);


        // Add listener for item clicks
        eventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // Retrieve our class object and use index to resolve item tapped
                final IndividualEvent item = mutableEvents.get(position);
                goToEventDetail(item.getEventName(), eventDetailFormatter.format(item.getEventDate()), item.getEventDuration(), item.getEventVenue(), item.getEventInfo());

            }
        });


        //String value = (String)adapter.getItemAtPosition(position);
        final CompactCalendarView compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendarView.drawSmallIndicatorForEvents(true);
        dateDisplay = (TextView) findViewById(R.id.datedisplay);

        //perform add events
        events = addEventsIntoCalendar(compactCalendarView);
        compactCalendarView.invalidate();
        System.out.println("65" + events);
        // below line will display Sunday as the first day of the week
        compactCalendarView.setShouldShowMondayAsFirstDay(false);

        dateDisplay.setText(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));
        //set title on calendar scroll
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                List<IndividualEvent> eventsFromMap = events.get(dateClicked);
                //Log.d("MainActivity", "inside onclick " + dateClicked);
                if (eventsFromMap != null) {
                    //Log.d("MainActivity", eventsFromMap.toString());
                    mutableEvents.clear();
                    for (IndividualEvent thisEvent : eventsFromMap) {
                        mutableEvents.add(thisEvent);
                    }
                    adapter.addAll(mutableEvents);
                    //System.out.println(mutableEvents);
                   //System.out.println(adapter.getCount());
                    // below will remove events
                    // compactCalendarView.removeEvent(new CalendarDayEvent(dateClicked.getTime(), Color.argb(255, 169, 68, 65)), true);
                    //adapter.notifyDataSetChanged();
                } else {
                    mutableEvents.clear();
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                //actionBar.setTitle(dateFormatForMonth.format(firstDayOfNewMonth));
                dateDisplay.setText(dateFormatForMonth.format(firstDayOfNewMonth));
            }
        });

        showPreviousMonthBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compactCalendarView.showPreviousMonth();
            }
        });

        showNextMonthBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compactCalendarView.showNextMonth();
            }
        });
    }

    public void goToEventDetail(String eventName, String eventDate, String duration, String venue, String info){
        //Start Intend to UserProfileEdit class and pass the ShencareUser object to the UserProfileEdit class
        Intent intentEventDetail = new Intent(this, EventDetails.class);
        intentEventDetail.putExtra("eventName", eventName);
        intentEventDetail.putExtra("eventDate", eventDate);
        intentEventDetail.putExtra("eventDuration", duration);
        intentEventDetail.putExtra("eventVenue", venue);
        intentEventDetail.putExtra("info", info);
        startActivity(intentEventDetail);
    }

    public Map<Date, List<IndividualEvent>> addEventsIntoCalendar(CompactCalendarView compactCalendarView){
        Map<Date, List<IndividualEvent>> tempMap = new HashMap<>();
        eventsMgr.addEvents();
        tempMap = eventsMgr.getMap();
        Iterator it = tempMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Date,List> pair = (Map.Entry<Date,List>)it.next();
            Date dateValue = pair.getKey();
            compactCalendarView.addEvent(new CalendarDayEvent(dateValue.getTime(),  Color.argb(255, 169, 68, 65)), false);
            //it.remove(); // avoids a ConcurrentModificationException
        }
        return tempMap;
    }



}
