package bzu.mobile.project;

import android.content.Intent;
import android.os.AsyncTask;
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
import java.util.HashMap;
import java.util.Map;

public class CartActivity extends AppCompatActivity {

    private static final String PRICE_VALUE = "price_value";
    private static String url_convert_currency = "http://10.0.2.2/android_connect/currency_converter.php";

    private TextView mTextMessage;
    private GridView cartGrid;
    private TextView sectionTitle;
    ArrayList<CartItem> selectedItemsList;

    private TextView convertedPrice;

    private static String response = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        convertedPrice = (TextView) findViewById(R.id.convertedPrice);

        cartGrid = findViewById(R.id.cartGrid);
        sectionTitle = findViewById(R.id.CartTitleTxtView);

        selectedItemsList = new ArrayList<>();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

        selectedItemsList = dbHandler.getCartItems();

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


    public void ConvertCurrecny(View view) {
        new ConvertCurrencyAsyncTask().execute();

    }

    private class ConvertCurrencyAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {
            HttpJsonParser httpJsonParser = new HttpJsonParser();
            Map<String, String> httpParams = new HashMap<>();

            int totalPrice = 0;
            for(int i=0;i<selectedItemsList.size();i++) {
                totalPrice += Integer.parseInt(
                        selectedItemsList.get(i).getPrice().substring(0, selectedItemsList.get(i).getPrice().length() - 1));
            }
            String priceVal = String.valueOf(totalPrice);
            httpParams.put(PRICE_VALUE, priceVal);
            response = httpJsonParser.makeHttpRequest(
                    url_convert_currency, "POST", httpParams);
            return null;
        }

        protected void onPostExecute(String result) {
            runOnUiThread(new Runnable() {
                public void run() {
                    convertedPrice.setText(response + convertedPrice.getText());
                }
            });
        }

    }

}
