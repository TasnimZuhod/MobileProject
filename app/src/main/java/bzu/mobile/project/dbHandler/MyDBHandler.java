package bzu.mobile.project.dbHandler;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

import bzu.mobile.project.dbModels.CartItem;
import bzu.mobile.project.dbModels.User;
import bzu.mobile.project.models.SectionItem;

public class MyDBHandler  extends SQLiteOpenHelper {
    //information of database
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "userDB.db";

    public static final String TABLE_USERS = "Users";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";

    public static final String TABLE_CART_ITEMS = "CartItems";
    public static final String COLUMN_CartID = "_id";
    public static final String COLUMN_IMGURL = "imageUrl";
    public static final String COLUMN_PRICE = "price";


    //initialize the database
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CART_Items_TABLE = "CREATE TABLE " +
                TABLE_CART_ITEMS + "("
                + COLUMN_CartID + " INTEGER PRIMARY KEY," + COLUMN_IMGURL
                + " INTEGER," + COLUMN_PRICE + " TEXT" + ")";
        db.execSQL(CREATE_CART_Items_TABLE);
        Log.i("db", "cart table is added");

        String CREATE_USERS_TABLE = "CREATE TABLE " +
                TABLE_USERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_EMAIL
                + " TEXT," + COLUMN_PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);

        Log.i("db", "users table is added");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public void addUser(User user) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, user.getUserEmail());
        values.put(COLUMN_PASSWORD, user.getUserPassword());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public User findUser(String userEmail) {
        String query = "Select * FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + " = " + "'" + userEmail + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User user = new User();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            user.setUserEmail(cursor.getString(1));
            user.setUserPassword(cursor.getString(2));
            cursor.close();
        } else {
            user = null;
        }
        db.close();
        return user;
    }

    public void addCartItem(CartItem cartItem) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_IMGURL, cartItem.getImgUrl());
        values.put(COLUMN_PRICE, cartItem.getPrice());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_CART_ITEMS, null, values);
        db.close();
    }

    public void deleteCartItems() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Delete FROM " + TABLE_CART_ITEMS;
        Cursor cursor = db.rawQuery(query, null);
        cursor.close();
        db.close();
    }

    public ArrayList<CartItem> getCartItems() {
        ArrayList<CartItem> cartItemsList = new ArrayList<CartItem>();
        String query = "Select * FROM " + TABLE_CART_ITEMS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        CartItem cartItem;

        while (cursor.moveToNext()) {
            Integer imageId = cursor.getInt(1);
            String price = cursor.getString(2);
            cartItem = new CartItem(imageId, price);
            cartItemsList.add(cartItem);
        }
        cursor.close();
        db.close();
        return cartItemsList;
    }

    public String loadHandler() {
        String result = "";
        String query = "Select * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            String email = cursor.getString(1);
            String password = cursor.getString(2);
            result += String.valueOf(email) + " " + password +
                    System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }
}