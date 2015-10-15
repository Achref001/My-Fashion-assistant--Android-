package com.andy.myfashionassistant.sqlLite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.andy.myfashionassistant.entities.Vetement;



public class VetementBDD {

	private static final int VERSION_BDD = 2;
	private static final String NAME_BDD = "vetement.db";

	private SQLiteDatabase bdd;

	private DBHelper dbHelper;

	public VetementBDD(Context context) {
		super();
		dbHelper = new DBHelper(context, NAME_BDD, null, VERSION_BDD);
	}

	public void open() {
		// TODO Open Data Base
		bdd = dbHelper.getWritableDatabase();
	}

	public void close() {
		// TODO Close Data Base
		dbHelper.close();
	}

	public SQLiteDatabase getBDD() {
		return bdd;
	}

	public long insertTop(Vetement partis) {
		Cursor cursor = bdd.rawQuery("select max(" + DBHelper.ID_VETEMENT
				+ ") from " + DBHelper.TABLE_VETEMENT, null);
		if (cursor.moveToFirst())
			partis.setId(cursor.getInt(0) + 1);
		else
			partis.setId(1);
		Log.i("TEST",partis.getId()+"");
		// TODO Add Article to data base
		ContentValues values = new ContentValues();
		values.put(DBHelper.ID_VETEMENT, partis.getId());
		values.put(DBHelper.TYPE_VETEMENT, partis.getType());
		values.put(DBHelper.TITLE_VETEMENT, partis.getTitle());
		values.put(DBHelper.LOGO_VETEMENT, partis.getLogo());
		bdd.insert(DBHelper.TABLE_VETEMENT, null, values);

		return 0;
	}

	public int removeAllVetements() {
		// TODO Remove all Table
		bdd.execSQL("DELETE FROM " + DBHelper.TABLE_VETEMENT);
		return 0;
	}

	public int removeVetement(int index) {
		// TODO Remove one Article
		bdd.delete(DBHelper.TABLE_VETEMENT, DBHelper.ID_VETEMENT + " = ?",
				new String[] { index + "" });
		return 0;
	}

	public List<Vetement> selectAll() {
		List<Vetement> list = new ArrayList<Vetement>();
		// TODO Get list of Article
		Cursor cursor = bdd.query(DBHelper.TABLE_VETEMENT, new String[] {
				DBHelper.ID_VETEMENT, DBHelper.TYPE_VETEMENT,
				DBHelper.TITLE_VETEMENT, DBHelper.LOGO_VETEMENT }, null, null,
				null, null,null);

		if (cursor.moveToFirst()) {
			do {
				Vetement vetement = new Vetement(cursor.getInt(0), cursor.getString(1),
						cursor.getString(2), cursor.getBlob(3));
				list.add(vetement);
			} while (cursor.moveToNext());
		}
		return list;
	}
}
