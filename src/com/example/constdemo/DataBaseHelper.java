package com.example.constdemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * This class is basically a hack that lets us load a premade sqlite
 * database into the the application. It came from this site.
 * http://www.reigndesign.com/blog/using-your-own-sqlite-database-in-android-applications/
 * @author Juan-Manuel Flux
 * @modifications Nick Wilson
 *
 */
public class DataBaseHelper extends SQLiteOpenHelper{
	//The Android's default system path of your application database.
	//private static String DB_PATH = "/data/data/packagename/databases/";
	private static String DB_PATH; // = context.getApplicationInfo().dataDir + "/databases/";
	//Data Base Name.
	private static final String DATABASE_NAME = "constellationdb.sqlite";
	//Data Base Version.
	private static final int DATABASE_VERSION = 1;
	//Table Names of Data Base.
	static final String TABLE_Name1 = "tablename1";
	static final String TABLE_Name2 = "tablename2";
	static final String TABLE_Name3 = "tablename3";
	static final String TABLE_Name4 = "tablename4";
	public Context context;
	static SQLiteDatabase sqliteDataBase;
	
	public DataBaseHelper(Context context) {
		super(context, DATABASE_NAME, null ,DATABASE_VERSION);
		this.context = context;
		DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
	}
	
	@Override
	public void onCreate(SQLiteDatabase arg0) {
	}
	
	public SQLiteDatabase getDB() {
		return sqliteDataBase;
	}
	//check if the database exists
	public void createDataBase() throws IOException{
			boolean databaseExist = checkDataBase();
			databaseExist = false;
			if(databaseExist){
				//Do Nothing.
			}else{
				this.getWritableDatabase();
				try {
					copyDataBase();
				} catch(IOException e) {
				}
			}
				//TODO Auto-generated catch block
			
	}// end if else dbExist

	//end createDataBase().
	public boolean checkDataBase(){
		File databaseFile = new File(DB_PATH + DATABASE_NAME);
		return databaseFile.exists();
	}
	
	private void copyDataBase() throws IOException{
		File database = context.getApplicationContext().getDatabasePath("databasename.db");

		if (!database.exists()) {
		    // Database does not exist so copy it from assets here
		    Log.i("Database", "Not Found");
		} else {
		    Log.i("Database", "Found");
		}
		//Open your local db as the input stream
		InputStream myInput = context.getAssets().open(DATABASE_NAME);
		//Path to the just created empty db
		String outFileName = DB_PATH + DATABASE_NAME;
		//Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);
		//transfer bytes from the input file to the output file
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer))>0){
			myOutput.write(buffer, 0, length);
		}
		//Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();
	}

	/**
	 * This method opens the data base connection.
	 * First it create the path up till data base of the device.
	 * Then create connection with data base.
	 */
	public void openDataBase() throws SQLException{
		//Open the database
		try {
			createDataBase();
		} catch (IOException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
		String myPath = DB_PATH + DATABASE_NAME;
		sqliteDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
	}
	
	/**
	 * This Method is used to close the data base connection.
	 */
	@Override
	public synchronized void close() {
		if(sqliteDataBase != null)
			sqliteDataBase.close();
		super.close();
	}

	//declare methods to fetch data
	public Cursor getBasicCategoryDetails(){
		return sqliteDataBase.rawQuery( "write the query", null);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//TODO Auto-generated method stub
	}
}