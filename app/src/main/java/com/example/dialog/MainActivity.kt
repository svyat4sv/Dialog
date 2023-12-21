package com.example.dialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dialog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), DialogDataCallback {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val dialogFragment = MyDialogFragment()
            val manager = supportFragmentManager
            dialogFragment.show(manager, "dialogFragment")
        }
    }

    override fun onDataEntered(coffee: Coffee) {
        binding.text.text = coffee.type
        binding.dateTxtView.text = coffee.date
        binding.textView5.text = (if (coffee.isSugar) "с сахаром, " else "").toString()
        binding.textView7.text = coffee.time
        binding.textView11.text = coffee.strength.toString()

    }
}