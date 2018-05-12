package com.duyquang.lastproject.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.duyquang.lastproject.Adapter.DetailFilterAdapter;
import com.duyquang.lastproject.Adapter.FilterAdapter;
import com.duyquang.lastproject.NutritionixObject.Hit;
import com.duyquang.lastproject.NutritionixObject.NutritionixSearch;
import com.duyquang.lastproject.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FoodSearchActivity extends AppCompatActivity {

    public static String time;
    ListView lv;
    SearchView inputSearch;
    ProgressBar loadingSearchResultBar;
    Handler mHandler=new Handler();
    List<Hit> hits=new ArrayList<Hit>();
    NutritionixSearch nutritionixSearch;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_search);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            time=bundle.getString("time");
        }

        lv = (ListView) findViewById(R.id.list_view);
        inputSearch = (SearchView) findViewById(R.id.inputSearch);
        loadingSearchResultBar=findViewById(R.id.loadingSearchResultBar);
        loadingSearchResultBar.setVisibility(View.GONE);

        FilterAdapter adapter = new FilterAdapter(FoodSearchActivity.this, hits);
        lv.setAdapter(adapter);

        inputSearch.requestFocus();
        inputSearch.setIconified(false);
        inputSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String arg0) {
                // TODO Auto-generated method stub
                String query=arg0.trim();
                if(query != null && query.length()>0)
                {
                    loadingSearchResultBar.setVisibility(View.VISIBLE);

                    final String searchURL=getSearchURL(query.toString());
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(searchURL)
                            .build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    Toast.makeText(FoodSearchActivity.this,"No Internet Connection",Toast.LENGTH_LONG).show();
                                }
                            });
                            e.printStackTrace();
                            loadingSearchResultBar.setVisibility(View.GONE);
                        }
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {

                            String rawNutritionixSearch = response.body().string();
                            Gson gson = new Gson();
                            nutritionixSearch = (NutritionixSearch) gson.fromJson(rawNutritionixSearch, NutritionixSearch.class);
                            hits =  nutritionixSearch.getHits();

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    DetailFilterAdapter adapter = new DetailFilterAdapter(FoodSearchActivity.this, hits);
                                    lv.setAdapter(adapter);
                                    loadingSearchResultBar.setVisibility(View.GONE);
                                }
                            });
                        }
                    });
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // TODO Auto-generated method stub
//                mQuerry=query;
                final String constraint=query.trim();
                mHandler.removeCallbacksAndMessages(null);

                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(constraint != null && constraint.length()>0)
                        {
                            loadingSearchResultBar.setVisibility(View.VISIBLE);
                            final String searchURL=getSearchURL(constraint.toString());
                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder()
                                    .url(searchURL)
                                    .build();
                            client.newCall(request).enqueue(new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {
                                    runOnUiThread(new Runnable() {
                                        public void run() {
                                            Toast.makeText(FoodSearchActivity.this,"No Internet Connection",Toast.LENGTH_LONG).show();
                                        }
                                    });
                                    e.printStackTrace();
                                }
                                @Override
                                public void onResponse(Call call, Response response) throws IOException {

                                    String rawNutritionixSearch = response.body().string();
                                    Gson gson = new Gson();
                                    nutritionixSearch = (NutritionixSearch) gson.fromJson(rawNutritionixSearch, NutritionixSearch.class);
                                    hits =  nutritionixSearch.getHits();

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            FilterAdapter adapter = new FilterAdapter(FoodSearchActivity.this, hits);
                                            lv.setAdapter(adapter);
                                            loadingSearchResultBar.setVisibility(View.GONE);
                                        }
                                    });
                                }
                            });
                        }
                    }
                }, 500);

                if (TextUtils.isEmpty(query)){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            lv.setAdapter(new FilterAdapter(FoodSearchActivity.this,new ArrayList<Hit>()));
                        }
                    });
                }
                return true;
            }
        });
    }

    private String getSearchURL(String keyWord){
        String headPart="https://api.nutritionix.com/v1_1/search/";
//        String optionPart="?results=0%3A20&fields=item_name%2Cbrand_name%2Citem_id%2Cbrand_id%2Citem_description%2Cnf_ingredient_statement%2Cnf_calories%2Cnf_calories_from_fat%2Cnf_total_fat%2Cnf_cholesterol";
        String optionPart="?results=0%3A20&fields=item_name%2Cbrand_name%2Citem_id%2Cnf_calories%2Cnf_total_fat%2Cnf_cholesterol%2Cnf_protein%2Cnf_total_carbohydrate";
        String authPart="&appId=da1adb3d&appKey=5a520c8e9d788cd66dc0b2b3a26ef34b";
        keyWord.replace(" ","%20");
        return  (headPart+keyWord+optionPart+authPart);
    }
}
