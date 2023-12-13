package com.example.mygarden

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.mygarden.databinding.ActivityInfoLifehacksBinding

class InfoLifehacksActivity : AppCompatActivity() {
	private lateinit var binding: ActivityInfoLifehacksBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityInfoLifehacksBinding.inflate(layoutInflater)

		setContentView(binding.root)

		binding.ivClose.setOnClickListener {
			finish()
		}

		binding.wvLifehacks.settings.javaScriptEnabled = true
		binding.wvLifehacks.webViewClient = WebViewClient()
		binding.wvLifehacks.loadUrl("https://www.ogorod.ru/ru/main/useful/19299/5-lajfhakov-dlya-sbora-urozhaya.htm")
	}
}