package com.shencare.shencaremobile;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class Services extends Navigation_drawer implements View.OnClickListener {
    private String searchKeyWord, serviceType, location, theSpec;
    private TextView defaultServiceMsg;
    private ImageView ivDeleteText;
    private EditText etSearch;
    private Spinner serviceTypeSpinner;
    private Spinner locationSpinner;
    private Spinner specSpinner;
    private ListView serviceSearchResult;
    private Button searchServiceButton;
    ArrayAdapter<String> serviceTypeAdapter = null;
    ArrayAdapter<String> locationAdapter = null;
    ArrayAdapter<String> specAdapter = null;
    static int servicePosition, locPosition, specPosition;
    //private ArrayList<Services>
    // I think we should by default show nothing first, after the user click on search button then we show the results
    //ArrayList<ServiceItem> servicesList
    //private ArrayList<VolunteerService> volunteerList
    //private ArrayList<ClinicService> clinicList
    //private ArrayList<OTService> otList


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_services, frameLayout);

        /**
         * Setting title and itemChecked
         */
        mDrawerList.setItemChecked(position, true);
        setTitle("Care Services");
        menuCondition = "CareServices";

        //search bar delete text function
        ivDeleteText = (ImageView) findViewById(R.id.ivDeleteText);
        etSearch = (EditText) findViewById(R.id.etSearch);
        defaultServiceMsg = (TextView)findViewById(R.id.default_service_text);
        defaultServiceMsg.setText(R.string.defaultServiceMsg);
        serviceSearchResult = (ListView)findViewById(R.id.services_listview);
        //serviceResultAdapter serviceAdapter = new serviceResultAdapter(list)
        searchServiceButton = (Button)findViewById(R.id.btnServiceSearch);
        searchServiceButton.setOnClickListener(this);

        ivDeleteText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                etSearch.setText("");
            }
        });
        etSearch.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    ivDeleteText.setVisibility(View.GONE);
                } else {
                    ivDeleteText.setVisibility(View.VISIBLE);
                }
            }
        });
        //End of function
        setSpinner();

        if(searchKeyWord == null && serviceType==null && location==null && theSpec==null){
            //when the user visit this page for the first time, the list of items would not show
            //but instead the default brief information of this page will be shown
            defaultServiceMsg.setVisibility(View.VISIBLE);
            serviceSearchResult.setVisibility(View.INVISIBLE);
        }else{
            //should hide the default message when the search button is clicked
            defaultServiceMsg.setVisibility(View.INVISIBLE);
            serviceSearchResult.setVisibility(View.VISIBLE);
        }
    }


    /*
     * add arrays to spinners
     */
    private void setSpinner()
    {
        serviceTypeSpinner = (Spinner)findViewById(R.id.spin_province);
        locationSpinner = (Spinner)findViewById(R.id.spin_city);
        specSpinner = (Spinner)findViewById(R.id.spin_county);

        //绑定适配器和值
        serviceTypeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.care_service_category));
        serviceTypeSpinner.setAdapter(serviceTypeAdapter);
        serviceTypeSpinner.setSelection(0, true);  //设置默认选中项，此处为默认选中第4个值

        locationAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.mpl_characters));
        locationSpinner.setAdapter(locationAdapter);
        locationSpinner.setSelection(0, true);  //默认选中第0个

        specAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.pot_characters));
        specSpinner.setAdapter(specAdapter);
        specSpinner.setSelection(0, true);


        //省级下拉框监听
        serviceTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            // 表示选项被改变的时候触发此方法，主要实现办法：动态改变地级适配器的绑定值
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {

                if (serviceTypeAdapter.getItem(position).equals("Clinic")) {
                    locationAdapter = new ArrayAdapter<String>(
                            Services.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.na));
                    // 设置二级下拉列表的选项内容适配器
                    locationAdapter.notifyDataSetChanged();
                    locationSpinner.setAdapter(locationAdapter);
                    specAdapter = new ArrayAdapter<String>(
                            Services.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.na));
                    // 设置二级下拉列表的选项内容适配器
                    specAdapter.notifyDataSetChanged();
                    specSpinner.setAdapter(specAdapter);
                } else if (serviceTypeAdapter.getItem(position).equals("Volunteer")) {
                    locationAdapter = new ArrayAdapter<String>(
                            Services.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.mpl_characters));
                    // 设置二级下拉列表的选项内容适配器
                    locationAdapter.notifyDataSetChanged();
                    locationSpinner.setAdapter(locationAdapter);
                    specAdapter = new ArrayAdapter<String>(
                            Services.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.na));
                    // 设置二级下拉列表的选项内容适配器
                    specAdapter.notifyDataSetChanged();
                    specSpinner.setAdapter(specAdapter);
                } else {
                    locationAdapter = new ArrayAdapter<String>(
                            Services.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.mpl_characters));
                    // 设置二级下拉列表的选项内容适配器
                    locationAdapter.notifyDataSetChanged();
                    locationSpinner.setAdapter(locationAdapter);
                    specAdapter = new ArrayAdapter<String>(
                            Services.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.pot_characters));
                    // 设置二级下拉列表的选项内容适配器
                    specAdapter.notifyDataSetChanged();
                    specSpinner.setAdapter(specAdapter);
                }
                serviceType = (String) serviceTypeAdapter.getItem(position);
                servicePosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {}
        });

        //spinner listener
        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long arg3) {
                location = (String) locationAdapter.getItem(position);
                locPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {}
        });

        //ot spec listener
        specSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long arg3) {
                theSpec = (String) specAdapter.getItem(position);
                specPosition = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {}
        });
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch(id){
            case R.id.btnServiceSearch:

                searchKeyWord = etSearch.getText().toString();
                if(searchKeyWord != null){
                    if(servicePosition == 0 && locPosition == 0 && specPosition == 0){
                        // when the spinner is not selected

                    }else if(specPosition == 0 && locPosition == 0){
                        // when only the serviceType is selected
                    }else if(specPosition == 0){
                        //when only the servicePosition and locPositions are selected
                    }else{
                        //when three of the spinner type are chosen - which only apply to the ot case
                    }

                }else{
                    // if there is search key word
                    if(servicePosition == 0 && locPosition == 0 && specPosition == 0){
                        //when the spinner is not selected
                        //search everything using this keyword
                    }else if(specPosition == 0 && locPosition == 0){
                        // when only the serviceType is selected
                        //obtain the list of the same serviceType and search for this keyword

                    }else if(specPosition == 0){
                        //when only the serviceType and locPositions are selected

                    }else{
                        //when three of the spinner type are chosen - which only apply to the ot case
                    }

                }
                //if search Key word is null and the user did not select any category - retrieve everything from the database?

                //if search Key word is not null but the user did not select for the spinners
                //- select everything from the db which contains the keyword inputed

                //if search key word is not null and the user has select from the spinners: select serviceType only, select serviceType and locations
                //select serviceType, locations and the specialisation of the OT


                break;
        }
    }

    public void prepareSearchMessage(){

    }

}
