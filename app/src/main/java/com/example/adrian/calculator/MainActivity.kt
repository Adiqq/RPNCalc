package com.example.adrian.calculator

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*


object DisplaySettings {
    var color = android.R.color.holo_green_dark;
}

class MainActivity : AppCompatActivity(), CalculatorListener {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.settings -> {
                val intent = Intent(this, Settings::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCalculationCompleted(result: String) {
        textView.text = result
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView.setBackgroundResource(DisplaySettings.color)
        val calc = Calculator(this)
        buttonOne.setOnClickListener {
            calc.insertNumber(1)
        }
        buttonTwo.setOnClickListener{
            calc.insertNumber(2)
        }
        buttonThree.setOnClickListener{
            calc.insertNumber(3)
        }
        buttonFour.setOnClickListener{
            calc.insertNumber(4)
        }
        buttonFive.setOnClickListener{
            calc.insertNumber(5)
        }
        buttonSix.setOnClickListener{
            calc.insertNumber(6)
        }
        buttonSeven.setOnClickListener{
            calc.insertNumber(7)
        }
        buttonEight.setOnClickListener{
            calc.insertNumber(8)
        }
        buttonNine.setOnClickListener{
            calc.insertNumber(9)
        }
        buttonZero.setOnClickListener{
            calc.insertNumber(0)
        }
        buttonPlus.setOnClickListener{
            calc.add()
        }
        buttonMinus.setOnClickListener{
            calc.subtract()
        }
        buttonMultiply.setOnClickListener{
            calc.multiply()
        }
        buttonDivide.setOnClickListener{
            calc.divide()
        }
        buttonYX.setOnClickListener{
            calc.yx()
        }
        buttonEnter.setOnClickListener{
            calc.enter()
        }
        buttonSwap.setOnClickListener{
            calc.swap()
        }
        buttonDrop.setOnClickListener{
            calc.drop()
        }
        buttonAc.setOnClickListener{
            calc.ac()
        }
        buttonBack.setOnClickListener{
            calc.back()
        }
        buttonDot.setOnClickListener{
            calc.insertDot()
        }
        buttonSqrt.setOnClickListener{
            calc.sqrt()
        }
    }
}
