package com.jrmnds.productlist.domain.model

import org.junit.Assert
import org.junit.Before
import org.junit.Test


internal class PromoIconTest {

    private lateinit var promoIcon: PromoIcon

    @Before
    fun setUp() {
        promoIcon = PromoIcon("testText", "testType")
    }

    @Test
    fun getPromoIconTextTest(){
        Assert.assertNotNull(promoIcon.text)
    }

    @Test
    fun getPromoIconTypeTest(){
        Assert.assertNotNull(promoIcon.type)
    }
}