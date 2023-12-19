package com.example.mygarden

import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mygarden.databinding.ActivityPlantBinding
import java.time.LocalDate
import java.time.LocalTime


class PlantActivity : AppCompatActivity() {
	private lateinit var binding: ActivityPlantBinding
	private lateinit var dbBeds: BedRepository
	private lateinit var dbPlants: PlantRepository
	private var plantId: Int = -1
	private var plantBedId: Int = -1

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityPlantBinding.inflate(layoutInflater)
		dbBeds = BedRepository(this)
		dbPlants = PlantRepository(this)

		binding.ivClose.setOnClickListener {
			finish()
		}

		binding.rlAddPlantSave.setOnClickListener {
			save()
		}

		val extra = intent.extras

		if (extra != null) {
			plantId = extra.getInt("plantId")
		}

		loadBeds()

		if (plantId != -1){
			loadPlant()
			binding.tvPlantTitle.setText("Изменить растение")
		}
		else
			binding.tvPlantTitle.setText("Добавить растение")

		setContentView(binding.root)
	}

	private fun loadBeds(){
		binding.rvAddPlantBeds.adapter = null

		var dataset: ArrayList<BedsRecyclerData> = arrayListOf()
		var beds = dbBeds.getAllBeds()
		var plants = dbPlants.getAllPlant()

		beds.forEach {
			dataset.add(BedsRecyclerData(it,plants.count { p -> p.bedId == it.id }))
		}

		val bedsAdapter = BedsRecyclerAdapter(dataset) {
			plantBedId = it

			val selectedBed = dbBeds.getBedById(it)

			binding.tvAddPlantTitleBedSelected.text = "Выбрано: ${selectedBed.name}"
		}

		binding.rvAddPlantBeds.layoutManager = GridLayoutManager(this,2, RecyclerView.VERTICAL ,false)
		binding.rvAddPlantBeds.adapter = bedsAdapter
	}

	private fun loadPlant(){
		val plant = dbPlants.getPlantById(plantId)

		binding.etAddPlantName.setText(plant.name)
		binding.etAddPlantAge.setText(plant.age.toString())
		binding.etAddPlantSort.setText(plant.sort)

		val planted = LocalDate.ofEpochDay(plant.planted.toLong())
		binding.dpAddPlantPlanted.init(planted.year,planted.monthValue - 1,planted.dayOfMonth) {_,_,_,_ ->}

		plantBedId = plant.bedId
		val selectedBed = dbBeds.getBedById(plant.bedId)
		binding.tvAddPlantTitleBedSelected.text = "Выбрано: ${selectedBed.name}"
	}

	private fun save(){
		if (plantBedId == -1){
			Toast.makeText(this,"Выберите грядку!",Toast.LENGTH_SHORT).show()
			return
		}

		val name = binding.etAddPlantName.text.toString()
		if (name.isNullOrEmpty()){
			Toast.makeText(this,"Введите название!",Toast.LENGTH_SHORT).show()
			return
		}

		val age = binding.etAddPlantAge.text.toString().toInt()

		val sort = binding.etAddPlantSort.text.toString()
		if (sort.isNullOrEmpty()){
			Toast.makeText(this,"Введите сорт!",Toast.LENGTH_SHORT).show()
			return
		}

		val day = binding.dpAddPlantPlanted.dayOfMonth
		val month = binding.dpAddPlantPlanted.month + 1
		val year = binding.dpAddPlantPlanted.year

		val planted: Int = LocalDate.of(year,month,day).toEpochDay().toInt()

		var newPlant = Plant(plantId,name,age,sort,planted,plantBedId)

		if (plantId == -1){
			dbPlants.addPlant(newPlant)
		}
		else{
			dbPlants.updatePlant(newPlant)
		}

		finish()
	}
}