package com.duyquang.lastproject.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.duyquang.lastproject.Fragment.UserInformationFragment;
import com.duyquang.lastproject.R;

public class UserInformationActivity extends AppCompatActivity {

    UserInformationFragment userInformationFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);

        FragmentManager fragmentManager = getSupportFragmentManager();
        userInformationFragment = UserInformationFragment.newInstance();
        fragmentManager.beginTransaction().replace(R.id.container, userInformationFragment).commit();

    }
}
