package com.jrmnds.productlist.domain.model

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(ReviewInformation::class, PromoIcon::class)
internal class ProductTest {

    private lateinit var product: Product

    @Mock
    private lateinit var reviewInformation: ReviewInformation

    @Mock
    private lateinit var promoIcon: PromoIcon

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        product = Product(1, "productNameTest", reviewInformation,
        mutableListOf(), 1.1, 1.1, 1.1,
        "productImageTest", promoIcon, true)
    }

    @Test
    fun getProductIdTest(){
        Assert.assertNotNull(product.productId)
    }

    @Test
    fun getProductNameTest(){
        Assert.assertNotNull(product.productName)
    }

    @Test
    fun getProductReviewInfoTest(){
        Assert.assertNotNull(product.reviewInformation)
    }

    @Test
    fun getProductLabelsTest(){
        Assert.assertNotNull(product.productLabels)
    }

    @Test
    fun getProductSalesPriceTest(){
        Assert.assertNotNull(product.salesPriceIncVat)
    }

    @Test
    fun getProductListPriceInVacTest(){
        Assert.assertNotNull(product.listPriceIncVat)
    }

    @Test
    fun getProductListPriceExVatTest(){
        Assert.assertNotNull(product.listPriceExVat)
    }

    @Test
    fun getProductImageTest(){
        Assert.assertNotNull(product.productImage)
    }

    @Test
    fun getProductPromoIconTest(){
        Assert.assertNotNull(product.promoIcon)
    }

    @Test
    fun getProductDeliveryNextDayTest(){
        Assert.assertNotNull(product.nextDayDelivery)
    }
}