package bzu.mobile.project;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import bzu.mobile.project.adapters.SectionItemsAdapter;
import bzu.mobile.project.models.SectionItem;

import java.util.ArrayList;

public class SectionActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private GridView SectionGrid;
    private TextView sectionTitle;
    ArrayList<SectionItem> SectionList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);

        SectionGrid = findViewById(R.id.sectionGrid);
        sectionTitle = findViewById(R.id.SectionTitleTxtView);

        SectionList = new ArrayList<>();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        String categoryName= getIntent().getStringExtra("CATEGORY_NAME");

        sectionTitle.setText(categoryName + " Section");

        int[] imagesIds = new int[8];
        String[] prices = new String[8];

        switch (categoryName) {
            case "Men":
                imagesIds = new int[]{R.drawable.men1, R.drawable.men2, R.drawable.men3, R.drawable.men4, R.drawable.men5, R.drawable.men6, R.drawable.men7, R.drawable.men8};
                prices = new String[]{"15$", "30$", "35$", "20$", "25$", "40$", "45$", "17$"};

                break;
            case "Women":
                imagesIds = new int[]{R.drawable.women1, R.drawable.women2, R.drawable.women3, R.drawable.women4, R.drawable.women5, R.drawable.women6, R.drawable.women7, R.drawable.women8};
                prices = new String[]{"10$", "30$", "20$", "45$", "30$", "40$", "15$", "25$"};
                break;
            case "Home & Garden":
                imagesIds = new int[]{R.drawable.garden1, R.drawable.garden2, R.drawable.garden3, R.drawable.garden4, R.drawable.garden5, R.drawable.garden6, R.drawable.garden7, R.drawable.garden8};
                prices = new String[]{"100$", "5$", "10$", "12$", "20$", "10$", "8$", "15$"};
                break;
            case "Accessories":
                imagesIds = new int[]{R.drawable.accessories1, R.drawable.accessories2, R.drawable.accessories3, R.drawable.accessories4, R.drawable.accessories5, R.drawable.accessories6, R.drawable.accessories7, R.drawable.accessories8};
                prices = new String[]{"5$", "15$", "10$", "4$", "10$", "15$", "20$", "10$"};
                break;
            case "Kids & Babies":
                imagesIds = new int[]{R.drawable.kids1, R.drawable.kids2, R.drawable.kids3, R.drawable.kids4, R.drawable.kids5, R.drawable.kids6, R.drawable.kids7, R.drawable.kids8};
                prices = new String[]{"20$", "15$", "30$", "20", "25$", "30$", "15$", "20$"};
                break;
            case "Shoes & Bags":
                imagesIds = new int[]{R.drawable.shoes1, R.drawable.shoes2, R.drawable.shoes3, R.drawable.shoes4, R.drawable.shoes5, R.drawable.shoes6, R.drawable.shoes7, R.drawable.shoes8};
                prices = new String[]{"25$", "40$", "30$", "20", "35$", "25$", "30$", "40$"};
                break;
        }

        for(int i=0; i<imagesIds.length; i++){
            SectionItem item = new SectionItem();
            item.setPrice(prices[i]);
            item.setImgUrl(imagesIds[i]);
            SectionList.add(item);
        }

        SectionItemsAdapter SectionListAdpater = new SectionItemsAdapter(SectionList,this);
        SectionGrid.setAdapter(SectionListAdpater);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_cart);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_profile);
                    return true;
            }
            return false;
        }
    };
}
