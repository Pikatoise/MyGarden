package com.example.mygarden

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mygarden.databinding.ActivityAddBedBinding

class AddBedActivity : AppCompatActivity() {
	private lateinit var binding: ActivityAddBedBinding
	private lateinit var bdBeds: BedRepository

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityAddBedBinding.inflate(layoutInflater)
		bdBeds = BedRepository(this)

		binding.ivClose.setOnClickListener {
			finish()
		}

		binding.rlAddBedSave.setOnClickListener {
			var name = binding.etAddBedName.text.toString()

			if (name.isNullOrEmpty()){
				Toast.makeText(this,"Введите название!",Toast.LENGTH_SHORT).show()
				return@setOnClickListener
			}

			if (name.length > 20){
				Toast.makeText(this,"Название слишком длинное!\nМаксимальная длина: 20 символов",Toast.LENGTH_SHORT).show()
				return@setOnClickListener
			}

			bdBeds.addBed(name)

			finish()
		}

		setContentView(binding.root)
	}
}