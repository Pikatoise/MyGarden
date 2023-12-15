package com.example.mygarden

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
	private lateinit var bdBeds: BedRepository
	private lateinit var bdPlants: PlantRepository

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		binding = FragmentBedsBinding.inflate(inflater,container,false)

		bdBeds = BedRepository(this.requireContext())
		bdPlants = PlantRepository(this.requireContext())

		loadBeds()

		return binding.root
	}

	fun loadBeds(){
		binding.rvBeds.adapter = null

		var dataset: ArrayList<BedsRecyclerData> = arrayListOf()
		var beds = bdBeds.getAllBeds()
		var plants = bdPlants.getAllPlant()

		beds.forEach {

			dataset.add(BedsRecyclerData(it,plants.count { p -> p.bedId == it.id }))
		}

			Toast.makeText(this.requireContext(),"${dataset.size}",Toast.LENGTH_SHORT).show()
		val bedsAdapter = BedsRecyclerAdapter(dataset)

		binding.rvBeds.layoutManager = GridLayoutManager(this.requireContext(),2, RecyclerView.VERTICAL ,false)
		binding.rvBeds.adapter = bedsAdapter
	}
}