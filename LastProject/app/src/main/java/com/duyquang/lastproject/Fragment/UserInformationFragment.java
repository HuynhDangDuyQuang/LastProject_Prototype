package com.duyquang.lastproject.Fragment;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.duyquang.lastproject.NutritionixObject.User;
import com.duyquang.lastproject.NutritionixObject.UserTrackingData;
import com.duyquang.lastproject.R;

import io.realm.Realm;
import io.realm.RealmList;


public class UserInformationFragment extends Fragment {

    EditText name;
    EditText age;
    RadioButton female;
    RadioButton male;
    EditText height;
    EditText weight;
    Spinner activity;
    EditText caloriesGoal;
    TextView recommendLabel;
    Button confirmBtn;

    Realm realm;
    User user;

    public static UserInformationFragment newInstance(){
        UserInformationFragment fragment=new UserInformationFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_information, container, false);
        name=view.findViewById(R.id.NameField);
        age=view.findViewById(R.id.AgeField);
        female=view.findViewById(R.id.female);
        male=view.findViewById(R.id.male);
        height=view.findViewById(R.id.HeightField);
        weight=view.findViewById(R.id.WeightField);
        activity=view.findViewById(R.id.activitySpinner);
        caloriesGoal=view.findViewById(R.id.GoalField);
        recommendLabel=view.findViewById(R.id.recommendLabel);
        confirmBtn=view.findViewById(R.id.btnConfirm);
        recommendLabel.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        realm = Realm.getDefaultInstance();
        user= realm.where(User.class).findFirst();

        if(user!=null){
            name.setText(user.getName());
            age.setText(user.getAge()+"");

            if(user.isFemale()) female.setChecked(true);
            else male.setChecked(true);

            height.setText(user.getHeight()+"");
            weight.setText(user.getWeight()+"");
            activity.setSelection(user.getActive());
            caloriesGoal.setText(user.getGoal()+"");
        }

//        activity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                    if(!age.getText().toString().isEmpty()
//                            &&!height.getText().toString().isEmpty()
//                            &&!weight.getText().toString().isEmpty()){
//                        double recommendCalPerDay;
//                        if(female.isChecked())
//                            recommendCalPerDay=( (9.247 * Float.parseFloat(weight.getText().toString()))
//                                    + (3.098 * Float.parseFloat(height.getText().toString()))
//                                    - (4.330 * Float.parseFloat(age.getText().toString())) + 447.593 );
//
//                        else
//                            recommendCalPerDay=( (13.397 * Float.parseFloat(weight.getText().toString()))
//                                    + (4.799 * Float.parseFloat(height.getText().toString()))
//                                    - (5.677 * Float.parseFloat(age.getText().toString())) + 88.362 );
//
//                        double rate=0;
//                        switch (activity.getSelectedItemPosition()){
//                            case 1:{
//                                rate =  1.2;
//                                break;
//                            }
//                            case 2:{
//                                rate = 1.375;
//                                break;
//                            }
//                            case 3:{
//                                rate = 1.55;
//                                break;
//                            }
//                            case 4:{
//                                rate = 1.725;
//                                break;
//                            }
//                            case 5:{
//                                rate = 1.9;
//                                break;
//                            }
//                            default: break;
//                        }
//
//                        caloriesGoal.setText((int)(rate*recommendCalPerDay)+"");
//
//                    }
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> adapterView) {
//
//                }
//            }
//        );

        recommendLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(activity.getSelectedItemPosition()!=0
                        &&!name.getText().toString().trim().isEmpty()
                        &&!age.getText().toString().isEmpty()
                        &&!height.getText().toString().isEmpty()
                        &&!weight.getText().toString().isEmpty()) {
                    double recommendCalPerDay;
                    if(female.isChecked())
                        recommendCalPerDay=( (9.247 * Float.parseFloat(weight.getText().toString()))
                                + (3.098 * Float.parseFloat(height.getText().toString()))
                                - (4.330 * Float.parseFloat(age.getText().toString())) + 447.593 );

                    else
                        recommendCalPerDay=( (13.397 * Float.parseFloat(weight.getText().toString()))
                                + (4.799 * Float.parseFloat(height.getText().toString()))
                                - (5.677 * Float.parseFloat(age.getText().toString())) + 88.362 );

                    double rate=0;
                    switch (activity.getSelectedItemPosition()){
                        case 1:{
                            rate =  1.2;
                            break;
                        }
                        case 2:{
                            rate = 1.375;
                            break;
                        }
                        case 3:{
                            rate = 1.55;
                            break;
                        }
                        case 4:{
                            rate = 1.725;
                            break;
                        }
                        case 5:{
                            rate = 1.9;
                            break;
                        }
                        default: break;
                    }

                    caloriesGoal.setText((int)(rate*recommendCalPerDay)+"");
                }
                else Toast.makeText(UserInformationFragment.this.getActivity(),"Fill all above fields so that i can recommend this for you :-?",Toast.LENGTH_SHORT).show();
            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(activity.getSelectedItemPosition()!=0
                        &&!name.getText().toString().trim().isEmpty()
                        &&!age.getText().toString().isEmpty()
                        &&!height.getText().toString().isEmpty()
                        &&!weight.getText().toString().isEmpty()
                        &&!caloriesGoal.getText().toString().isEmpty()) {
                    boolean isCreated;
                    if(user!=null) isCreated=true;
                    else isCreated =false;

                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            if(user==null) {
                                user = realm.createObject(User.class);
                                RealmList<UserTrackingData> userTrackingData= new RealmList<>();
                                user.setUserTrackingData(userTrackingData);
                            }
                            user.setName(name.getText().toString());
                            user.setAge(Integer.parseInt(age.getText().toString()));
                            user.setFemale(female.isChecked());
                            user.setHeight(Integer.parseInt(height.getText().toString()));
                            user.setWeight(Integer.parseInt(weight.getText().toString()));
                            user.setActive(activity.getSelectedItemPosition());
                            user.setGoal(Integer.parseInt(caloriesGoal.getText().toString()));
                        }
                    });

                    if(!isCreated) {
                        Toast.makeText(UserInformationFragment.this.getActivity(), "Welcoming!!", Toast.LENGTH_SHORT).show();
                        UserInformationFragment.this.getActivity().finish();
                    }
                    else {
                        Toast.makeText(UserInformationFragment.this.getActivity(),"User Information Updated!!",Toast.LENGTH_SHORT).show();
                        UserInformationFragment.this.getActivity().recreate();
                    }
                }
                else Toast.makeText(UserInformationFragment.this.getActivity(),"Fill all field so that i can help you :(",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
