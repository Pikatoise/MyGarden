package com.example.mygarden

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.mygarden.databinding.FragmentBedsBinding

class BedsFragment : Fragment() {
	private lateinit var binding: FragmentBedsBinding
	private lateinit var dbBeds: BedRepository
	private lateinit var dbPlants: PlantRepository

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		binding = FragmentBedsBinding.inflate(inflater,container,false)

		dbBeds = BedRepository(this.requireContext())
		dbPlants = PlantRepository(this.requireContext())

		binding.rlAddBed.setOnClickListener {
			startActivity(Intent(this.requireContext(),AddBedActivity::class.java))
		}

		return binding.root
	}

	fun loadBeds(){
		binding.rvBeds.adapter = null

		var dataset: ArrayList<BedsRecyclerData> = arrayListOf()
		var beds = dbBeds.getAllBeds()
		var plants = dbPlants.getAllPlant()

		beds.forEach {
			dataset.add(BedsRecyclerData(it,plants.count { p -> p.bedId == it.id }))
		}

		val bedsAdapter = BedsRecyclerAdapter(dataset) {
			// Открытие активити грядки
		}

		binding.rvBeds.layoutManager = GridLayoutManager(this.requireContext(),2, RecyclerView.VERTICAL ,false)
		binding.rvBeds.adapter = bedsAdapter
	}

	override fun onResume() {
		super.onResume()

		loadBeds()
	}
}