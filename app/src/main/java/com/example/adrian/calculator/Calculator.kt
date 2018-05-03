package com.example.adrian.calculator


import java.math.BigDecimal
import java.util.*
import kotlin.math.pow
import kotlin.math.sqrt

class Calculator(private val listener: CalculatorListener){
    var stack : Stack<Double> = Stack()
    var currentValue : String = ""
    init {
        listener.onCalculationCompleted(display())
    }

    private fun display(): String {
        var displayString = "";
        for(i in 4 downTo 1){
            displayString += "$i: "
            if(stack.size >= i){
                val stripedVal = BigDecimal(stack[stack.size - i]).stripTrailingZeros()
                displayString += stripedVal
            }
            displayString += '\n'
        }
        displayString += currentValue;
        return displayString
    }

    fun insertNumber(value : Int){
        currentValue += value.toString()
        listener.onCalculationCompleted(display())
    }
    fun insertDot(){
        currentValue += '.'
        listener.onCalculationCompleted(display())
    }
    fun back(){
        if(currentValue.isNotEmpty()){
            currentValue = currentValue.dropLast(1)
        } else if(stack.isNotEmpty()){
            stack.pop()
        }
        listener.onCalculationCompleted(display())
    }
    fun enter(){
        if(currentValue.isNotEmpty()) {
            stack.push(currentValue.toDouble())
            drop()
        } else if(stack.isNotEmpty()){
            stack.push(stack.peek())
        }
        listener.onCalculationCompleted(display())
    }
    fun swap(){
        if(stack.size > 1){
            val top = stack.pop();
            val second = stack.pop();
            stack.push(top)
            stack.push(second)
        }
        listener.onCalculationCompleted(display())
    }
    fun sqrt(){
        if(currentValue.isNotEmpty()){
            stack.push(sqrt(currentValue.toDouble()))
            drop()
        } else if (stack.isNotEmpty()){
            stack.push(sqrt(stack.pop()))
        }
        listener.onCalculationCompleted(display())
    }
    fun yx(){
        if(currentValue.isNotEmpty() && stack.isNotEmpty()){
            val top = stack.pop()
            val current = currentValue.toDouble()
            stack.push(top.pow(current))
            drop()
        } else if (stack.size > 1){
            val top = stack.pop()
            val second = stack.pop()
            stack.push(second.pow(top))
        }
        listener.onCalculationCompleted(display())
    }

    fun ac() {
        stack.clear()
        currentValue = "";
        listener.onCalculationCompleted(display())
    }

    fun drop() {
        if(currentValue.isNotEmpty()){
            currentValue = ""
        } else{
            stack.pop()
        }
        listener.onCalculationCompleted(display())
    }

    fun add() {
        if(currentValue.isNotEmpty() && stack.isNotEmpty()){
            val result = currentValue.toDouble() + stack.pop()
            stack.push(result)
            drop()
        } else if(stack.size > 1){
            val top = stack.pop()
            val second = stack.pop()
            stack.push(top + second)
        }
        listener.onCalculationCompleted(display())
    }
    fun subtract() {
        if(currentValue.isNotEmpty() && stack.isNotEmpty()){
            val result = stack.pop() - currentValue.toDouble()
            stack.push(result)
            drop()
        } else if(stack.size > 1){
            val top = stack.pop()
            val second = stack.pop()
            stack.push(second - top)
        }
        listener.onCalculationCompleted(display())
    }
    fun multiply() {
        if(currentValue.isNotEmpty() && stack.isNotEmpty()){
            val result = currentValue.toDouble() * stack.pop()
            stack.push(result)
            drop()
        } else if(stack.size > 1){
            val top = stack.pop()
            val second = stack.pop()
            stack.push(top * second)
        }
        listener.onCalculationCompleted(display())
    }

    fun divide(){
        if(currentValue.isNotEmpty() && stack.isNotEmpty()){
            val result = stack.pop()  / currentValue.toDouble()
            stack.push(result)
            drop()
        } else if(stack.size > 1){
            val top = stack.pop()
            val second = stack.pop()
            stack.push(second / top)
        }
        listener.onCalculationCompleted(display())
    }
}