package com.jrmnds.productlist.common

import org.junit.Assert
import org.junit.Test


internal class ConstantsTest {

    @Test
    fun testStrings(){
        Assert.assertNotNull(Constants.BASE_URL)
        Assert.assertEquals(Constants.BASE_URL, "https://bdk0sta2n0.execute-api.eu-west-1.amazonaws.com/mobile-assignment/")
    }

    @Test
    fun testInts(){
        Assert.assertNotNull(Constants.STARTING_KEY)
        Assert.assertEquals(Constants.STARTING_KEY, 1)
    }
}