package com.shencare.shencaremobile.EventPackage;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shencare.shencaremobile.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/16.
 */
//custom item list for event items
public class EventListItemAdapter extends BaseAdapter{
//pls change to ArrayAdapter
    private ArrayList<IndividualEvent> items;
    private Context context;



    public EventListItemAdapter(ArrayList<IndividualEvent> list, Context context){
        super();
        this.items =list;
        this.context =context;
    }
    @Override
    public int getCount(){
        return items.size();
    }
    @Override
    public IndividualEvent getItem(int position){
        return items.get(position);
    }
    @Override
    public long getItemId(int position){
        return position;
    }

    public void addAll(List<IndividualEvent> list) {
        Log.v("this", list.size() + " resultsize");
        if(items==null){
            items = new ArrayList<IndividualEvent>();
        }
        items.addAll(list);
        //Log.d("this",items.toString());
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        //Get the current list item
        //final IndividualEvent eventItem = items.get(position);

        LayoutInflater li = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = li.inflate(R.layout.event_list_item,parent,false);
        IndividualEvent eventItem = items.get(position);
        //System.out.println("eventlistitemadapter"+ eventItem);
        TextView eventTitle = (TextView)convertView.findViewById(R.id.event_list_item_title);
        eventTitle.setText(eventItem.getEventName());
        return convertView;
    }

}
