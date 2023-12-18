package com.example.mygarden

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

data class PlantsRecyclerData(val plant: Plant, val bedName: String)

class PlantsRecyclerAdapter(
	private val dataSet: ArrayList<PlantsRecyclerData>,
	private val callbackDelete: (id: Int) -> Unit,
	private val callbackHarvest: (id: Int) -> Unit,
	private val callbackClick: (id: Int) -> Unit) :
	RecyclerView.Adapter<PlantsRecyclerAdapter.ViewHolder>() {

	class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
		val tvName: TextView
		val tvBedName: TextView
		val rlDelete: RelativeLayout
		val rlHarvest: RelativeLayout
		val clTextContent: ConstraintLayout

		init {
			tvName = view.findViewById(R.id.tv_item_plant_name)
			tvBedName = view.findViewById(R.id.tv_item_plant_bed_name)
			rlDelete = view.findViewById(R.id.rl_item_plant_delete)
			rlHarvest = view.findViewById(R.id.rl_item_plant_harvest)
			clTextContent = view.findViewById(R.id.cl_item_plant_text_content)
		}
	}

	override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
		val view = LayoutInflater.from(viewGroup.context)
			.inflate(R.layout.item_recycler_plant, viewGroup, false)

		return ViewHolder(view)
	}

	override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
		viewHolder.tvName.text = dataSet[position].plant.name
		viewHolder.tvBedName.text = dataSet[position].bedName

		viewHolder.rlDelete.setOnClickListener {
			callbackDelete(dataSet[position].plant.id)
		}

		viewHolder.rlHarvest.setOnClickListener {
			callbackHarvest(dataSet[position].plant.id)
		}

		viewHolder.clTextContent.setOnClickListener {
			callbackClick(dataSet[position].plant.id)
		}
	}

	override fun getItemCount() = dataSet.size

}