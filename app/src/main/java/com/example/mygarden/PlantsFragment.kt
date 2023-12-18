package com.example.mygarden

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mygarden.databinding.FragmentPlantsBinding

class PlantsFragment : Fragment() {
	private lateinit var binding: FragmentPlantsBinding
	private lateinit var bdPlants: PlantRepository
	private lateinit var bdBeds: BedRepository

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		binding = FragmentPlantsBinding.inflate(inflater,container,false)

		bdPlants = PlantRepository(this.requireContext())
		bdBeds = BedRepository(this.requireContext())

		binding.rlAddPlant.setOnClickListener {
			val intent = Intent(this.requireContext(),PlantActivity::class.java)

			intent.putExtra("plantId",-1)

			startActivity(intent)
		}

		return binding.root
	}

	fun loadPlants(){
		binding.rvPlants.adapter = null

		var dataset: ArrayList<PlantsRecyclerData> = arrayListOf()
		var plants = bdPlants.getAllPlant()
		var beds = bdBeds.getAllBeds()

		plants.forEach {
			dataset.add(PlantsRecyclerData(
				it,
				beds.find { b -> b.id == it.bedId }?.name ?: "Отсутствует"
			))
		}

		val plantsAdapter = PlantsRecyclerAdapter(dataset,
			{
				val dialogRemove = DialogRemoveItem( "Удалить растение?") {
					bdPlants.removePlant(it)

					loadPlants()
				}
				val manager = parentFragmentManager
				dialogRemove.show(manager,"removeDialog")
			},
			{

			},
			{
				val intent = Intent(this.requireContext(),PlantActivity::class.java)

				intent.putExtra("plantId",it)

				startActivity(intent)
			})

		binding.rvPlants.adapter = plantsAdapter
	}

	override fun onResume() {
		super.onResume()

		loadPlants()
	}
}