package com.application.smaqu.personalcv.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.application.smaqu.personalcv.pojo.CardViewItem;
import com.application.smaqu.personalcv.pojo.DetailListViewItem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SmaQu on 2017-12-22.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    public static final String TAG = "database_helper";
    private static final String DB_NAME = "datas.db";
    private final String DB_PATH;
    private static final int DB_VERSION = 1;

    public static final String DB_CARD_TABLE = "card_view_data";
    public static final String DB_LIST_TABLE = "list_view_data";

    public static final String KEY_CARD_VIEW_TABLE_ID = "_id";
    public static final String KEY_CARD_VIEW_TABLE_DESCRIPTION = "description";
    public static final String KEY_CARD_VIEW_TABLE_PHOTO = "photo";

    public static final String KEY_LIST_VIEW_TABLE_ID = "_id";
    public static final String KEY_LIST_VIEW_TABLE_CARD_VIEW_DESCRIPTION = "card_view_description";
    public static final String KEY_LIST_VIEW_TABLE_TOPIC = "topic";
    public static final String KEY_LIST_VIEW_TABLE_ICON = "icon";
    public static final String KEY_LIST_VIEW_TABLE_INFO = "info";
    public static final String KEY_LIST_VIEW_TABLE_DATE = "date";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
        DB_PATH = context.getApplicationContext().getFilesDir().getPath() + DB_NAME;
    }

    public void createDatabase() {
        if (!databaseExists()) {
            getReadableDatabase();
            //Copy database
            try {
                copyDatabase();
            } catch (IOException e) {
                Log.e(TAG, "Cannot copy database");
                e.printStackTrace();
            }
        }
    }

    public List<CardViewItem> getCardViewList() {
        List<CardViewItem> cardViewItemList = new ArrayList<>();
        try {
            openDatabase();
        } catch (SQLException e) {
            Log.e(TAG, "Cannot open database!");
            e.printStackTrace();
        }
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_CARD_TABLE, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            CardViewItem cardViewItem = new CardViewItem();
            cardViewItem.setDescription(cursor.getString(cursor.getColumnIndex(KEY_CARD_VIEW_TABLE_DESCRIPTION)));
            cardViewItem.setImage(cursor.getString(cursor.getColumnIndex(KEY_CARD_VIEW_TABLE_PHOTO)));
            cardViewItemList.add(cardViewItem);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return cardViewItemList;
    }

    public List<DetailListViewItem> getListViewList(String cardViewDescription) {
        List<DetailListViewItem> listViewItemList = new ArrayList<>();
        try {
            openDatabase();
        } catch (SQLException e) {
            Log.e(TAG, "Cannot open database!");
            e.printStackTrace();
        }
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_LIST_TABLE + " WHERE " + KEY_LIST_VIEW_TABLE_CARD_VIEW_DESCRIPTION + "=" + "'" + cardViewDescription + "'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            DetailListViewItem detailListViewItem = new DetailListViewItem();
            detailListViewItem.setCardViewDescription(cursor.getString(cursor.getColumnIndex(KEY_LIST_VIEW_TABLE_CARD_VIEW_DESCRIPTION)));
            detailListViewItem.setTopic(cursor.getString(cursor.getColumnIndex(KEY_LIST_VIEW_TABLE_TOPIC)));
            detailListViewItem.setIco(cursor.getString(cursor.getColumnIndex(KEY_LIST_VIEW_TABLE_ICON)));
            detailListViewItem.setInfo(cursor.getString(cursor.getColumnIndex(KEY_LIST_VIEW_TABLE_INFO)));
            detailListViewItem.setDate(cursor.getString(cursor.getColumnIndex(KEY_LIST_VIEW_TABLE_DATE)));
            listViewItemList.add(detailListViewItem);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return listViewItemList;
    }

    private void copyDatabase() throws IOException {
        final InputStream inputStream = context.getAssets().open(DB_NAME);

        final OutputStream outputStream = new FileOutputStream(DB_PATH);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    private boolean databaseExists() {
        File file = context.getApplicationContext().getDatabasePath(DB_NAME);
        return file.exists();
    }

    private void openDatabase() throws SQLException {
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
            return;
        }
        sqLiteDatabase = SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READONLY);
    }

    private void closeDatabase() {
        if (sqLiteDatabase != null) {
            sqLiteDatabase.close();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}