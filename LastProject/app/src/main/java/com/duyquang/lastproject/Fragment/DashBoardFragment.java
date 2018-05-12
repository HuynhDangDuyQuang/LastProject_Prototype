package com.duyquang.lastproject.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.duyquang.lastproject.Activity.FoodSearchActivity;
import com.duyquang.lastproject.Activity.MainActivity;
import com.duyquang.lastproject.Adapter.FoodListAdapter;
import com.duyquang.lastproject.MyApp;
import com.duyquang.lastproject.NutritionixObject.ItemDetail;
import com.duyquang.lastproject.NutritionixObject.User;
import com.duyquang.lastproject.NutritionixObject.UserTrackingData;
import com.duyquang.lastproject.R;

import java.util.Calendar;

import io.realm.Realm;


public class DashBoardFragment extends Fragment{

    ProgressBar progressBar;
    TextView txtLeftBtn;
    TextView txtRightBtn;
    RelativeLayout backArea;
    RelativeLayout nextArea;
    TextView date;
    TextView intake;
    TextView remaining;

    TextView protein;
    TextView carb;
    TextView fat;

    Button btnAddBreakfast;
    Button btnAddLunch;
    Button btnAddDinner;
    Button btnAddSnack;

    TextView breakfastTotalCalorie;
    TextView lunchTotalCalorie;
    TextView dinnerTotalCalorie;
    TextView snackTotalCalorie;

    ListView listBreakfast;
    ListView listLunch;
    ListView listDinner;
    ListView listSnack;

    FloatingActionButton buttonAdd;

    private Realm realm;

    User user;
    UserTrackingData utd;

    public static DashBoardFragment newInstance(){
        DashBoardFragment fragment=new DashBoardFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dash_board, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtLeftBtn = view.findViewById(R.id.left);
        txtLeftBtn.setText("  <  ");
        txtRightBtn = view.findViewById(R.id.right);
        txtRightBtn.setText("  >  ");
        backArea = view.findViewById(R.id.backArea);
        nextArea = view.findViewById(R.id.nextArea);

        date = view.findViewById(R.id.date);

        intake = view.findViewById(R.id.intake);
        remaining = view.findViewById(R.id.remaining);

        progressBar = view.findViewById(R.id.animatedProgressBar);

        protein = view.findViewById(R.id.protein);
        carb = view.findViewById(R.id.carb);
        fat = view.findViewById(R.id.fat);

        btnAddBreakfast = view.findViewById(R.id.btnAddBreakfast);
        btnAddLunch = view.findViewById(R.id.btnAddLunch);
        btnAddDinner = view.findViewById(R.id.btnAddDinner);
        btnAddSnack = view.findViewById(R.id.btnAddSnack);

        breakfastTotalCalorie = view.findViewById(R.id.breakfastTotalCalorie);
        lunchTotalCalorie = view.findViewById(R.id.lunchTotalCalorie);
        dinnerTotalCalorie = view.findViewById(R.id.dinnerTotalCalorie);
        snackTotalCalorie = view.findViewById(R.id.snackTotalCalorie);

        listBreakfast = view.findViewById(R.id.listBreakfast);
        listLunch = view.findViewById(R.id.listLunch);
        listDinner = view.findViewById(R.id.listDinner);
        listSnack = view.findViewById(R.id.listSnack);

        buttonAdd = view.findViewById(R.id.btnAdd);

        listBreakfast.setEmptyView(view.findViewById(R.id.emptyListNotify1));
        listLunch.setEmptyView(view.findViewById(R.id.emptyListNotify2));
        listDinner.setEmptyView(view.findViewById(R.id.emptyListNotify3));
        listSnack.setEmptyView(view.findViewById(R.id.emptyListNotify4));


        nextArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.datePointer.add(Calendar.DATE,1);
                DashBoardFragment.this.getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .detach(DashBoardFragment.this)
                        .attach(DashBoardFragment.this)
                        .commit();
            }
        });

        backArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.datePointer.add(Calendar.DATE,-1);
                DashBoardFragment.this.getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .detach(DashBoardFragment.this)
                        .attach(DashBoardFragment.this)
                        .commit();

            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchFood=new Intent(DashBoardFragment.this.getActivity(), FoodSearchActivity.class);
                String time;
                int hourOfDay=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                if(hourOfDay>=5&&hourOfDay<10) time="Breakfast";
                else if(hourOfDay>=10&&hourOfDay<15) time="Lunch";
                else if(hourOfDay>=15&&hourOfDay<20) time="Dinner";
                else time="Snack";
                searchFood.putExtra("time",time);
                startActivityForResult(searchFood, MyApp.DASH_BOARD_REQUEST_CODE);
            }
        });
        btnAddBreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchFood=new Intent(DashBoardFragment.this.getActivity(), FoodSearchActivity.class);
                searchFood.putExtra("time","Breakfast");
                startActivityForResult(searchFood, MyApp.DASH_BOARD_REQUEST_CODE);
            }
        });
        btnAddLunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchFood=new Intent(DashBoardFragment.this.getActivity(), FoodSearchActivity.class);
                searchFood.putExtra("time","Lunch");
                startActivityForResult(searchFood, MyApp.DASH_BOARD_REQUEST_CODE);
            }
        });
        btnAddDinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchFood=new Intent(DashBoardFragment.this.getActivity(), FoodSearchActivity.class);
                searchFood.putExtra("time","Dinner");
                startActivityForResult(searchFood, MyApp.DASH_BOARD_REQUEST_CODE);
            }
        });
        btnAddSnack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchFood=new Intent(DashBoardFragment.this.getActivity(), FoodSearchActivity.class);
                searchFood.putExtra("time","Snack");
                startActivityForResult(searchFood, MyApp.DASH_BOARD_REQUEST_CODE);
            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();

        if(MainActivity.datePointer.get(Calendar.DATE) == Calendar.getInstance().get(Calendar.DATE)
                && MainActivity.datePointer.get(Calendar.MONTH) == Calendar.getInstance().get(Calendar.MONTH)
                && MainActivity.datePointer.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)) {
            date.setText("Today, "+(MainActivity.datePointer.get(Calendar.MONTH)+1)+"/"+MainActivity.datePointer.get(Calendar.DATE));
        }
        else {
            String DayOfWeek;
            switch (MainActivity.datePointer.get(Calendar.DAY_OF_WEEK))
            {
                case Calendar.MONDAY:{ DayOfWeek="Monday";break;}
                case Calendar.TUESDAY:{ DayOfWeek="Tuesday";break;}
                case Calendar.WEDNESDAY:{ DayOfWeek="Wednesday";break;}
                case Calendar.THURSDAY:{ DayOfWeek="Thursday";break;}
                case Calendar.FRIDAY:{ DayOfWeek="Friday";break;}
                case Calendar.SATURDAY:{ DayOfWeek="Saturday";break;}
                case Calendar.SUNDAY:{ DayOfWeek="Sunday";break;}
                default:{DayOfWeek="";break;}
            }

            date.setText(DayOfWeek+", "+(MainActivity.datePointer.get(Calendar.MONTH)+1)+"/"+MainActivity.datePointer.get(Calendar.DATE));
        }

        realm=Realm.getDefaultInstance();
        user=realm.where(User.class).findFirst();

        utd=realm.where(UserTrackingData.class)
                .equalTo("year",MainActivity.datePointer.get(Calendar.YEAR))
                .equalTo("month",MainActivity.datePointer.get(Calendar.MONTH))
                .equalTo("day",MainActivity.datePointer.get(Calendar.DATE))
                .findFirst();

        progressBar.setMax(user.getGoal());
        int totalIntakeCalorie=0;
        int totalProtein=0;
        int totalCarbohydrates=0;
        int totalFat=0;
        int breakfastCalories=0;
        int lunchCalories=0;
        int dinnerCalories=0;
        int snackCalories=0;

        if(utd!=null){
            if(utd.getBreakfast()!=null) {
                for(ItemDetail i:utd.getBreakfast()){
                    totalIntakeCalorie+=i.getNf_calories().intValue()*i.getNf_serving_size_qty().intValue();
                    if(i.getNf_protein()!=null) totalProtein+=i.getNf_protein().intValue()*i.getNf_serving_size_qty().intValue();

                    if(i.getNf_total_carbohydrate()!=null) totalCarbohydrates+=i.getNf_total_carbohydrate().intValue()*i.getNf_serving_size_qty().intValue();

                    if(i.getNf_total_fat()!=null) totalFat+=i.getNf_total_fat().intValue()*i.getNf_serving_size_qty().intValue();

                    breakfastCalories+=i.getNf_calories().intValue()*i.getNf_serving_size_qty().intValue();
                }
                FoodListAdapter adapter = new FoodListAdapter(DashBoardFragment.this.getActivity(),utd.getBreakfast(),"Breakfast");
                listBreakfast.setAdapter(adapter);
                setListViewHeightBasedOnChildren(listBreakfast);
            }
            if(utd.getLunch()!=null) {
                for(ItemDetail i:utd.getLunch()){
                    totalIntakeCalorie+=i.getNf_calories().intValue()*i.getNf_serving_size_qty().intValue();
                    if(i.getNf_protein()!=null) totalProtein+=i.getNf_protein().intValue()*i.getNf_serving_size_qty().intValue();

                    if(i.getNf_total_carbohydrate()!=null) totalCarbohydrates+=i.getNf_total_carbohydrate().intValue()*i.getNf_serving_size_qty().intValue();

                    if(i.getNf_total_fat()!=null) totalFat+=i.getNf_total_fat().intValue()*i.getNf_serving_size_qty().intValue();

                    lunchCalories+=i.getNf_calories().intValue()*i.getNf_serving_size_qty().intValue();
                }
                FoodListAdapter adapter = new FoodListAdapter(DashBoardFragment.this.getActivity(),utd.getLunch(),"Lunch");
                listLunch.setAdapter(adapter);
                setListViewHeightBasedOnChildren(listLunch);
            }
            if(utd.getDinner()!=null) {
                for(ItemDetail i:utd.getDinner()){
                    totalIntakeCalorie+=i.getNf_calories().intValue()*i.getNf_serving_size_qty().intValue();
                    if(i.getNf_protein()!=null) totalProtein+=i.getNf_protein().intValue()*i.getNf_serving_size_qty().intValue();

                    if(i.getNf_total_carbohydrate()!=null) totalCarbohydrates+=i.getNf_total_carbohydrate().intValue()*i.getNf_serving_size_qty().intValue();

                    if(i.getNf_total_fat()!=null) totalFat+=i.getNf_total_fat().intValue()*i.getNf_serving_size_qty().intValue();

                    dinnerCalories+=i.getNf_calories().intValue()*i.getNf_serving_size_qty().intValue();
                }
                FoodListAdapter adapter = new FoodListAdapter(DashBoardFragment.this.getActivity(),utd.getDinner(),"Dinner");
                listDinner.setAdapter(adapter);
                setListViewHeightBasedOnChildren(listDinner);
            }
            if(utd.getSnack()!=null) {
                for(ItemDetail i:utd.getSnack()){
                    totalIntakeCalorie+=i.getNf_calories().intValue()*i.getNf_serving_size_qty().intValue();
                    if(i.getNf_protein()!=null) totalProtein+=i.getNf_protein().intValue()*i.getNf_serving_size_qty().intValue();

                    if(i.getNf_total_carbohydrate()!=null) totalCarbohydrates+=i.getNf_total_carbohydrate().intValue()*i.getNf_serving_size_qty().intValue();

                    if(i.getNf_total_fat()!=null) totalFat+=i.getNf_total_fat().intValue()*i.getNf_serving_size_qty().intValue();

                    snackCalories+=i.getNf_calories().intValue()*i.getNf_serving_size_qty().intValue();
                }
                FoodListAdapter adapter = new FoodListAdapter(DashBoardFragment.this.getActivity(),utd.getSnack(),"Snack");
                listSnack.setAdapter(adapter);
                setListViewHeightBasedOnChildren(listSnack);
            }
        }
        breakfastTotalCalorie.setText(breakfastCalories+" cal");
        lunchTotalCalorie.setText(lunchCalories+" cal");
        dinnerTotalCalorie.setText(dinnerCalories+" cal");
        snackTotalCalorie.setText(snackCalories+" cal");
        intake.setText(totalIntakeCalorie+" cal intake");
        remaining.setText("remainning "+(user.getGoal()-totalIntakeCalorie));
        protein.setText(totalProtein+" g Protein");
        carb.setText(totalCarbohydrates+" g Carb");
        fat.setText(totalFat+" g Fat");
        progressBar.setProgress(totalIntakeCalorie);
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}

