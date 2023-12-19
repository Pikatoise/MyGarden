package com.example.mygarden

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mygarden.databinding.ActivityAddHarvestBinding
import com.example.mygarden.databinding.ActivityHarvestBinding
import java.time.LocalDate

class AddHarvestActivity : AppCompatActivity() {
	private lateinit var binding: ActivityAddHarvestBinding
	private lateinit var dbHarvests: HarvestRepository
	private var plantId: Int = -1

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityAddHarvestBinding.inflate(layoutInflater)
		dbHarvests = HarvestRepository(this)


		binding.ivClose.setOnClickListener {
			finish()
		}

		binding.rlAddHarvestSave.setOnClickListener {
			save()
		}

		val extra = intent.extras

		if (extra != null) {
			plantId = extra.getInt("plantId")
		}

		setContentView(binding.root)
	}

	private fun save(){
		val amount = binding.etAddHarvestAmount.text.toString().toInt()

		val day = binding.dpAddHarvestDate.dayOfMonth
		val month = binding.dpAddHarvestDate.month + 1
		val year = binding.dpAddHarvestDate.year

		val date: Int = LocalDate.of(year,month,day).toEpochDay().toInt()

		val newHarvest = Harvest(-1,plantId,amount,date)

		dbHarvests.addHarvest(newHarvest)

		finish()
	}
}