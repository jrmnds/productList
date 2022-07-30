package com.jrmnds.productlist.data.remote.dto


import com.jrmnds.productlist.domain.model.Product
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(ReviewInformationDTO::class, PromoIconDTO::class)
internal class ProductDTOTest {

    private lateinit var productDTO: ProductDTO

    @Mock
    private lateinit var reviewInformationDTO: ReviewInformationDTO

    @Mock
    private lateinit var promoIconDTO: PromoIconDTO

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        productDTO = ProductDTO(1, "productNameTest", reviewInformationDTO,
            mutableListOf(), 1, 1.1, 1.1, 1.1,
        "productImageTest", "testTilte", promoIconDTO, true)
    }

    @Test
    fun testProductDtoToProduct(){
        val product = productDTO.toProduct()
        MatcherAssert.assertThat(product, CoreMatchers.isA(Product::class.java))
    }

    @Test
    fun testGetProductDtoId(){
        Assert.assertNotNull(productDTO.productId)
    }

    @Test
    fun testGetProductDtoName(){
        Assert.assertNotNull(productDTO.productName)
    }

    @Test
    fun testGetProductDtoReviewInformationDto(){
        Assert.assertNotNull(productDTO.reviewInformation)
    }

    @Test
    fun testGetProductDtoAvailabilityState(){
        Assert.assertNotNull(productDTO.availabilityState)
    }

    @Test
    fun testGetProductDtoSalesPriceIncVat(){
        Assert.assertNotNull(productDTO.salesPriceIncVat)
    }

    @Test
    fun testGetProductDtoListPriceInVat(){
        Assert.assertNotNull(productDTO.listPriceIncVat)
    }

    @Test
    fun testGetProductDtoListPriceExVat(){
        Assert.assertNotNull(productDTO.listPriceExVat)
    }

    @Test
    fun testGetProductDtoProductImage(){
        Assert.assertNotNull(productDTO.productImage)
    }

    @Test
    fun testGetProductDtoCoolsBlueChoiceTitle(){
        Assert.assertNotNull(productDTO.coolbluesChoiceInformationTitle)
    }

    @Test
    fun testGetProductDtoPromoIcon(){
        Assert.assertNotNull(productDTO.promoIcon)
    }

    @Test
    fun testGetProductDtoNextDayDelivery(){
        Assert.assertNotNull(productDTO.nextDayDelivery)
    }
}