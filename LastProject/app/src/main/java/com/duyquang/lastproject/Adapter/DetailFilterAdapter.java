package com.duyquang.lastproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.duyquang.lastproject.Activity.FoodDetailActivity;
import com.duyquang.lastproject.Activity.FoodSearchActivity;
import com.duyquang.lastproject.NutritionixObject.Fields;
import com.duyquang.lastproject.NutritionixObject.Hit;
import com.duyquang.lastproject.R;

import java.util.List;

public class DetailFilterAdapter extends BaseAdapter {

    Context mContext;
    List<Hit> hits;

    public DetailFilterAdapter(Context ctx, List<Hit> hits) {
        // TODO Auto-generated constructor stub

        this.mContext=ctx;
        this.hits=hits;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return hits.size();
    }

    @Override
    public Object getItem(int pos) {
        // TODO Auto-generated method stub
        return hits.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        // TODO Auto-generated method stub
        return hits.indexOf(getItem(pos));
    }

    boolean isSent=true;
    @Override
    public View getView(final int pos, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final Hit hit=hits.get(pos);
        LayoutInflater inflater=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.detail_list_item, parent,false);
        }
        TextView itemName=(TextView) convertView.findViewById(R.id.item_name);
        TextView brandName=(TextView) convertView.findViewById(R.id.brand_name);
        TextView nfCalories=(TextView) convertView.findViewById(R.id.nf_calories);
        TextView nfProtein=convertView.findViewById(R.id.nf_protein);
        TextView nfCholesterol=convertView.findViewById(R.id.nf_cholesterol);
        TextView nfCarbohydrate=convertView.findViewById(R.id.nf_total_carbohydrate);
        TextView nfFat=convertView.findViewById(R.id.nf_total_fat);

        Fields fields=hit.getFields();
        itemName.setText(fields.getItem_name());
        brandName.setText(fields.getBrand_name());

        nfCalories.setText((fields.getNf_calories()!=null)?(fields.getNf_calories()+"cal"):(""));
        nfProtein.setText((fields.getNf_protein()!=null)?(fields.getNf_protein()+"g Protein"):(""));
        nfCholesterol.setText((fields.getNf_cholesterol()!=null)?(fields.getNf_cholesterol()+"mg Chol"):(""));
        nfCarbohydrate.setText((fields.getNf_total_carbohydrate()!=null)?(fields.getNf_total_carbohydrate()+"g Carb"):(""));
        nfFat.setText((fields.getNf_total_fat()!=null)?(fields.getNf_total_fat()+"g Fat"):(""));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(mContext,FoodDetailActivity.class);
                i.putExtra("time", FoodSearchActivity.time);
                i.putExtra("id",hit.getId());
                i.putExtra("willAdd",true);
                mContext.startActivity(i);

            }
        });
        return convertView;
    }
}
