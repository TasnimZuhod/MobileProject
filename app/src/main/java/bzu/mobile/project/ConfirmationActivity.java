package bzu.mobile.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class ConfirmationActivity extends AppCompatActivity {

    private static final String PRICE_VALUE = "price_value";
    private static String url_convert_currency = "http://10.0.2.2/android_connect/currency_converter.php";
    private TextView mTextMessage;
    private ImageView confirmItemImgView;
    private TextView confirmItemPriceLbl;
    int imageID;
    String price;

    private TextView priceLabel;
    private TextView convertedPrice;

    private static String response = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_buy);

        priceLabel = (TextView) findViewById(R.id.confirmPriceLabel);
        convertedPrice = (TextView) findViewById(R.id.convertedPrice);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        imageID= getIntent().getIntExtra("IMAGE_ID", -1);
        price= getIntent().getStringExtra("PRICE");

        confirmItemImgView = (ImageView) findViewById(R.id.confirmImageView);
        confirmItemPriceLbl = (TextView) findViewById(R.id.confirmPriceLabel);

        confirmItemImgView.setImageResource(imageID);
        confirmItemPriceLbl.setText(price);
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
                case R.id.navigation_dashboard:
                    intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_notifications:
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

    public void OnConfirmButtonClick(View view) {
        Toast.makeText(this, "Done !", Toast.LENGTH_LONG).show();
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

            String priceVal = (String) priceLabel.getText().subSequence(0, priceLabel.getText().length() - 1);
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


