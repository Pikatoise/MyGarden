package com.example.mygarden

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.mygarden.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityMainBinding.inflate(layoutInflater)

		theme.applyStyle(R.style.Theme_BlueNavBar, false)

		window.requestFeature(Window.FEATURE_NO_TITLE)

		setContentView(binding.root)

		binding.apply {
			ivBurger.setOnClickListener {
				drawer.openDrawer(GravityCompat.START)
			}

			navigationView.setCheckedItem(R.id.item_home)
			replaceFragment(HomeFragment())

			navigationView.setNavigationItemSelectedListener {
				if (!it.isChecked){
					it.isChecked = true

					when(it.itemId){
						R.id.item_home -> {
							replaceFragment(HomeFragment())
						}

						R.id.item_beds -> {
							replaceFragment(BedsFragment())
						}

						R.id.item_plants -> {

						}

						R.id.item_about -> {
							replaceFragment(AboutFragment())
						}
					}
				}

				true
			}
		}
	}

	private fun replaceFragment(fragment: Fragment){
		val fragmentManager = supportFragmentManager
		val fragmentTransaction = fragmentManager.beginTransaction()
		fragmentTransaction.replace(R.id.frameLayout,fragment)
		fragmentTransaction.commit()

		binding.drawer.closeDrawers()
	}
}