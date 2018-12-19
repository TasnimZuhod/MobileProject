package bzu.mobile.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import bzu.mobile.project.adapters.CartItemsAdapter;
import bzu.mobile.project.adapters.SectionItemsAdapter;
import bzu.mobile.project.dbHandler.MyDBHandler;
import bzu.mobile.project.dbModels.CartItem;
import bzu.mobile.project.models.SectionItem;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private GridView cartGrid;
    private TextView sectionTitle;
    ArrayList<CartItem> selectedItemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartGrid = findViewById(R.id.cartGrid);
        sectionTitle = findViewById(R.id.CartTitleTxtView);

        selectedItemsList = new ArrayList<>();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

        //dbHandler.deleteCartItems();
        selectedItemsList = dbHandler.getCartItems();

        Toast.makeText(this, selectedItemsList.size()+"", Toast.LENGTH_LONG).show();

        CartItemsAdapter cartListAdpater = new CartItemsAdapter(selectedItemsList,this);
        cartGrid.setAdapter(cartListAdpater);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.getMenu().getItem(1).setChecked(true);
    }

    public void onCardConfirmBtnClick(View view) {
        Toast.makeText(this, "Done !", Toast.LENGTH_LONG).show();
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
}
