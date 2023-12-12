package com.example.mygarden

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.mygarden.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
	private lateinit var binding: ActivitySplashBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		theme.applyStyle(R.style.Theme_BlueNavBar,true)

		AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

		binding = ActivitySplashBinding.inflate(layoutInflater)

		setContentView(binding.root)

		binding.ivLogo.alpha = 0f
		binding.ivLogo.animate().setDuration(1500).alpha(1f ).withEndAction {
			val i = Intent(this, MainActivity::class.java)

			startActivity(i)

			overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)

			finish()
		}
	}
}