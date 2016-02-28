package com.shencare.shencaremobile.ServicePackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shencare.shencaremobile.EventPackage.IndividualEvent;
import com.shencare.shencaremobile.R;
import com.shencare.shencaremobile.ServiceItems.ServiceItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/28.
 */
public class ServiceItemAdapter extends BaseAdapter{
    private ArrayList<ServiceItem> items;
    private Context context;

    public ServiceItemAdapter(ArrayList<ServiceItem> items, Context context){
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount(){
        return items.size();
    }
    @Override
    public ServiceItem getItem(int position){
        return items.get(position);
    }
    @Override
    public long getItemId(int position){
        return position;
    }

    public void addAllService(List<ServiceItem> list){
        if(items == null){
            items = new ArrayList<ServiceItem>();
        }
        items.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater li = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = li.inflate(R.layout.careservice_list_item,parent,false);
        ServiceItem serviceItem = items.get(position);

        TextView serviceTitle = (TextView)convertView.findViewById(R.id.service_list_item_title);
        serviceTitle.setText(serviceItem.getServiceName());
        TextView serviceType = (TextView)convertView.findViewById(R.id.service_list_item_type);
        serviceType.setText(serviceItem.getServiceType());
        return convertView;
    }

}
