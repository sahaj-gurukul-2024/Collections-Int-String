package com.ownlist.linkedlist

import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow

sealed interface MyIntegerList {
    fun getByIndex(index: Int): Int? = when (this) {
        is NonEmptyIntegerList -> if (index == 0) head else tail.getByIndex(index - 1)
        is EmptyIntegerList -> null
    }

    fun prepend(value: Int): MyIntegerList = NonEmptyIntegerList(value, this)

    fun append(value: Int): MyIntegerList = when (this) {
        is EmptyIntegerList -> NonEmptyIntegerList(value, EmptyIntegerList)
        is NonEmptyIntegerList -> NonEmptyIntegerList(head, tail.append(value))
    }

    fun getPowerValues(power: Int): MyIntegerList {
        return transform { it.toDouble().pow(power.toDouble()).toInt() }
    }

    fun transform(transformFunction: (Int) -> Int): MyIntegerList = when (this) {
        is EmptyIntegerList -> EmptyIntegerList
        is NonEmptyIntegerList -> NonEmptyIntegerList(transformFunction(head), tail.transform(transformFunction))
    }

    fun getOddValues(): MyIntegerList {
        val filterFunction: (Int) -> Boolean = { it % 2 != 0 }
        return filter(filterFunction)
    }

    fun getEvenValues(): MyIntegerList {
        val filterFunction: (Int) -> Boolean = { it % 2 == 0 }
        return filter(filterFunction)
    }

    fun filter(filterFunction: (Int) -> Boolean): MyIntegerList = when (this) {
        EmptyIntegerList -> EmptyIntegerList
        is NonEmptyIntegerList -> if (filterFunction(head)) NonEmptyIntegerList(head, tail.filter(filterFunction))
        else tail.filter(filterFunction)
    }


    fun incrementBy(incrementStep: Int): MyIntegerList {
        return transform { it.plus(incrementStep) }
    }

    fun getSquareValues(): MyIntegerList = getPowerValues(2)

    fun getCubeValues(): MyIntegerList = getPowerValues(3)

    fun getSum(): Int? = aggregate(0) { a, b -> a + b }

    fun getMin(): Int? = aggregate(Int.MAX_VALUE, ::min)

    fun getMax(): Int? = aggregate(Int.MIN_VALUE, ::max)

    fun aggregate(default: Int, func: (Int, Int) -> Int): Int? = when (this) {
        is EmptyIntegerList -> null
        is NonEmptyIntegerList -> func(head, tail.aggregate(default, func) ?: default)
    }

}

data class NonEmptyIntegerList(val head: Int, val tail: MyIntegerList) : MyIntegerList

data object EmptyIntegerList : MyIntegerList
