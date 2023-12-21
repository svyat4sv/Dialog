package com.example.dialog

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.DialogFragment
import com.example.dialog.databinding.FragmentDialogBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MyDialogFragment : DialogFragment(){
    private lateinit var binding: FragmentDialogBinding
    private lateinit var coffee: Coffee
    private lateinit var dialogDataCallback: DialogDataCallback

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDialogBinding.inflate(layoutInflater)
        dialogDataCallback = requireActivity() as DialogDataCallback
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        coffee = Coffee()

        binding.radioGroup2.setOnCheckedChangeListener { _, id ->
            if (id == R.id.radioButton5)
                coffee.type = binding.radioButton5.text.toString()
            if (id == R.id.radioButton6)
                coffee.type = binding.radioButton6.text.toString()
            if (id == R.id.radioButton7)
                coffee.type = binding.radioButton7.text.toString()
            if (id == R.id.radioButton8)
                coffee.type = binding.radioButton8.text.toString()
        }

        binding.button.setOnClickListener {
            coffee.isSugar = binding.MycheckBox.isChecked
            dialogDataCallback.onDataEntered(coffee)
            dismiss()
        }

        var myCalendar = Calendar.getInstance()
        var datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDate(myCalendar)
            binding.dateTxt.visibility = View.VISIBLE
        }
        binding.button2.setOnClickListener {
            DatePickerDialog(
                requireActivity(),
                datePicker,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }
        binding.button3.setOnClickListener {
            val currentTime = Calendar.getInstance()
            val hour = currentTime.get(Calendar.HOUR_OF_DAY)
            val minute = currentTime.get(Calendar.MINUTE)

            TimePickerDialog(requireActivity(), TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                binding.timeTxt.text = "$hourOfDay : $minute"
                coffee.time = "$hourOfDay : $minute"
                binding.timeTxt.visibility = View.VISIBLE
            }, hour, minute, false).show()
        }

        binding.seekBar.max = 5
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.textView9.text = p1.toString()
                coffee.strength = p1
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
    }

    private fun updateDate(myCalendar: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.GERMANY)
        binding.dateTxt.text = sdf.format(myCalendar.time)
        coffee.date = sdf.format(myCalendar.time)

    }
}

