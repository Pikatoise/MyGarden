package com.example.mygarden

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.mygarden.databinding.ActivityHarvestBinding

class HarvestActivity : AppCompatActivity() {
	private lateinit var binding: ActivityHarvestBinding
	private lateinit var dbHarvests: HarvestRepository
	private lateinit var dbPlants: PlantRepository
	private lateinit var dbBeds: BedRepository
	private var plantId = -1
	private var bedId = -1

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityHarvestBinding.inflate(layoutInflater)

		dbHarvests = HarvestRepository(this)
		dbPlants = PlantRepository(this)
		dbBeds = BedRepository(this)

		binding.ivClose.setOnClickListener {
			finish()
		}

		binding.rlAddHarvest.setOnClickListener {
			val intent = Intent(this,AddHarvestActivity::class.java)

			intent.putExtra("plantId",plantId)

			startActivity(intent)
		}

		val extra = intent.extras

		if (extra != null) {
			plantId = extra.getInt("plantId")
			bedId = extra.getInt("bedId")

			if (bedId != -1)
				binding.rlAddHarvest.visibility = View.INVISIBLE
			else
				binding.rlAddHarvest.visibility = View.VISIBLE
		}

		setContentView(binding.root)
	}

	private fun loadHarvestsByPlant(){
		binding.rvHarvests.adapter = null

		val dataset: ArrayList<HarvestsRecyclerData> = arrayListOf()
		val plant = dbPlants.getPlantById(plantId)
		val harvests = dbHarvests.getHarvestsByPlant(plantId)

		harvests.forEach {
			dataset.add(HarvestsRecyclerData(plant.name,it))
		}

		val harvestsAdapter = HarvestsRecyclerAdapter(dataset){
			val dialogRemove = DialogRemoveItem( "Удалить урожай?") {
				dbHarvests.removeHarvest(it)

				loadHarvestsByPlant()
			}
			dialogRemove.show(supportFragmentManager,"removeDialog")
		}

		binding.rvHarvests.adapter = harvestsAdapter
	}

	private fun loadHarvestsByBed(){
		binding.rvHarvests.adapter = null

		val dataset: ArrayList<HarvestsRecyclerData> = arrayListOf()
		val plants = dbPlants.getPlantsByBed(bedId)

		plants.forEach { plant ->
			val harvests = dbHarvests.getHarvestsByPlant(plant.id)

			harvests.forEach { harvest ->
				dataset.add(HarvestsRecyclerData(plant.name,harvest))
			}
		}

		val harvestsAdapter = HarvestsRecyclerAdapter(dataset){
			val dialogRemove = DialogRemoveItem( "Удалить урожай?") {
				dbHarvests.removeHarvest(it)

				loadHarvestsByBed()
			}
			dialogRemove.show(supportFragmentManager,"removeDialog")
		}

		binding.rvHarvests.adapter = harvestsAdapter
	}

	override fun onResume() {
		super.onResume()

		if (plantId != -1){
			loadHarvestsByPlant()
		}
		else
			loadHarvestsByBed()
	}
}