package com.example.mygarden

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate

data class HarvestsRecyclerData(val plantName: String, val harvest: Harvest)

class HarvestsRecyclerAdapter(private val dataSet: ArrayList<HarvestsRecyclerData>, private val callbackDelete: (id: Int) -> Unit) :
	RecyclerView.Adapter<HarvestsRecyclerAdapter.ViewHolder>() {

	class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
		val tvPlantName: TextView
		val tvDate: TextView
		val tvAmount: TextView
		val rvDelete: RelativeLayout

		init {
			tvPlantName = view.findViewById(R.id.tv_item_harvest_plant_name)
			tvDate = view.findViewById(R.id.tv_item_harvest_date)
			tvAmount = view.findViewById(R.id.tv_item_harvest_amount)
			rvDelete = view.findViewById(R.id.rl_item_harvest_delete)
		}
	}

	override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
		val view = LayoutInflater.from(viewGroup.context)
			.inflate(R.layout.item_recycler_harvest, viewGroup, false)

		return ViewHolder(view)
	}

	override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

		viewHolder.tvPlantName.text = dataSet[position].plantName
		viewHolder.tvAmount.text = "${dataSet[position].harvest.amount.toString()} г."

		var date = LocalDate.ofEpochDay(dataSet[position].harvest.date.toLong())

		viewHolder.tvDate.text = date.toString()

		viewHolder.rvDelete.setOnClickListener {
			callbackDelete(dataSet[position].harvest.id)
		}
	}

	override fun getItemCount() = dataSet.size

}