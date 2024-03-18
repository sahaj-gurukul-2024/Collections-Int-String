package com.ownlist.linkedlist


sealed interface MyStringList {
    fun getUpperCase(): MyStringList = transform { it.uppercase() }

    fun getLowerCase(): MyStringList = transform { it.lowercase() }

    fun transform(transformFunction: (String) -> String): MyStringList = when (this) {
        EmptyStringList -> EmptyStringList
        is NonEmptyStringList -> NonEmptyStringList(transformFunction(head), tail.transform(transformFunction))
    }

    fun getStringsWithThreeCharacters(): MyStringList {
        return when (this) {
            EmptyStringList -> EmptyStringList
            is NonEmptyStringList ->
                if (head.length == 3) NonEmptyStringList(
                    head,
                    tail.getStringsWithThreeCharacters()
                ) else tail.getStringsWithThreeCharacters()
        }
    }

    fun getLengthList(): MyIntegerList = when (this) {
        EmptyStringList -> EmptyIntegerList
        is NonEmptyStringList -> NonEmptyIntegerList(head.length, tail.getLengthList())
    }

    fun getLengthSum(): Int? = getLengthList().getSum()

    fun concatAll(separator: String): String? = concat(separator) { it }

    fun concatFirstCharacter(separator: String): String? = concat(separator) { it.first().toString() }

    fun concat(separator: String, getValue: (String) -> String): String? = when (this) {
        EmptyStringList -> null
        is NonEmptyStringList -> "${getValue(head)}$separator${tail.concat(separator, getValue) ?: ""}".trim()
    }
}

data class NonEmptyStringList(val head: String, val tail: MyStringList) : MyStringList

data object EmptyStringList : MyStringList