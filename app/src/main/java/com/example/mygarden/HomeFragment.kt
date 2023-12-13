package com.example.mygarden

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mygarden.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
	private lateinit var binding: FragmentHomeBinding

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		binding = FragmentHomeBinding.inflate(inflater,container,false)

		binding.apply {
			cvCompability.setOnClickListener {
				val intent = Intent(this@HomeFragment.context,InfoCompabilityActivity::class.java)

				startActivity(intent)
			}

			cvLifehacks.setOnClickListener {
				val intent = Intent(this@HomeFragment.context,InfoLifehacksActivity::class.java)

				startActivity(intent)
			}

			cvIntrestingPlants.setOnClickListener {
				val intent = Intent(this@HomeFragment.context,InfoPlantsActivity::class.java)

				startActivity(intent)
			}
		}

		return binding.root
	}
}