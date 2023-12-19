package com.example.mygarden

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mygarden.databinding.ActivityBedBinding

class BedActivity : AppCompatActivity() {
	private lateinit var binding: ActivityBedBinding
	private lateinit var dbPlants: PlantRepository
	private lateinit var dbBeds: BedRepository
	private var bedId = -1

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityBedBinding.inflate(layoutInflater)
		dbPlants = PlantRepository(this)
		dbBeds = BedRepository(this)

		binding.ivClose.setOnClickListener {
			finish()
		}

		val extra = intent.extras

		if (extra != null) {
			bedId = extra.getInt("bedId")
		}

		binding.rlBedHarvests.setOnClickListener {
			val intent = Intent(this,HarvestActivity::class.java)

			intent.putExtra("plantId",-1)
			intent.putExtra("bedId",bedId)

			startActivity(intent)
		}

		binding.rlDeleteBed.setOnClickListener {
			dbBeds.removeBed(bedId)

			finish()
		}

		var bed = dbBeds.getBedById(bedId)
		binding.tvBedName.text = bed.name

		setContentView(binding.root)
	}

	private fun loadPlants(){
		binding.rvBedPlants.adapter = null

		var dataset: ArrayList<PlantsRecyclerData> = arrayListOf()
		var plants = dbPlants.getPlantsByBed(bedId)
		var bed = dbBeds.getBedById(bedId)

		plants.forEach {
			dataset.add(PlantsRecyclerData(it,bed.name))
		}

		val plantsAdapter = PlantsRecyclerAdapter(dataset,
			{
				val dialogRemove = DialogRemoveItem( "Удалить растение?") {
					dbPlants.removePlant(it)

					loadPlants()
				}

				dialogRemove.show(supportFragmentManager,"removeDialog")
			},
			{
				val intent = Intent(this,HarvestActivity::class.java)

				intent.putExtra("plantId",it)
				intent.putExtra("bedId",-1)

				startActivity(intent)
			},
			{
				val intent = Intent(this,PlantActivity::class.java)

				intent.putExtra("plantId",it)

				startActivity(intent)
			})

		binding.rvBedPlants.adapter = plantsAdapter
	}

	override fun onResume() {
		super.onResume()

		loadPlants()
	}
}