package com.duyquang.lastproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import com.duyquang.lastproject.Adapter.PagerAdapter;
import com.duyquang.lastproject.CustomViewPager;
import com.duyquang.lastproject.Fragment.DashBoardFragment;
import com.duyquang.lastproject.Fragment.StatsFragment;
import com.duyquang.lastproject.Fragment.UserInformationFragment;
import com.duyquang.lastproject.MyApp;
import com.duyquang.lastproject.NutritionixObject.User;
import com.duyquang.lastproject.R;

import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.exceptions.RealmMigrationNeededException;

public class MainActivity extends AppCompatActivity {

    private Realm realm;
    User user;
    public static Calendar datePointer= Calendar.getInstance();

    public static CustomViewPager viewPager;
    public static PagerAdapter pagerAdapter;



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
        }
    }

    @Override
    protected void onResume(){
        super.onResume();

//        user= realm.where(User.class).findFirst();
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
}
