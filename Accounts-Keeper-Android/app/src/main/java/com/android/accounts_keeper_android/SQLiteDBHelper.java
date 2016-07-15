package com.android.accounts_keeper_android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Save recent 1 months accounts
 */
public class SQLiteDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "Accounts.db";
    private static final String TABLE_NAME = "Accounts";
    private static final int DB_VERSION = 1;

    private static final String FOOD = "_food",
                                FRUITS = "_fruits",
                                NECESSARIES = "_necessaries",
                                SCHOOLTHINGS = "_schoolThings",
                                NOTE = "_note",
                                DATE = "_date",
                                USER = "_user";

    public SQLiteDBHelper(Context ctx) {
        super(ctx, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "create table " + TABLE_NAME + "(" +
                "_id integer primary key autoincrement, " +
                USER + " text not null, " +
                DATE + " text not null unique, " +
                FOOD + " real default 0, "  +
                FRUITS + " real default 0, " +
                NECESSARIES + " real default 0, " +
                SCHOOLTHINGS + " real default 0, " +
                NOTE + " text default '');";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXITS " + TABLE_NAME);
        onCreate(db);
    }

    // When this date's data is empty
    public void insertEmpty(String user, String date) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER, user);
        values.put(DATE, date);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    // Update data, data like money can only be update...
    public void updateAccordingZh(String which, String user, String date, String value) {
        SQLiteDatabase db = getReadableDatabase();
        String where = USER + " = ? and " + DATE + " = ?";
        String[] whereArgs = new String[]{user, date};
        ContentValues contentValues = new ContentValues();

        switch (which) {
            case "备忘":
                contentValues.put(NOTE, value);
                break;
            case "食物":
                contentValues.put(FOOD, queryWithColumn(user, date, FOOD) + Double.parseDouble(value));
                break;
            case "水果":
                contentValues.put(FRUITS, queryWithColumn(user, date, FRUITS) + Double.parseDouble(value));
                break;
            case "生活用品":
                contentValues.put(NECESSARIES, queryWithColumn(user, date, NECESSARIES) + Double.parseDouble(value));
                break;
            case "学习用品":
                contentValues.put(SCHOOLTHINGS, queryWithColumn(user, date, SCHOOLTHINGS) + Double.parseDouble(value));
                break;
            default:
                break;
        }

        db.update(TABLE_NAME, contentValues, where, whereArgs);

        db.close();
    }

    public double queryWithColumn(String user, String date, String columns) {
        SQLiteDatabase db = getReadableDatabase();
        String where = USER + " = ? and " + DATE + " = ?";
        String[] whereArgs = new String[]{user, date};

        Cursor cursor = db.query(TABLE_NAME,
                new String[]{columns},
                where,
                whereArgs,
                null, null, null);

        cursor.moveToFirst();
        return cursor.getDouble(0);
    }

    public Cursor queryAccordingDate(String user, String date) {
        SQLiteDatabase db = getReadableDatabase();
        String where = USER + " = ? and " + DATE + " = ?";
        String[] whereArgs = new String[]{user, date};

        Cursor cursor = db.query(TABLE_NAME,
                        new String[]{FOOD, FRUITS, NECESSARIES, SCHOOLTHINGS, NOTE}, // Columns
                        where,                                // Selection
                        whereArgs,
                        null, null, null);

        if (!cursor.moveToFirst()) { // Insert Empty Account when no data.
            insertEmpty(user, date);

            db = getReadableDatabase();
            cursor = db.query(TABLE_NAME,
                    new String[]{FOOD, FRUITS, NECESSARIES, SCHOOLTHINGS, NOTE}, // Columns
                    where,                                  // Selection
                    whereArgs,
                    null, null, null);
        }

        return cursor;
    }

    public List<AccountsItem> getAdapterSource(Cursor cursor) {
        List<AccountsItem> list = new ArrayList<>();
        cursor.moveToFirst();

        list.add(new AccountsItem(R.mipmap.ic_food, "食物", cursor.getDouble(cursor.getColumnIndex(FOOD))));
        list.add(new AccountsItem(R.mipmap.ic_fruits, "水果", cursor.getDouble(cursor.getColumnIndex(FRUITS))));
        list.add(new AccountsItem(R.mipmap.ic_necessary, "生活用品", cursor.getDouble(cursor.getColumnIndex(NECESSARIES))));
        list.add(new AccountsItem(R.mipmap.ic_schoolthings, "学习用品", cursor.getDouble(cursor.getColumnIndex(SCHOOLTHINGS))));
        list.add(new AccountsItem(R.mipmap.ic_note, "备忘", cursor.getString(cursor.getColumnIndex(NOTE))));

        return list;
    }
}
