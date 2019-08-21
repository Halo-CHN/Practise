package com.halochn.kaixue_kt

import com.halochn.kaixue_kt.lesson_2.ArrayAndList
import com.halochn.kaixue_kt.lesson_2.DeConstrutor
import com.halochn.kaixue_kt.lesson_2.DeConstrutor2
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun lesson_2_constroct_test() {

        val testClass = DeConstrutor.newInstance()

        testClass.cons()

        DeConstrutor2.cons()
    }

    @Test
    fun lesson_2_array_list_test(){
        val testClass=ArrayAndList()

        testClass.test()

//        testClass.test2()
    }
}