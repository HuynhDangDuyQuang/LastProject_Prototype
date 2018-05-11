package com.duyquang.lastproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.duyquang.lastproject.NutritionixObject.ItemDetail;
import com.duyquang.lastproject.NutritionixObject.User;
import com.duyquang.lastproject.NutritionixObject.UserTrackingData;
import com.duyquang.lastproject.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmList;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FoodDetailActivity extends AppCompatActivity {

    TextView itemName;

    TextView caloriesValue;
    TextView caloriesFromFatValue;
    TextView totalFatValue;
    TextView cholesterolValue;
    TextView sodiumValue;
    TextView carbohydratesValue;
    TextView proteinValue;
    TextView vitaminADailyValue;
    TextView vitaminCDailyValue;
    TextView calciumDailyValue;
    TextView ironDailyValue;
    ProgressBar progressBar;

    Button addFood;

    ItemDetail itemDetail;
    String rawItemDetail;

    Realm realm;
    User user;
    UserTrackingData utd;

    Bundle bundle;
    String id="";
    String time;
    boolean willAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        bundle=getIntent().getExtras();

        itemName=findViewById(R.id.itemName);
        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        caloriesValue=findViewById(R.id.caloriesValue);
        caloriesFromFatValue=findViewById(R.id.caloriesFromFatValue);
        totalFatValue=findViewById(R.id.totalFatValue);
        cholesterolValue=findViewById(R.id.cholesterolValue);
        sodiumValue=findViewById(R.id.sodiumValue);
        carbohydratesValue=findViewById(R.id.carbohydratesValue);
        proteinValue=findViewById(R.id.proteinValue);
        vitaminADailyValue=findViewById(R.id.vitaminADailyValue);
        vitaminCDailyValue=findViewById(R.id.vitaminCDailyValue);
        calciumDailyValue=findViewById(R.id.calciumDailyValue);
        ironDailyValue=findViewById(R.id.ironDailyValue);

        addFood=findViewById(R.id.addFood);
        willAdd=false;
        if(bundle!=null)
            willAdd=bundle.getBoolean("willAdd");

        addFood.setVisibility(View.INVISIBLE);
        realm = Realm.getDefaultInstance();
        addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                utd=realm.where(UserTrackingData.class)
                        .equalTo("year",MainActivity.datePointer.get(Calendar.YEAR))
                        .equalTo("month",MainActivity.datePointer.get(Calendar.MONTH))
                        .equalTo("day",MainActivity.datePointer.get(Calendar.DATE))
                        .findFirst();

                if(bundle!=null){
                    time=bundle.getString("time");
                }

                if(utd!=null){
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            ItemDetail newItem=realm.createObjectFromJson(ItemDetail.class,rawItemDetail);
                            if(time.equals("Breakfast")){
                                if(utd.getBreakfast()!=null)
                                    utd.getBreakfast().add(newItem);
                                else {
                                    RealmList<ItemDetail> breakfast=new  RealmList<>();
                                    breakfast.add(newItem);
                                    utd.setBreakfast(breakfast);
                                }
                            }
                            else if(time.equals("Lunch")) {
                                if(utd.getLunch()!=null)
                                    utd.getLunch().add(newItem);
                                else {
                                    RealmList<ItemDetail> lunch=new  RealmList<>();
                                    lunch.add(newItem);
                                    utd.setLunch(lunch);
                                }
                            }
                            else if(time.equals("Dinner")) {
                                if(utd.getDinner()!=null)
                                    utd.getDinner().add(newItem);
                                else {
                                    RealmList<ItemDetail> dinner=new  RealmList<>();
                                    dinner.add(newItem);
                                    utd.setDinner(dinner);
                                }
                            }
                            else {
                                if(utd.getSnack()!=null)
                                    utd.getSnack().add(newItem);
                                else {
                                    RealmList<ItemDetail> snack=new  RealmList<>();
                                    snack.add(newItem);
                                    utd.setSnack(snack);
                                }
                            }
                        }
                    });
                }
                else{
                    user=realm.where(User.class).findFirst();
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            utd=realm.createObject(UserTrackingData.class);
                            utd.setYear(MainActivity.datePointer.get(Calendar.YEAR));
                            utd.setMonth(MainActivity.datePointer.get(Calendar.MONTH));
                            utd.setDay(MainActivity.datePointer.get(Calendar.DATE));

                            ItemDetail newItem=realm.createObjectFromJson(ItemDetail.class,rawItemDetail);
                            if(time.equals("Breakfast")){
                                RealmList<ItemDetail> breakfast=new  RealmList<>();
                                breakfast.add(newItem);
                                utd.setBreakfast(breakfast);
                            }
                            else if(time.equals("Lunch")) {
                                RealmList<ItemDetail> lunch=new  RealmList<>();
                                lunch.add(newItem);
                                utd.setLunch(lunch);
                            }
                            else if(time.equals("Dinner")) {
                                RealmList<ItemDetail> dinner=new  RealmList<>();
                                dinner.add(newItem);
                                utd.setDinner(dinner);
                            }
                            else {
                                RealmList<ItemDetail> snack=new  RealmList<>();
                                snack.add(newItem);
                                utd.setSnack(snack);
                            }
                            user.getUserTrackingData().add(utd);
                        }
                    });
                }

                Intent i=new Intent(FoodDetailActivity.this,MainActivity.class);

                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                setResult(MyApp.FOOD_DETAIL_ACTIVITY_RESULT_CODE,i);
                startActivity(i);

            }
        });



        if(bundle!=null){
            id=bundle.getString("id");
        }

        String foodDetailURL=getFoodDetailURL(id);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(foodDetailURL)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(FoodDetailActivity.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
                    }
                });
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                rawItemDetail=response.body().string();
                Gson gson=new Gson();
                itemDetail=gson.fromJson(rawItemDetail,ItemDetail.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        itemName.setText(itemDetail.getItem_name());

                        caloriesValue.setText(itemDetail.getNf_calories()+"");
                        caloriesFromFatValue.setText((itemDetail.getNf_calories_from_fat()!=null)?(itemDetail.getNf_calories_from_fat()+""):(""));
                        totalFatValue.setText((itemDetail.getNf_total_fat()!=null)?(itemDetail.getNf_total_fat()+"g"):(""));
                        cholesterolValue.setText((itemDetail.getNf_cholesterol()!=null)?(itemDetail.getNf_cholesterol()+"mg"):(""));
                        sodiumValue.setText((itemDetail.getNf_sodium()!=null)?(itemDetail.getNf_sodium()+"mg"):(""));
                        carbohydratesValue.setText((itemDetail.getNf_total_carbohydrate()!=null)?(itemDetail.getNf_total_carbohydrate()+"g"):(""));
                        proteinValue.setText((itemDetail.getNf_protein()!=null)?(itemDetail.getNf_protein()+"g"):(""));
                        vitaminADailyValue.setText((itemDetail.getNf_vitamin_a_dv()!=null)?(itemDetail.getNf_vitamin_a_dv()+"%"):(""));
                        vitaminCDailyValue.setText((itemDetail.getNf_vitamin_c_dv()!=null)?(itemDetail.getNf_vitamin_c_dv()+"%"):(""));
                        calciumDailyValue.setText((itemDetail.getNf_calcium_dv()!=null)?(itemDetail.getNf_calcium_dv()+"%"):(""));
                        ironDailyValue.setText((itemDetail.getNf_iron_dv()!=null)?(itemDetail.getNf_iron_dv()+"%"):(""));

                        progressBar.setVisibility(View.GONE);
                        if(willAdd) addFood.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
    }

    private String getFoodDetailURL(String id){
        String headPart="https://api.nutritionix.com/v1_1/item?id=";
        String authPart="&appId=da1adb3d&appKey=5a520c8e9d788cd66dc0b2b3a26ef34b";
        return  (headPart+id+authPart);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
