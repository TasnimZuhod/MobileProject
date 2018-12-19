package bzu.mobile.project;

import android.content.Intent;
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
        String [] description = new String[8];

        switch (categoryName) {
            case "Men":
                imagesIds = new int[]{R.drawable.men1, R.drawable.men2, R.drawable.men3, R.drawable.men4, R.drawable.men5, R.drawable.men6, R.drawable.men7, R.drawable.men8};
                prices = new String[]{"15$", "30$", "35$", "20$", "25$", "40$", "45$", "17$"};
                description = new String[]{"Fanideaz Men’s Half Sleeve Navy Blue with White Contrast","Blue, While & Black Casual Shirt", "Men's Fashion Long Sleeve T-shirt Cotton Casual Round Neck Sweatshirt", "Polo Steak T Shirt For Men", "Men's White Dress Shirt", "Sunshiny polka dot shirts for men, slim fit and long shirt", "Good Man Brand Slim Fit Henley | Nordstrom", "Timberland North Twin MT INS navy" };
                break;
            case "Women":
                imagesIds = new int[]{R.drawable.women1, R.drawable.women2, R.drawable.women3, R.drawable.women4, R.drawable.women5, R.drawable.women6, R.drawable.women7, R.drawable.women8};
                prices = new String[]{"10$", "30$", "20$", "45$", "30$", "40$", "15$", "25$"};
                description = new String[]{"Vintage Fashion Paris Girl T Shirt", "Elegant Women Floral Lace Bodycon Summer Dress","Red Mini Skirts", "2018 Women Straight Mini Dress Long Sleeve Off Shoulder", "Strapless Chiffon Gown Color Desert Rose", "Women Formal Office Blazer Suits Dress", "Hanes Women's Essential Short-Sleeve V-neck T-Shirt", "Women Skirts 2017 Elegant Vintage Pleated Frill Ruched High Waist" };
                break;
            case "Home & Garden":
                imagesIds = new int[]{R.drawable.garden1, R.drawable.garden2, R.drawable.garden3, R.drawable.garden4, R.drawable.garden5, R.drawable.garden6, R.drawable.garden7, R.drawable.garden8};
                prices = new String[]{"100$", "5$", "10$", "12$", "20$", "10$", "8$", "15$"};
                description = new String[]{"Cobra electric lawn mower ", "Fruit/Vegetables peeler knife | Kitchen Wish", "Plastic Kitchen - vogue plastic kitchen foil", "Gardening Galvanized Steel Watering Can ", "Garden Tools Set Kids Leaf Rake Spade Hoe Leaf Yard Play Toys Grow 4 Pieces New", "Glass Containers", "KHGDNOR Stylish Stainless Steel Stand Oven Safe Taco Truck", "Pack of 3 hardware tools - Carbon Steel Hardened And Tempered."};
                break;
            case "Accessories":
                imagesIds = new int[]{R.drawable.accessories1, R.drawable.accessories2, R.drawable.accessories3, R.drawable.accessories4, R.drawable.accessories5, R.drawable.accessories6, R.drawable.accessories7, R.drawable.accessories8};
                prices = new String[]{"5$", "15$", "10$", "4$", "10$", "15$", "20$", "10$"};
                description = new String[]{"Miniera Sand Oval Earring", "Kymyad Choker Necklace", "Bling Jewelry White Pearl Three Strand Choker Bridal Necklace", "Yellow Fan Dangle Earrings ", "Oval Earrings with Silver Filigree and Shell Detail", "Links Pendant Necklace with Swarovski® Crystals", "Linked clusters of elegant crystals Necklace", "Sterling Silver Linear Links Earrings" };
                break;
            case "Kids & Babies":
                imagesIds = new int[]{R.drawable.kids1, R.drawable.kids2, R.drawable.kids3, R.drawable.kids4, R.drawable.kids5, R.drawable.kids6, R.drawable.kids7, R.drawable.kids8};
                prices = new String[]{"20$", "15$", "30$", "20", "25$", "30$", "15$", "20$"};
                description = new String[]{"Webla Newborn Infant Baby Girl Letter I'm My Daddy's Girl and My Mummy's World Romper +Tutu Skirt+Headbands", "1-6 Year Cartoon Whirlybird Boys Girls T Shirt Summer Baby Kids Shorts Sleeve", "Toy Balloon kids White Self Design Fit and Flare Dress", "Hongxin Boys T-Shirt Kids Tees children Cartoon Spring Tee ", "2017 girls off-the-shoulder cute printed dresses unlined upper garment dress with short", "Carter's 3-pc. Kitty Terry Bodysuit, Jacket & Pants Set - Baby 0-12 Mos", "Kids Long Sleeve T-shirts 18M -6 years old", "Jumpsuit Ruffles Strappy Bodysuit Stripe Romper Shorts Cute Outfits" };
                break;
            case "Shoes & Bags":
                imagesIds = new int[]{R.drawable.shoes1, R.drawable.shoes2, R.drawable.shoes3, R.drawable.shoes4, R.drawable.shoes5, R.drawable.shoes6, R.drawable.shoes7, R.drawable.shoes8};
                prices = new String[]{"25$", "40$", "30$", "20", "35$", "25$", "30$", "40$"};
                description = new String[]{"GB Girls Quilted Crossbody Handbag", "Sparkly glitter on a prom shoe is always a good idea! Love the rose gold", "Black Stiletto High Heel Shoes for Women", "BAOSHA HB-24 Ladies Women Canvas Weekender Bag ", "Shoulder leather handbags with adjustable across shoulder ", "Guess Gold Sandal Women", "Thin and High Heels Shoes Mixed Colors Shallow", "Ericdress Color Block Stripe Women Handbag" };
                break;
        }

        for(int i=0; i<imagesIds.length; i++){
            SectionItem item = new SectionItem();
            item.setPrice(prices[i]);
            item.setImgUrl(imagesIds[i]);
            item.setDescription(description[i]);
            SectionList.add(item);
        }

        SectionItemsAdapter SectionListAdpater = new SectionItemsAdapter(SectionList,this);
        SectionGrid.setAdapter(SectionListAdpater);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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
