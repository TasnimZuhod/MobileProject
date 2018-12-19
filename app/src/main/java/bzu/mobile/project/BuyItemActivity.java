package bzu.mobile.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import bzu.mobile.project.dbHandler.MyDBHandler;
import bzu.mobile.project.dbModels.CartItem;
import bzu.mobile.project.dbModels.User;

public class BuyItemActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private ImageView itemImageView;
    private TextView itemPriceLabel;
    private TextView itemDescLabel;
    int imageID;
    String price;
    String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_item);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        imageID= getIntent().getIntExtra("IMAGE_ID", -1);
        price= getIntent().getStringExtra("PRICE");
        description= getIntent().getStringExtra("DESCRIPTION");

        itemImageView = (ImageView) findViewById(R.id.buyItemImg);
        itemPriceLabel = (TextView) findViewById(R.id.buyItemPrice);
        itemDescLabel = (TextView) findViewById(R.id.buyItemDesc);

        itemImageView.setImageResource(imageID);
        itemPriceLabel.setText(price);
        itemDescLabel.setText(description);
    }

    public void OnBuyButtonClick(View view) {
        Intent intent = new Intent(this, ConfirmationActivity.class);
        intent.putExtra("IMAGE_ID", imageID);
        intent.putExtra("PRICE", price);
        startActivity(intent);
    }

    public void OnAddToCartButtonClick(View view){
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        Integer itemImageID = imageID;
        String itemPrice = price;
        CartItem cartItem = new CartItem(itemImageID, itemPrice);
        dbHandler.addCartItem(cartItem);
        Toast.makeText(this, "Added successfully!", Toast.LENGTH_LONG).show();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent intent;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_cart:
                    intent = new Intent(getApplicationContext(), CartActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_profile:
                    intent = new Intent(getApplicationContext(), UserprofileActivity.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };


    public void OnConfirmButtonClick(View view) {
    }

}
