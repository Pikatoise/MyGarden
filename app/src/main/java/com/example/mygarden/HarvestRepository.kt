package com.example.mygarden

import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import java.io.IOException

class HarvestRepository(context: Context) {
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

	public fun getFirstHarvest(): Harvest {
		var harvest: Harvest = Harvest(-1,-1,-1,-1)

		var cursor = mDb.rawQuery(
			"SELECT * FROM [Harvests]",
			null)

		if (cursor != null && cursor.count > 0){
			cursor.moveToFirst()

			harvest.id = cursor.getInt(0)
			harvest.plantId = cursor.getInt(1)
			harvest.amount = cursor.getInt(2)
			harvest.date = cursor.getInt(3)
		}

		cursor.close()

		return harvest
	}

	public fun getHarvestsByPlant(plantId: Int): ArrayList<Harvest>{
		var harvests: ArrayList<Harvest> = arrayListOf()

		var cursor = mDb.rawQuery(
			"SELECT * FROM [Harvests] WHERE PlantId = $plantId",
			null)

		if (cursor != null && cursor.count > 0){
			cursor.moveToFirst()

			while (!cursor.isAfterLast) {
				var id = cursor.getInt(0)
				var plantId = cursor.getInt(1)
				var amount = cursor.getInt(2)
				var date = cursor.getInt(3)

				harvests.add(Harvest(id,plantId,amount,date))

				cursor.moveToNext()
			}
		}
		cursor.close()

		return harvests;
	}

	public fun addHarvest(harvest: Harvest){
		mDb.execSQL(
			"INSERT INTO [Harvests](PlantId,Amount,Date) VALUES (${harvest.plantId},${harvest.amount},${harvest.date})")
	}

	public fun removeHarvest(id: Int){
		mDb.execSQL("DELETE FROM [Harvests] WHERE Id=${id}")
	}
}