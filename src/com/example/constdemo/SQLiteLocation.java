package com.example.constdemo;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteLocation extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "LocationDB";

    public SQLiteLocation(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_LOCATION_TABLE = "CREATE TABLE location ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, "+
                "author TEXT )";

        // create books table
        db.execSQL(CREATE_LOCATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS location");

        // create fresh constellation table
        this.onCreate(db);
    }



    /**
     * CRUD operations (create "add", read "get", update, delete) book + get all books + delete all books
     */

    //Constellations table name
    private static final String TABLE_LOCATION = "location";

    // Constellation Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_MIN_LAT = "min_lat";
    private static final String KEY_MAX_LAT = "max_lat";
    private static final String KEY_CONSTELLATIONS = "constellations";

    private static final String[] COLUMNS = {KEY_ID, KEY_MIN_LAT, KEY_MAX_LAT,
        KEY_CONSTELLATIONS};

    public void addLocations(Location location){
        //Log.d("addLocation", constellation.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_MIN_LAT, location.getMinLat());
        values.put(KEY_MAX_LAT, location.getMaxLat());
        // TODO: Need to find other way to add arraylist/BLOB to db
        //values.put(KEY_CONSTELLATIONS, location.getConstellations());

        // 3. insert
        db.insert(TABLE_LOCATION, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }


    public Location getLocation(int id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_LOCATION, // a. table
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
        Location location = new Location(cursor.getInt(1), cursor.getInt(2),
                null); // TODO: set null to get the BLOB/BYTES
        //location.setId(Integer.parseInt(cursor.getString(0)));

        //Log.d("getBook("+id+")", constellation.toString());

        // 5. return book
        return location;
    }

    // Get All Books
    public List<Location> getAllBooks() {
        List<Location> locations = new LinkedList<Location>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_LOCATION;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        Location location = null;
        if (cursor.moveToFirst()) {
            do {
                location = new Location(cursor.getInt(1), cursor.getInt(2),
                        null); // TODO: make last one arraylist/blob

                // Add location to locations
                locations.add(location);
            } while (cursor.moveToNext());
        }

        // return list of locations
        return locations;
    }

    // Updating single location
    public int updateBook(Location location) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("title", location.getMinLat());
        values.put("author", location.getMaxLat());

        // 3. updating row
        int i = db.update(TABLE_LOCATION, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(location.getId()) }); //selection args

        // 4. close
        db.close();

        return i;

    }

    // Deleting single book
    public void deleteBook(Location location) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_LOCATION,
                KEY_ID+" = ?",
                new String[] { String.valueOf(location.getId()) });

        // 3. close
        db.close();
    }


}
