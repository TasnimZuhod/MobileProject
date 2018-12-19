package bzu.mobile.project.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import bzu.mobile.project.BuyItemActivity;
import bzu.mobile.project.R;
import bzu.mobile.project.dbModels.CartItem;
import bzu.mobile.project.models.SectionItem;

public class CartItemsAdapter extends BaseAdapter {

    private ArrayList<CartItem> CartList;
    private Context context;

    public CartItemsAdapter(ArrayList<CartItem> CartList, Context context){
        this.CartList = CartList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return CartList.size();
    }

    @Override
    public Object getItem(int i) {
        return CartList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View myView = inflater.inflate(R.layout.section_item,null);

        ImageView cartItemImage = myView.findViewById(R.id.section_img);
        TextView cartItemPrice = myView.findViewById(R.id.section_price);

        final int imageUrl = CartList.get(i).getImgUrl();
        final String price = CartList.get(i).getPrice();

        cartItemImage.setImageDrawable(context.getResources().getDrawable(imageUrl));
        cartItemPrice.setText(price);

//        sectionImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, BuyItemActivity.class);
//                intent.putExtra("IMAGE_ID", imageUrl);
//                intent.putExtra("PRICE", price);
//                intent.putExtra("DESCRIPTION", description);
//                context.startActivity(intent);
//
//                //context.startActivity(new Intent(context, BuyItemActivity.class));
//            }
//        });
        return myView;
    }
}
