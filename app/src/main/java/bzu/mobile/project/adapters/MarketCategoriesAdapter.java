package bzu.mobile.project.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import java.util.ArrayList;

import bzu.mobile.project.SectionActivity;
import bzu.mobile.project.models.MarketCategory;
import bzu.mobile.project.R;

public class MarketCategoriesAdapter extends BaseAdapter {

    private ArrayList<MarketCategory> marketCategoriesList;
    private Context context;

    public MarketCategoriesAdapter(ArrayList<MarketCategory> marketCategoriesList, Context context){
        this.marketCategoriesList = marketCategoriesList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return marketCategoriesList.size();
    }

    @Override
    public Object getItem(int i) {
        return marketCategoriesList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View myView = inflater.inflate(R.layout.category_item,null);

        AppCompatImageView categoryImage = myView.findViewById(R.id.category_img);
        AppCompatTextView categoryType = myView.findViewById(R.id.category_text);

        categoryImage.setImageDrawable(context.getResources().getDrawable(marketCategoriesList.get(i).getImgUrl()));
        categoryType.setText(marketCategoriesList.get(i).getType());

        categoryImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, SectionActivity.class));
            }
        });

        return myView;
    }
}
