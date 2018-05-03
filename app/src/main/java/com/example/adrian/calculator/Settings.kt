package com.example.adrian.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_settings.*

class Settings : AppCompatActivity(), OnItemSelectedListener {
    private val displayColors = arrayOf("Light green", "Dark green", "Light blue")
    val colorMap = hashMapOf(
            "Light green" to android.R.color.holo_green_light,
            "Dark green" to android.R.color.holo_green_dark,
            "Light blue" to android.R.color.holo_blue_light)
    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val selectedColor =  colorMap[displayColors[position]]
        DisplaySettings.color = selectedColor!!
    }

    fun <Key,Value> Map<Key,Value>.findKeyByValue(searchValue: Value): Key? {
        for ((key, value) in this) {
            if (value == searchValue) return key
        }
        return null
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        spinner!!.onItemSelectedListener = this
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, displayColors)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner!!.adapter = aa
        val color = colorMap.findKeyByValue(DisplaySettings.color)
        spinner.setSelection(displayColors.indexOf(color))

    }
}
