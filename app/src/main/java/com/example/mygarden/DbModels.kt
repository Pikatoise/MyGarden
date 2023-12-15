package com.example.mygarden

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import java.time.LocalDate

data class Bed(var id: Int, var name: String): Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readInt(),
		parcel.readString() as String
	)

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeInt(id)
		parcel.writeString(name)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Bed> {
		override fun createFromParcel(parcel: Parcel): Bed {
			return Bed(parcel)
		}

		override fun newArray(size: Int): Array<Bed?> {
			return arrayOfNulls(size)
		}
	}
}

data class Plant(var id: Int, var name: String, var age: Int, var sort: String, var planted: Int, var bedId: Int): Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readInt(),
		parcel.readString() as String,
		parcel.readInt(),
		parcel.readString() as String,
		parcel.readInt(),
		parcel.readInt()
	)

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeInt(id)
		parcel.writeString(name)
		parcel.writeInt(age)
		parcel.writeString(sort)
		parcel.writeInt(planted)
		parcel.writeInt(bedId)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Plant> {
		override fun createFromParcel(parcel: Parcel): Plant {
			return Plant(parcel)
		}

		override fun newArray(size: Int): Array<Plant?> {
			return arrayOfNulls(size)
		}
	}
}

data class Harvest(var id: Int, var plantId: Int, var amount: Int, var date: Int): Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readInt(),
		parcel.readInt(),
		parcel.readInt(),
		parcel.readInt()
	)

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeInt(id)
		parcel.writeInt(plantId)
		parcel.writeInt(amount)
		parcel.writeInt(date)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Harvest> {
		override fun createFromParcel(parcel: Parcel): Harvest {
			return Harvest(parcel)
		}

		override fun newArray(size: Int): Array<Harvest?> {
			return arrayOfNulls(size)
		}
	}
}