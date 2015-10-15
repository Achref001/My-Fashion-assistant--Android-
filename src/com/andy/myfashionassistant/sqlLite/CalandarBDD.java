package com.andy.myfashionassistant.sqlLite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.andy.myfashionassistant.entities.Assistance;

public class CalandarBDD {

	private static final int VERSION_BDD = 2;
	private static final String NAME_BDD = "vetements.db";

	private SQLiteDatabase bdd;

	private DBHelperCalander dbHelperCalandar;

	public CalandarBDD(Context context) {
		super();
		dbHelperCalandar = new DBHelperCalander(context, NAME_BDD, null, VERSION_BDD);
	}

	public void open() {
		// TODO Open Data Base
		bdd = dbHelperCalandar.getWritableDatabase();
	}

	public void close() {
		// TODO Close Data Base
		dbHelperCalandar.close();
	}

	public SQLiteDatabase getBDD() {
		return bdd;
	}

	public long insertTop(Assistance partis) {
		Cursor cursor = bdd.rawQuery("select max("
				+ DBHelperCalander.ID_ASSISTANCE + ") from "
				+ DBHelperCalander.TABLE_ASSISTANCE, null);
		if (cursor.moveToFirst())
			partis.setId(cursor.getInt(0) + 1);
		else
			partis.setId(1);
		Log.i("TEST", partis.getId() + "");
		ContentValues values = new ContentValues();
		values.put(DBHelperCalander.ID_ASSISTANCE, partis.getId());
		values.put(DBHelperCalander.LOGO_COAT, partis.getLogoCoat());
		values.put(DBHelperCalander.LOGO_UPPER_BODY, partis.getLogoUpperBody());
		values.put(DBHelperCalander.LOGO_LOWER_BODY, partis.getLogoLowerBody());
		values.put(DBHelperCalander.LOGO_SHOES, partis.getLogoShoes());
		values.put(DBHelperCalander.LOGO_ACCESSORIES,
				partis.getLogoAccessories());
		values.put(DBHelperCalander.ASSISTANCE_DATE, partis.getDataAssistance());
		bdd.insert(DBHelperCalander.TABLE_ASSISTANCE, null, values);

		return 0;
	}

	public int removeAllVetements() {
		// TODO Remove all Table
		bdd.execSQL("DELETE FROM " + DBHelperCalander.TABLE_ASSISTANCE);
		return 0;
	}

	public int removeVetement(int index) {
		// TODO Remove one Article
		bdd.delete(DBHelperCalander.TABLE_ASSISTANCE,
				DBHelperCalander.ID_ASSISTANCE + " = ?", new String[] { index
						+ "" });
		return 0;
	}

	public List<Assistance> selectAll() {
		List<Assistance> list = new ArrayList<Assistance>();
		// TODO Get list of Article
		Cursor cursor = bdd.query(DBHelperCalander.TABLE_ASSISTANCE,
				new String[] { DBHelperCalander.ID_ASSISTANCE,
						DBHelperCalander.LOGO_COAT,
						DBHelperCalander.LOGO_UPPER_BODY,
						DBHelperCalander.LOGO_LOWER_BODY,
						DBHelperCalander.LOGO_SHOES,
						DBHelperCalander.LOGO_ACCESSORIES,
						DBHelperCalander.LOGO_DRESS,
						DBHelperCalander.ASSISTANCE_DATE}, null, null, null,null,null,
				null);

		if (cursor.moveToFirst()) {
			do {
				Assistance assistance = new Assistance(cursor.getInt(0),
						cursor.getBlob(1), cursor.getBlob(2),
						cursor.getBlob(3), cursor.getBlob(4),
						cursor.getBlob(5), cursor.getBlob(6),
						cursor.getString(7));
				list.add(assistance);
			} while (cursor.moveToNext());
		}
		return list;
	}

	public Assistance getDateAssistance(String date) {

		Cursor cursor = bdd.query(DBHelperCalander.TABLE_ASSISTANCE,
				new String[] { DBHelperCalander.LOGO_COAT,
						DBHelperCalander.LOGO_UPPER_BODY,
						DBHelperCalander.LOGO_LOWER_BODY,
						DBHelperCalander.LOGO_SHOES,
						DBHelperCalander.LOGO_ACCESSORIES,
						DBHelperCalander.LOGO_DRESS
						},
				DBHelperCalander.ASSISTANCE_DATE + "=?", new String[] { date },
				null, null, null, null);

		Assistance assistance = null;
		if (cursor.moveToFirst()) {

			assistance = new Assistance(cursor.getBlob(1), cursor.getBlob(2),
					cursor.getBlob(3), cursor.getBlob(4), cursor.getBlob(5),
					cursor.getBlob(6));

		}
		return assistance;
	}
}
