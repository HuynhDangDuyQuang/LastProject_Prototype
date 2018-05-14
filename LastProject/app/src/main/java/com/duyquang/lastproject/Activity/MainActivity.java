package com.duyquang.lastproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.duyquang.lastproject.Adapter.PagerAdapter;
import com.duyquang.lastproject.CustomViewPager;
import com.duyquang.lastproject.Fragment.DashBoardFragment;
import com.duyquang.lastproject.Fragment.StatsFragment;
import com.duyquang.lastproject.Fragment.UserInformationFragment;
import com.duyquang.lastproject.MyApp;
import com.duyquang.lastproject.NutritionixObject.ItemDetail;
import com.duyquang.lastproject.NutritionixObject.User;
import com.duyquang.lastproject.NutritionixObject.UserTrackingData;
import com.duyquang.lastproject.R;

import java.io.IOException;
import java.util.Calendar;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.exceptions.RealmMigrationNeededException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private Realm realm;
    User user;
    public static Calendar datePointer= Calendar.getInstance();

    public static CustomViewPager viewPager;
    public static PagerAdapter pagerAdapter;
    String rawItemDetail;
    ItemDetail newItem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        viewPager =  findViewById(R.id.viewpager);
        pagerAdapter =
                new PagerAdapter(getSupportFragmentManager(), MainActivity.this);
        pagerAdapter.addFragment(StatsFragment.newInstance(),"Stats");
        pagerAdapter.addFragment(DashBoardFragment.newInstance(),"Dashboard");
        pagerAdapter.addFragment(UserInformationFragment.newInstance(),"User");

        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setCurrentItem(1);
        viewPager.setPagingEnabled(false);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    protected void onStart(){
        super.onStart();
        try {
            realm = Realm.getDefaultInstance();
        } catch (RealmMigrationNeededException r) {
            RealmConfiguration config = new RealmConfiguration
                    .Builder()
                    .deleteRealmIfMigrationNeeded()
                    .build();
            Realm.deleteRealm(config);
            realm = Realm.getDefaultInstance();
        }

        user=realm.where(User.class).findFirst();
        if(user==null) {
            startActivityForResult(new Intent(MainActivity.this,UserInformationActivity.class), MyApp.MAIN_ACTIVITY_REQUEST_CODE);
//            creatSomeFakeData();

        }
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if(requestCode==MyApp.MAIN_ACTIVITY_REQUEST_CODE) {
            MainActivity.this.recreate();
        }
    }

    private void creatSomeFakeData(){
        if(rawItemDetail==null) {
            String foodDetailURL = "https://api.nutritionix.com/v1_1/item?id=56d5d808a3a6f4ee0dc5a39c&appId=da1adb3d&appKey=5a520c8e9d788cd66dc0b2b3a26ef34b";
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(foodDetailURL)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(MainActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                        }
                    });
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    rawItemDetail = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            MainActivity.this.recreate();

                        }
                    });
                }
            });
        }


        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                user = realm.createObject(User.class);
                RealmList<UserTrackingData> userTrackingData= new RealmList<>();
                user.setUserTrackingData(userTrackingData);
                user.setName("quang");
                user.setAge(22);
                user.setFemale(false);
                user.setHeight(173);
                user.setWeight(73);
                user.setActive(1);
                user.setGoal(2100);
            }
        });



        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                newItem=realm.createObjectFromJson(ItemDetail.class,rawItemDetail);
            }
        });

        datePointer.add(Calendar.DATE,-101);
        Calendar today=Calendar.getInstance();
        while(
                datePointer.get(Calendar.DATE)!=today.get(Calendar.DATE)||
                        datePointer.get(Calendar.MONTH)!=today.get(Calendar.MONTH)||
                        datePointer.get(Calendar.YEAR)!=today.get(Calendar.YEAR)){
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

                    UserTrackingData utd=realm.createObject(UserTrackingData.class);
                    utd.setYear(MainActivity.datePointer.get(Calendar.YEAR));
                    utd.setMonth(MainActivity.datePointer.get(Calendar.MONTH));
                    utd.setDay(MainActivity.datePointer.get(Calendar.DATE));

                    ItemDetail item1= realm.copyFromRealm(newItem);
                    ItemDetail item2= realm.copyFromRealm(newItem);
                    ItemDetail item3= realm.copyFromRealm(newItem);
                    ItemDetail item4= realm.copyFromRealm(newItem);
                    item4.setNf_calories((float)(new Random()).nextInt(500));

                    RealmList<ItemDetail> breakfast=new  RealmList<>();
                    utd.setBreakfast(breakfast);
                    utd.getBreakfast().add(item1);

                    RealmList<ItemDetail> lunch=new  RealmList<>();
                    utd.setLunch(lunch);
                    utd.getLunch().add(item2);

                    RealmList<ItemDetail> dinner=new  RealmList<>();
                    utd.setDinner(dinner);
                    utd.getDinner().add(item3);

                    RealmList<ItemDetail> snack=new  RealmList<>();
                    utd.setSnack(snack);
                    utd.getSnack().add(item4);

                    user.getUserTrackingData().add(utd);
                }
            });
            MainActivity.datePointer.add(Calendar.DATE,1);
        }
        MainActivity.this.recreate();
    }


/*
Basal metabolic rate (BMR)
Nam: [ (13.397 x Trọng lượng kg) + (4.799 x Chiều cao cm) - (5.677 x Tuổi năm) + 88.362) ]
Nữ  : [ (9.247 x Trọng lượng kg) + (3.098 x Chiều cao cm) - (4.330 x Tuổi năm) + 447.593) ]

Total Daily Energy Expenditure (TDEE)
Kiểu người:
Nhóm 1. Ít hoặc không vận động: BMR x 1.2
Nhóm 2. Vận động nhẹ: 1-3 lần/1 tuần: BMR x 1.375
Nhóm 3. Vận động vừa phải: 3-5 lần/ 1 tuần:  BMR x 1.55
Nhóm 4. Vận động nhiều: 6-7 lần/1 tuần: BMR x 1.725
Nhóm 5. Vận động nặng: Trên 7 lần 1 tuần: BMR x 1.9
* */


//create some fake data
// filter monthly
// add avatar

}
