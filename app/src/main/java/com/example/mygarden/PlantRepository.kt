package com.example.mygarden

import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import java.io.IOException

class PlantRepository(context: Context) {
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

	public fun getFirstPlant(): Plant {
		var plant: Plant = Plant(-1,"",-1,"",-1,-1)

		var cursor = mDb.rawQuery(
			"SELECT * FROM [Plants]",
			null)

		if (cursor != null && cursor.count > 0){
			cursor.moveToFirst()

			plant.id = cursor.getInt(0)
			plant.name = cursor.getString(1)
			plant.age = cursor.getInt(2)
			plant.sort = cursor.getString(3)
			plant.planted = cursor.getInt(4)
			plant.bedId = cursor.getInt(5)
		}

		cursor.close()

		return plant
	}

	public fun getAllPlant(): ArrayList<Plant> {
		var plants: ArrayList<Plant> = arrayListOf()

		var cursor = mDb.rawQuery(
			"SELECT * FROM [Plants]",
			null)

		if (cursor != null && cursor.count > 0){
			cursor.moveToFirst()

			while (!cursor.isAfterLast) {
				var id = cursor.getInt(0)
				var name = cursor.getString(1)
				var age = cursor.getInt(2)
				var sort = cursor.getString(3)
				var planted = cursor.getInt(4)
				var bedId = cursor.getInt(5)

				plants.add(Plant(id,name,age,sort,planted,bedId))

				cursor.moveToNext()
			}
		}
		cursor.close()

		return plants;
	}

	public fun getPlantsByBed(bedId: Int): ArrayList<Plant>{
		var plants: ArrayList<Plant> = arrayListOf()

		var cursor = mDb.rawQuery(
			"SELECT * FROM [Plants] WHERE BedId = $bedId",
			null)

		if (cursor != null && cursor.count > 0){
			cursor.moveToFirst()

			while (!cursor.isAfterLast) {
				var id = cursor.getInt(0)
				var name = cursor.getString(1)
				var age = cursor.getInt(2)
				var sort = cursor.getString(3)
				var planted = cursor.getInt(4)
				var bedId = cursor.getInt(5)

				plants.add(Plant(id,name,age,sort,planted,bedId))

				cursor.moveToNext()
			}
		}
		cursor.close()

		return plants;
	}

	public fun removePlant(id: Int){
		mDb.execSQL("DELETE FROM [Plants] WHERE Id=${id}")
	}

	public fun addPlant(plant: Plant){
		mDb.execSQL(
			"INSERT INTO [Plants](Name,Age,Sort,Planted,BedId) VALUES ('${plant.name}',${plant.age},'${plant.sort}',${plant.planted},${plant.bedId})")
	}

	public fun updatePlantBed(plantId: Int, bedId: Int){
		mDb.execSQL("UPDATE [Plants] SET BedId = $bedId WHERE Id = $plantId")
	}
}