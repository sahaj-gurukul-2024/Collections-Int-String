package com.ownlist.linkedlist

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class OwnStringTest {


    private val stringList = NonEmptyStringList("Hello", NonEmptyStringList("World", EmptyStringList))

    @Test
    fun `test upper case`() {
        val upperCaseStrings = stringList.getUpperCase()

        assertEquals(
            NonEmptyStringList("HELLO", NonEmptyStringList("WORLD", EmptyStringList)),
            upperCaseStrings
        )
    }

    @Test
    fun `test lower case`() {
        val lowerCaseStrings = stringList.getLowerCase()

        assertEquals(
            NonEmptyStringList("hello", NonEmptyStringList("world", EmptyStringList)),
            lowerCaseStrings
        )
    }

    @Test
    fun `test filter 3 character strings`() {
        val strings = NonEmptyStringList("abc", NonEmptyStringList("hello", EmptyStringList))

        assertEquals(
            NonEmptyStringList("abc", EmptyStringList),
            strings.getStringsWithThreeCharacters()
        )
    }

    @Test
    fun `get integer list with string length`() {
        val strings = NonEmptyStringList("abc", NonEmptyStringList("hello", EmptyStringList))

        val lengths = strings.getLengthList()

        assertEquals(
            NonEmptyIntegerList(3, NonEmptyIntegerList(5, EmptyIntegerList)),
            lengths
        )
    }

    @Test
    fun `get sum value of all the string lengths from string list`() {
        val strings = NonEmptyStringList("abc", NonEmptyStringList("hello", EmptyStringList))
        val lengthSum = strings.getLengthSum()

        assertEquals(
            8,
            lengthSum
        )
    }

    @Test
    fun `get single string by concatinating all values with separator`() {
        val strings = NonEmptyStringList("abc", NonEmptyStringList("hello", EmptyStringList))
        val continatedString = strings.concatAll(" ")

        val expected = "abc hello"
        assertEquals(
            expected,
            continatedString
        )
    }
    @Test
    fun `get single string by concatinating first letter from string values with separator`() {
        val strings = NonEmptyStringList("abc", NonEmptyStringList("hello", EmptyStringList))
        val continatedString = strings.concatFirstCharacter("")

        val expected = "ah"
        assertEquals(
            expected,
            continatedString
        )
    }
}