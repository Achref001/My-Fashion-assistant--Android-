package com.andy.myfashionassistant.sqlLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DBHelperCalander extends SQLiteOpenHelper {
	public static final String TABLE_ASSISTANCE = "assistance";

	public static final String ID_ASSISTANCE = "id";
	public static final String LOGO_COAT = "logoCoat";
	public static final String LOGO_UPPER_BODY = "logoUpperBudy";
	public static final String LOGO_LOWER_BODY = "logoLowerBody";
	public static final String LOGO_SHOES = "logoShoes";
	public static final String LOGO_ACCESSORIES = "logoAccessories";
	public static final String LOGO_DRESS = "logoDress";
	public static final String ASSISTANCE_DATE = "assistanceDate";

	private static final String CREATE_ASSISTANCE = "CREATE TABLE "
			+ TABLE_ASSISTANCE + " (" + ID_ASSISTANCE + " INTEGER, "
			+ LOGO_COAT + " BLOB, " + LOGO_UPPER_BODY + " BLOB, "
			+ LOGO_LOWER_BODY + " BLOB, " + LOGO_SHOES + " BLOB, "
			+ LOGO_ACCESSORIES + " BLOB, "+ LOGO_DRESS + " BLOB, " + ASSISTANCE_DATE + " TEXT);";

	public DBHelperCalander(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Create Data Base
		db.execSQL(CREATE_ASSISTANCE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int old, int newVersion) {

		// TODO Re-Create Data Base
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASSISTANCE);
		onCreate(db);
	}

}
