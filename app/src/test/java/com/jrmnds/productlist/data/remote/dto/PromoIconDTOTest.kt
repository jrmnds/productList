package com.jrmnds.productlist.data.remote.dto

import com.jrmnds.productlist.domain.model.PromoIcon
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert
import org.junit.Before
import org.junit.Test


internal class PromoIconDTOTest {

    private lateinit var promoIconDTO: PromoIconDTO

    @Before
    fun setUp() {
        promoIconDTO = PromoIconDTO("textIconTest", "typeIconText")
    }

    @Test
    fun testPromoIconDtoToPromoIcon(){
        val promoIcon = promoIconDTO.toPromoIcon()
        MatcherAssert.assertThat(promoIcon, CoreMatchers.isA(PromoIcon::class.java))
        Assert.assertNotNull(promoIcon)
    }

    @Test
    fun testGetPromoIconDtoText(){
        Assert.assertNotNull(promoIconDTO.text)
    }

    @Test
    fun testGetPromoIconDtoType(){
        Assert.assertNotNull(promoIconDTO.type)
    }
}