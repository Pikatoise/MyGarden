package com.example.mygarden

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

data class BedsRecyclerData(val bed: Bed, val plantsCount: Int)

class BedsRecyclerAdapter(private val dataSet: ArrayList<BedsRecyclerData>, private val callbackClick: (id: Int) -> Unit) :
	RecyclerView.Adapter<BedsRecyclerAdapter.ViewHolder>() {

	class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
		val tvName: TextView
		val tvCount: TextView
		val cvContent: CardView

		init {
			tvName = view.findViewById(R.id.tv_item_bed_name)
			tvCount = view.findViewById(R.id.tv_item_bed_plant_count)
			cvContent = view.findViewById(R.id.cv_item_bed_content)
		}
	}

	override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
		val view = LayoutInflater.from(viewGroup.context)
			.inflate(R.layout.item_recycler_bed, viewGroup, false)

		return ViewHolder(view)
	}

	override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

		viewHolder.tvName.text = dataSet[position].bed.name
		viewHolder.tvCount.text = "Растения: ${dataSet[position].plantsCount}"

		viewHolder.cvContent.setOnClickListener {
			callbackClick(dataSet[position].bed.id)
		}
	}

	override fun getItemCount() = dataSet.size

}