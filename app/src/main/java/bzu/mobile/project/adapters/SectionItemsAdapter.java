package bzu.mobile.project.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import bzu.mobile.project.BuyItemActivity;
import bzu.mobile.project.models.SectionItem;
import bzu.mobile.project.R;

import java.util.ArrayList;

public class SectionItemsAdapter extends BaseAdapter {

    private ArrayList<SectionItem> SectionList;
    private Context context;

    public SectionItemsAdapter(ArrayList<SectionItem> SectionList, Context context){
        this.SectionList = SectionList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return SectionList.size();
    }

    @Override
    public Object getItem(int i) {
        return SectionList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View myView = inflater.inflate(R.layout.section_item,null);

        ImageView sectionImage = myView.findViewById(R.id.section_img);
        TextView sectionPrice = myView.findViewById(R.id.section_price);

        final int imageUrl = SectionList.get(i).getImgUrl();
        final String price = SectionList.get(i).getPrice();
        final String description = SectionList.get(i).getDescription();

        sectionImage.setImageDrawable(context.getResources().getDrawable(imageUrl));
        sectionPrice.setText(price);


        sectionImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BuyItemActivity.class);
                intent.putExtra("IMAGE_ID", imageUrl);
                intent.putExtra("PRICE", price);
                intent.putExtra("DESCRIPTION", description);
                context.startActivity(intent);

                //context.startActivity(new Intent(context, BuyItemActivity.class));
            }
        });
        return myView;
    }
}
