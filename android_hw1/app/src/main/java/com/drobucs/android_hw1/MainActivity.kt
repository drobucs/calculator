package com.drobucs.android_hw1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.drobucs.android_hw1.expression.parser.*
import java.text.NumberFormat
import java.util.*
import kotlin.IllegalArgumentException

class MainActivity : AppCompatActivity() {

    companion object {
        const val LAST_STRING = "LAST_STRING"
        const val LOCALE = "RU"
        const val TAG = "MainActivity"
        val OPERATIONS = setOf(
            '+', '-', '*', '/'
        )
    }

    private lateinit var textView: TextView
    private lateinit var lastString: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lastString = getString(R.string.zero)

        textView = findViewById(R.id.result_textView)

        val clear: Button = findViewById(R.id.but_clear)
        val clearE: Button = findViewById(R.id.but_clear_element)
        val equal: Button = findViewById(R.id.but_equal)
        val point: Button = findViewById(R.id.but_point)

        val plus: Button = findViewById(R.id.but_plus)
        val minus: Button = findViewById(R.id.but_minus)
        val multiply: Button = findViewById(R.id.but_multiply)
        val divide: Button = findViewById(R.id.but_divide)

        val digits: Array<Button> = arrayOf(
            findViewById(R.id.but_zero),
            findViewById(R.id.but_one),
            findViewById(R.id.but_two),
            findViewById(R.id.but_three),
            findViewById(R.id.but_four),
            findViewById(R.id.but_five),
            findViewById(R.id.but_six),
            findViewById(R.id.but_seven),
            findViewById(R.id.but_eight),
            findViewById(R.id.but_nine)
        )

        for (i in 0..9) {
            digits[i].setOnClickListener {
                if (lastString == getString(R.string.zero)) {
                    setString(i.toString())
                } else {
                    setString(lastString + i.toString())
                }
            }
        }
        clear.setOnClickListener {
            setString(getString(R.string.zero))
        }

        clearE.setOnClickListener {
            if (lastString.length == 1) {
                setString(getString(R.string.zero))
            } else {
                setString(lastString.substring(0, lastString.length - 1))
            }
        }

        point.setOnClickListener {
            if (lastString.isEmpty()) {
                setString(getString(R.string.zero) + getString(R.string.point))
            } else if (checkPoint()) {
                setString(lastString + getString(R.string.point))
            }
        }

        setOnClickOp(plus, getString(R.string.plus))
        setOnClickOp(minus, getString(R.string.minus))
        setOnClickOp(multiply, getString(R.string.multiply))
        setOnClickOp(divide, getString(R.string.divide))

        equal.setOnClickListener {
            try {
                val parser = ExpressionParser()
                val result = parser.parse(lastString).evaluate(0.0)
                setString(NumberFormat.getInstance(Locale(LOCALE))
                    .format(result)
                    .replace(',', '.')
                    .filter { !it.isWhitespace() })
            } catch (e: IllegalArgumentException) {
                setString(getString(R.string.error))
            }
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        Log.i(TAG, "onSaveInstanceState")
        outState.putString(LAST_STRING, lastString)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Log.i(TAG, "onRestoreInstanceState")
        super.onRestoreInstanceState(savedInstanceState)
        lastString = savedInstanceState.getString(LAST_STRING).toString()
        updateTextView()
    }

    private fun updateTextView() {
        setString(lastString)
    }

    private fun setString(other: String) {
        lastString = other
        textView.setText(lastString)
    }


    private fun checkPoint(): Boolean {
        var i = lastString.length - 1
        while (i > 0 && lastString[i].isDigit()) {
            --i
        }
        return lastString[i] != '.' && back().isDigit()
    }

    private fun back(): Char {
        return if (lastString.isEmpty()) '_' else lastString[lastString.length - 1]
    }

    private fun setOnClickOp(but_op : View, symbol : String) {
        but_op.setOnClickListener {
            if (lastString.isNotEmpty() && OPERATIONS.contains(back())) {
                setString(lastString.substring(0, lastString.length - 1) + symbol)
            } else if (lastString.isNotEmpty()) {
                setString(lastString + symbol)
            }
        }
    }
}