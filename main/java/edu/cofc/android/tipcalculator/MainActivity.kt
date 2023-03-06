package edu.cofc.android.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import edu.cofc.android.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var amountTip = 0.0
    private var amountTotal = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // pass through the percent that is clicked
        binding.tenPercent.setOnClickListener {
            updateTextFields(.10)
        }

        binding.fifteenPercent.setOnClickListener {
            updateTextFields(.15)
        }

        binding.eighteenPercent.setOnClickListener {
            updateTextFields(.18)
        }

        binding.twentyPercent.setOnClickListener {
            updateTextFields(.20)
        }

        binding.billEdit.requestFocus()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
    }

    private fun updateTextFields(tipPercent : Double) {
        val costAmount = binding.billEdit.text.toString().toDouble()

        amountTip = tipPercent * costAmount

        amountTotal = amountTip + costAmount

        binding.tipFieldFill.text = NumberFormat.getCurrencyInstance().format(amountTip)
        binding.totalAmountFieldFill.text = NumberFormat.getCurrencyInstance().format(amountTotal)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putDouble("amountTip", amountTip)
        outState.putDouble("amountTotal", amountTotal)
    }

    override fun onRestoreInstanceState(inState: Bundle) {
        super.onRestoreInstanceState(inState)
        amountTip = inState.getDouble("amountTip")
        amountTotal = inState.getDouble("amountTotal")
        binding.tipFieldFill.text = NumberFormat.getCurrencyInstance().format(amountTip)
        binding.totalAmountFieldFill.text = NumberFormat.getCurrencyInstance().format(amountTotal)
    }
}