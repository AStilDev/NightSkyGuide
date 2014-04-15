package com.example.constdemo;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteConstellation extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "ConstellationDB";

    public SQLiteConstellation(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_CONSTELLATION_TABLE = "CREATE TABLE constellation ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, "+
                "author TEXT )";

        // create books table
        db.execSQL(CREATE_CONSTELLATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS constellation");

        // create fresh constellation table
        this.onCreate(db);
    }



    /**
     * CRUD operations (create "add", read "get", update, delete) book + get all books + delete all books
     */

    //Constellations table name
    private static final String TABLE_CONSTELLATION = "constellation";

    // Constellation Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ABBREVIATION = "abbreviation";
    private static final String KEY_SYMBOL = "symbol";
    private static final String KEY_FAMILY = "family";
    private static final String KEY_CLOSEST_STAR = "closest_star";
    private static final String KEY_WIKI_LINK = "wiki_link";

    private static final String[] COLUMNS = {KEY_ID, KEY_NAME, KEY_ABBREVIATION,
            KEY_SYMBOL, KEY_FAMILY, KEY_CLOSEST_STAR,
            KEY_CLOSEST_STAR, KEY_WIKI_LINK};


    public void addConstellations(Constellation constellation){
        Log.d("addConstellation", constellation.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, constellation.getName()); // get title
        values.put(KEY_ABBREVIATION, constellation.getAbbreviation()); // get
        values.put(KEY_SYMBOL, constellation.getSymbol());
        values.put(KEY_FAMILY, constellation.getFamily());
        values.put(KEY_CLOSEST_STAR, constellation.getClosestStar());
        values.put(KEY_WIKI_LINK, constellation.getWikiLink());

        // 3. insert
        db.insert(TABLE_CONSTELLATION, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }


    public Constellation getConstellation(int id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_CONSTELLATION, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build book object
        Constellation constellation = new Constellation();
        constellation.setId(Integer.parseInt(cursor.getString(0)));
        constellation.setName(cursor.getString(1));
        constellation.setAbbreviation(cursor.getString(2));
        constellation.setSymbol(cursor.getString(3));
        constellation.setFamily(cursor.getString(4));
        constellation.setClosestStar(cursor.getString(5));
        constellation.setWikiLink(cursor.getString(6));

        Log.d("getBook("+id+")", constellation.toString());

        // 5. return book
        return constellation;
    }

    // Get All Books
    public List<Constellation> getAllBooks() {
        List<Constellation> consts = new LinkedList<Constellation>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_CONSTELLATION;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        Constellation constellation = null;
        if (cursor.moveToFirst()) {
            do {
                constellation = new Constellation();
                constellation.setId(Integer.parseInt(cursor.getString(0)));
                constellation.setName(cursor.getString(1));
                constellation.setAbbreviation(cursor.getString(2));
                constellation.setSymbol(cursor.getString(3));
                constellation.setFamily(cursor.getString(4));
                constellation.setClosestStar(cursor.getString(5));
                constellation.setWikiLink(cursor.getString(6));

                // Add constellation to consts
                consts.add(constellation);
            } while (cursor.moveToNext());
        }

        Log.d("getAllconsts()", consts.toString());

        // return list of constellations
        return consts;
    }

    // Updating single constellation
    public int updateBook(Constellation constellation) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("title", constellation.getName()); // get name
        values.put("author", constellation.getAbbreviation()); // get abbreviation

        // 3. updating row
        int i = db.update(TABLE_CONSTELLATION, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(constellation.getId()) }); //selection args

        // 4. close
        db.close();

        return i;

    }

    // Deleting single book
    public void deleteBook(Constellation constellation) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_CONSTELLATION,
                KEY_ID+" = ?",
                new String[] { String.valueOf(constellation.getId()) });

        // 3. close
        db.close();

        Log.d("deleteBook", constellation.toString());

    }


}