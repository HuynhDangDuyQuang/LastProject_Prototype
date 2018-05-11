package com.duyquang.lastproject.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.duyquang.lastproject.NutritionixObject.ItemDetail;
import com.duyquang.lastproject.NutritionixObject.User;
import com.duyquang.lastproject.NutritionixObject.UserTrackingData;
import com.duyquang.lastproject.R;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmResults;


public class StatsFragment extends Fragment {

//    User user;
    Spinner timePicker;
    GraphView graph;
    Realm realm;
    User user;
    RealmResults<UserTrackingData> monthList;

    public static StatsFragment newInstance(){
        StatsFragment fragment=new StatsFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);
        graph = (GraphView) view.findViewById(R.id.graph);
        timePicker=view.findViewById(R.id.timePicker);

        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        realm=Realm.getDefaultInstance();
        RealmResults<UserTrackingData> allUTD=realm.where(UserTrackingData.class).findAll();
        user=realm.where(User.class).findFirst();
        int minYear=0;
        int maxYear=0;
        if(allUTD!=null && allUTD.min("year")!=null && allUTD.max("year")!=null){
            minYear=allUTD.min("year").intValue();
            maxYear=allUTD.max("year").intValue();
        }




        ArrayList<String> dropDownList=new ArrayList<>();
        for(int year=minYear;year<=maxYear;year++){
            RealmResults<UserTrackingData> allUTDinYear=realm.where(UserTrackingData.class)
                    .equalTo("year",year)
                    .findAll();

            int minMonthOfYear=-1;
            int maxMonthOfYear=-1;
            if(allUTD!=null && allUTDinYear.min("month")!=null && allUTDinYear.max("month")!=null){
                minMonthOfYear=allUTDinYear.min("month").intValue();
                maxMonthOfYear=allUTDinYear.max("month").intValue();
            }

            for(int month=minMonthOfYear;month<=maxMonthOfYear;month++){
                dropDownList.add((month+1)+" / "+year);
            }
        }

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(StatsFragment.this.getActivity(),R.layout.spinner_item,dropDownList);
        timePicker.setAdapter(adapter);
        String defaultSelection= (Calendar.getInstance().get(Calendar.MONTH)+1)+" / "+ Calendar.getInstance().get(Calendar.YEAR);
        timePicker.setSelection(adapter.getPosition(defaultSelection));

        timePicker.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                String selectedItem=adapterView.getItemAtPosition(position).toString();

                int selectedMonth=Integer.parseInt(selectedItem.split("/")[0].trim())-1;
                int selectedYear=Integer.parseInt(selectedItem.split("/")[1].trim());
                RealmResults<UserTrackingData> allUTDinMonth=realm.where(UserTrackingData.class)
                        .equalTo("year",selectedYear)
                        .equalTo("month",selectedMonth)
                        .findAll();
                int lastDayOfMonth=DayMax(selectedYear,selectedMonth+1);
                graph.removeAllSeries();
                DataPoint data[]=new DataPoint[lastDayOfMonth+1];
                for(int i=0;i<lastDayOfMonth+1;i++){
                    data[i]=new DataPoint(i,0);
                }

                for(UserTrackingData utd:allUTDinMonth){
                    int totalIntakeCalorie=0;

                    if(utd.getBreakfast()!=null) {
                        for(ItemDetail i:utd.getBreakfast()){
                            totalIntakeCalorie+=i.getNf_calories().intValue();
                        }
                    }
                    if(utd.getLunch()!=null) {
                        for(ItemDetail i:utd.getLunch()){
                            totalIntakeCalorie+=i.getNf_calories().intValue();
                        }
                    }
                    if(utd.getDinner()!=null) {
                        for(ItemDetail i:utd.getDinner()){
                            totalIntakeCalorie+=i.getNf_calories().intValue();
                        }
                    }
                    if(utd.getSnack()!=null) {
                        for(ItemDetail i:utd.getSnack()){
                            totalIntakeCalorie+=i.getNf_calories().intValue();
                        }
                    }

                    data[utd.getDay()]=new DataPoint(utd.getDay(),totalIntakeCalorie);

                }

                BarGraphSeries<DataPoint> series=new BarGraphSeries<>(data);
                series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return (data.getY()<user.getGoal())? Color.GREEN:Color.RED;
                    }
                });
                series.setAnimated(true);
                series.setSpacing(40);

                graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
                    @Override
                    public String formatLabel(double value, boolean isValueX) {
                        if((value-((int)value))==0)
                            return ""+((int) value);
                        else return "";
                    }
                });

                graph.getViewport().setMinX(0);
                graph.getViewport().setMaxX(lastDayOfMonth+1);
                graph.getViewport().setXAxisBoundsManual(true);
                graph.getViewport().setScrollable(true);
                graph.getViewport().setScalable(true);
                graph.addSeries(series);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    int DayMax(int yr,int mth)
    {
        if(mth==1||mth==3||mth==5||mth==7||
                mth==8||mth==10||mth==12)
            return 31;
        if(mth==4||mth==6||mth==9||mth==11)
            return 30;
        if(mth==2&&((yr%4==0&&yr%100!=0)||yr%400==0))
            return 29;
        else return 28;
    }
}
