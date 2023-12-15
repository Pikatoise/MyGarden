package com.example.mygarden

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class BedsRecyclerData(val bed: Bed, val plantsCount: Int)

class BedsRecyclerAdapter(private val dataSet: ArrayList<BedsRecyclerData>) :
	RecyclerView.Adapter<BedsRecyclerAdapter.ViewHolder>() {

	/**
	 * Provide a reference to the type of views that you are using
	 * (custom ViewHolder)
	 */
	class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
		val tvName: TextView
		val tvCount: TextView

		init {
			tvName = view.findViewById(R.id.tv_item_bed_name)
			tvCount = view.findViewById(R.id.tv_item_bed_plant_count)
		}
	}

	// Create new views (invoked by the layout manager)
	override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
		// Create a new view, which defines the UI of the list item
		val view = LayoutInflater.from(viewGroup.context)
			.inflate(R.layout.item_recycler_bed, viewGroup, false)

		return ViewHolder(view)
	}

	// Replace the contents of a view (invoked by the layout manager)
	override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

		// Get element from your dataset at this position and replace the
		// contents of the view with that element
		viewHolder.tvName.text = dataSet[position].bed.name
		viewHolder.tvCount.text = "Количество растений: ${dataSet[position].plantsCount}"
	}

	// Return the size of your dataset (invoked by the layout manager)
	override fun getItemCount() = dataSet.size

}