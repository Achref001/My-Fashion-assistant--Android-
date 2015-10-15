package com.andy.myfashionassistant.sqlLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public static final String TABLE_VETEMENT = "vetement";

	public static final String ID_VETEMENT = "id";
	public static final String TITLE_VETEMENT = "title";
	public static final String TYPE_VETEMENT = "type";
	public static final String LOGO_VETEMENT = "logo";

	private static final String CREATE_VETEMENT = "CREATE TABLE "
			+ TABLE_VETEMENT + " (" + ID_VETEMENT + " INTEGER, " + TITLE_VETEMENT
			+ " TEXT, " + TYPE_VETEMENT + " TEXT, " + LOGO_VETEMENT + " BLOB);";

	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Create Data Base
		db.execSQL(CREATE_VETEMENT);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int old, int newVersion) {

		// TODO Re-Create Data Base
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_VETEMENT);
		onCreate(db);
	}

}
