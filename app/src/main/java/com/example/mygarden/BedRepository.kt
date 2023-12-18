package com.example.mygarden

import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import java.io.IOException

class BedRepository(context: Context) {
	private var mDbHelper: DatabaseHelper
	private var mDb: SQLiteDatabase

	init{
		mDbHelper = DatabaseHelper(context)

		try {
			mDbHelper.updateDataBase()
		} catch (mIOException: IOException) {
			throw Error("UnableToUpdateDatabase")
		}

		mDb = try {
			mDbHelper.writableDatabase
		} catch (mSQLException: SQLException) {
			throw mSQLException
		}
	}

	public fun getFirstBed(): Bed {
		var bed: Bed = Bed(-1,"")

		var cursor = mDb.rawQuery(
			"SELECT * FROM [Beds]",
			null)

		if (cursor != null && cursor.count > 0){
			cursor.moveToFirst()

			bed.id = cursor.getInt(0)
			bed.name = cursor.getString(1)
		}

		cursor.close()

		return bed
	}

	public fun getBedById(id: Int): Bed {
		var bed: Bed = Bed(id,"")

		var cursor = mDb.rawQuery(
			"SELECT * FROM [Beds] WHERE Id = $id",
			null)

		if (cursor != null && cursor.count > 0){
			cursor.moveToFirst()

			bed.id = cursor.getInt(0)
			bed.name = cursor.getString(1)
		}

		cursor.close()

		return bed
	}

	public fun getAllBeds(): ArrayList<Bed> {
		var beds: ArrayList<Bed> = arrayListOf()

		var cursor = mDb.rawQuery(
			"SELECT * FROM [Beds]",
			null)

		if (cursor != null && cursor.count > 0){
			cursor.moveToFirst()

			while (!cursor.isAfterLast) {
				var id = cursor.getInt(0)
				var name = cursor.getString(1)

				beds.add(Bed(id,name))

				cursor.moveToNext()
			}
		}
		cursor.close()

		return beds;
	}

	public fun removeBed(id: Int){
		mDb.execSQL("DELETE FROM [Beds] WHERE Id=${id}")
	}

	public fun addBed(name: String){
		mDb.execSQL(
			"INSERT INTO [Beds](Name) VALUES ('${name}')")
	}
}