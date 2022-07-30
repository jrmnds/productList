package com.jrmnds.productlist.data.remote.dto

import com.jrmnds.productlist.domain.model.PageInfo
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
internal class PagingInfoDTOTest {

    private lateinit var pagingInfoDto: PagingInfoDTO

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
        pagingInfoDto = PagingInfoDTO(mutableListOf(productDTO), 1, 24, 24, 1)
    }

    @Test
    fun testPagingInfoDtoToPagingInfo(){
        val pagingInfo = pagingInfoDto.toPageInfo()
        MatcherAssert.assertThat(pagingInfo, CoreMatchers.isA(PageInfo::class.java))
        Assert.assertNotNull(pagingInfo)
    }

    @Test
    fun testGetProductList(){
        Assert.assertNotNull(pagingInfoDto.productList)
    }

    @Test
    fun testGetCurrentList(){
        Assert.assertNotNull(pagingInfoDto.currentPage)
    }

    @Test
    fun testGetPageSize(){
        Assert.assertNotNull(pagingInfoDto.pageSize)
    }

    @Test
    fun testGetTotalResults(){
        Assert.assertNotNull(pagingInfoDto.totalResults)
    }

    @Test
    fun testGetPageCount(){
        Assert.assertNotNull(pagingInfoDto.pageCount)
    }
}