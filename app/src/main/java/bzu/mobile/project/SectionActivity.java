package bzu.mobile.project;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;
import bzu.mobile.project.adapters.SectionItemsAdapter;
import bzu.mobile.project.models.SectionItem;

import java.util.ArrayList;

public class SectionActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private GridView SectionGrid;
    ArrayList<SectionItem> SectionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);

        SectionGrid = findViewById(R.id.sectionGrid);
        SectionList = new ArrayList<>();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        SectionItem item1 = new SectionItem();
        item1.setPrice("10$");
        item1.setImgUrl(R.drawable.bag);

        SectionItem item2 = new SectionItem();
        item2.setPrice("25$");
        item2.setImgUrl(R.drawable.bag);

        SectionItem item3 = new SectionItem();
        item3.setPrice("30$");
        item3.setImgUrl(R.drawable.bag);

        SectionList.add(item1);
        SectionList.add(item2);
        SectionList.add(item3);

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
