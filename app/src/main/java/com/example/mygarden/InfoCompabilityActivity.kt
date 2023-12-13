package com.example.mygarden

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.mygarden.databinding.ActivityInfoCompabilityBinding

class InfoCompabilityActivity : AppCompatActivity() {
	private lateinit var binding: ActivityInfoCompabilityBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityInfoCompabilityBinding.inflate(layoutInflater)

		setContentView(binding.root)

		binding.ivClose.setOnClickListener {
			finish()
		}

		binding.wvCompability.settings.javaScriptEnabled = true
		binding.wvCompability.webViewClient = WebViewClient()
		binding.wvCompability.loadUrl("https://fishki.net/3089515-25-ocheny-vazhnyh-shpargalok-dlja-sadovodov-ogorodnikov.html")
	}
}