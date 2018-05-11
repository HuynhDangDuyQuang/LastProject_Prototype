package com.duyquang.lastproject.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.duyquang.lastproject.Activity.FoodDetailActivity;
import com.duyquang.lastproject.Activity.MainActivity;
import com.duyquang.lastproject.NutritionixObject.ItemDetail;
import com.duyquang.lastproject.NutritionixObject.UserTrackingData;
import com.duyquang.lastproject.R;

import java.util.List;

import io.realm.Realm;

public class FoodListAdapter extends BaseAdapter {

    Context mContext;
    List<ItemDetail> items;
    String time;
    Realm realm;
    UserTrackingData utd;

    public FoodListAdapter(Context ctx, List<ItemDetail> items,String time) {
        // TODO Auto-generated constructor stub
        this.mContext=ctx;
        this.items=items;
        this.time=time;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return items.size();
    }

    @Override
    public Object getItem(int pos) {
        // TODO Auto-generated method stub
        return items.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        // TODO Auto-generated method stub
        return items.indexOf(getItem(pos));
    }

    boolean isSent=true;
    @Override
    public View getView(final int pos, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final ItemDetail item=items.get(pos);
        LayoutInflater inflater=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.date_food_item, parent,false);
        }
        TextView foodName=convertView.findViewById(R.id.foodName);
        TextView foodQuantity=convertView.findViewById(R.id.foodQuantity);
        TextView calories =convertView.findViewById(R.id.calories);

        foodName.setText(item.getItem_name());

        foodQuantity.setText("1 unit");

        calories.setText(item.getNf_calories().intValue()+"");
        realm=Realm.getDefaultInstance();


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence action[] = new CharSequence[] {"Delete","Detail"};

                final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle(item.getItem_name());
                builder.setItems(action, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // the user clicked on colors[which]
                        if(which==0) {
                            realm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    item.deleteFromRealm();
                                }
                            });
                            ((MainActivity)mContext).recreate();
                        }
                        else {
                            Intent i=new Intent(mContext,FoodDetailActivity.class);
                            i.putExtra("id",item.getItem_id());
                            i.putExtra("willAdd",false);
                            mContext.startActivity(i);
                        }

                    }
                });
                builder.show();
            }
        });
        return convertView;
    }
}
