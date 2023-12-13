package com.example.mygarden

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.mygarden.databinding.ActivityInfoPlantsBinding

class InfoPlantsActivity : AppCompatActivity() {
	private lateinit var binding: ActivityInfoPlantsBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityInfoPlantsBinding.inflate(layoutInflater)

		setContentView(binding.root)

		binding.ivClose.setOnClickListener {
			finish()
		}

		binding.wvPlants.settings.javaScriptEnabled = true
		binding.wvPlants.webViewClient = WebViewClient()
		binding.wvPlants.loadUrl("https://www.ogorod.ru/ru/ogorod/other/17634/10-neobychnyh-ovoshchej-kotorye-stoit-posadit-v-sleduyushchem-sezone-po-mneniyu-nashih-chitatelej.htm")
	}
}