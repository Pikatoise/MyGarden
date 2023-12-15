package com.example.mygarden

import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mygarden.databinding.FragmentAboutBinding
import java.time.LocalDate

class AboutFragment : Fragment() {
	private lateinit var binding: FragmentAboutBinding
	private lateinit var dbBeds: BedRepository
	private lateinit var dbPlants: PlantRepository
	private lateinit var dbHarvests: HarvestRepository

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		binding = FragmentAboutBinding.inflate(inflater,container,false)

		dbBeds = BedRepository(this.requireContext())
		dbPlants = PlantRepository(this.requireContext())
		dbHarvests = HarvestRepository(this.requireContext())

		binding.apply {
			tvDeveloperContactsMailContent.setOnClickListener {
				setClipboard(requireContext(),tvDeveloperContactsMailContent.text.toString())

				Toast.makeText(requireContext(),"Email скопирован!", Toast.LENGTH_SHORT).show()
			}

			rlDeveloperContactsTelegram.setOnClickListener {
				val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/pikatoise"))
				startActivity(browserIntent)
			}

			tvDeveloperContactsVk.setOnClickListener {
				val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/pikatoise"))
				startActivity(browserIntent)
			}

			tvProgrammText.setOnClickListener {
//				val plants = dbPlants.getPlantsByBed(1)
//				val plant = if (plants.size > 0) plants[0] else Plant(-1,"empty",-1,"empty",-1,-1)
//
//				Toast.makeText(this@AboutFragment.requireContext(),"Id: ${plant.id}\nName: ${plant.name}",Toast.LENGTH_SHORT).show()

//				val harvest = dbHarvests.getFirstHarvest()
//
//				val date = LocalDate.ofEpochDay(harvest.date.toLong())
//
//				Toast.makeText(this@AboutFragment.requireContext(),"Id: ${harvest.id}\nDate: ${date.toString()}",Toast.LENGTH_SHORT).show()
			}
		}

		return binding.root
	}

	private fun setClipboard(context: Context, text: String) {
		val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
		val clip = ClipData.newPlainText("Copied Text", text)
		clipboard.setPrimaryClip(clip)
	}
}