package com.byeoru.unitconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import androidx.core.widget.addTextChangedListener
import com.byeoru.unitconverter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var cmToM = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val inputEditText = binding.inputEditText
        val outputTextView = binding.outputTextView
        val inputUnitTextView = binding.inputUnitTextView
        val outputUnitTextView = binding.outputUnitTextView
        val swapButton = binding.swapButton

        var inputNumber = 0.0

        inputEditText.inputType = InputType.TYPE_CLASS_NUMBER

        inputEditText.addTextChangedListener { text ->
            inputNumber = if (text.isNullOrEmpty()) { 0.0 }
            else {
                try {
                    text.toString().toDouble()
                } catch (e:java.lang.NumberFormatException) {
                    inputNumber
                }
            }
            outputTextView.text = inputNumber.times(0.01).toString()
        }

        swapButton.setOnClickListener {
            cmToM = cmToM.not()

            if (cmToM) {
                inputUnitTextView.text = "cm"
                outputUnitTextView.text = "m"

                inputEditText.inputType = InputType.TYPE_CLASS_NUMBER

                outputTextView.text = inputNumber.times(0.01).toString()
            } else {
                inputUnitTextView.text = "m"
                outputUnitTextView.text = "cm"

                inputEditText.inputType = InputType.TYPE_NUMBER_FLAG_DECIMAL

                outputTextView.text = inputNumber.times(100).toString()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("cmToM", cmToM)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        cmToM = savedInstanceState.getBoolean("cmToM")
        binding.inputUnitTextView.text = if (cmToM) "cm" else "m"
        binding.outputUnitTextView.text = if (cmToM) "m" else "cm"
        super.onRestoreInstanceState(savedInstanceState)
    }
}