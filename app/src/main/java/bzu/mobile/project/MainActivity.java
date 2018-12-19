package bzu.mobile.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import bzu.mobile.project.adapters.MarketCategoriesAdapter;
import bzu.mobile.project.models.MarketCategory;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private ListView marketCategories;
    ArrayList<MarketCategory> marketCategoriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_categories);

        mTextMessage = (TextView) findViewById(R.id.message);
        marketCategories = findViewById(R.id.listview);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        marketCategoriesList = new ArrayList<>();

        MarketCategory category1 = new MarketCategory();
        category1.setType("Shoes & Bags");
        category1.setImgUrl(R.drawable.shoes_bags);

        MarketCategory category2 = new MarketCategory();
        category2.setType("Women");
        category2.setImgUrl(R.drawable.women);

        MarketCategory category3 = new MarketCategory();
        category3.setType("Accessories");
        category3.setImgUrl(R.drawable.accessories);

        MarketCategory category4 = new MarketCategory();
        category4.setType("Men");
        category4.setImgUrl(R.drawable.men);

        MarketCategory category5 = new MarketCategory();
        category5.setType("Kids & Babies");
        category5.setImgUrl(R.drawable.kids);

        MarketCategory category6 = new MarketCategory();
        category6.setType("Home & Garden");
        category6.setImgUrl(R.drawable.home);

        marketCategoriesList.add(category1);
        marketCategoriesList.add(category2);
        marketCategoriesList.add(category3);
        marketCategoriesList.add(category4);
        marketCategoriesList.add(category5);
        marketCategoriesList.add(category6);

        MarketCategoriesAdapter marketCategoriesAdpater = new MarketCategoriesAdapter(marketCategoriesList,this);
        marketCategories.setAdapter(marketCategoriesAdpater);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.getMenu().getItem(0).setChecked(true);
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
