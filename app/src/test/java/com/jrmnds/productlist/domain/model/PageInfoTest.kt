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
@PrepareForTest(Product::class)
internal class PageInfoTest {

    private lateinit var pageInfo: PageInfo

    @Mock
    private lateinit var product: Product

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        pageInfo = PageInfo(mutableListOf(product), 1)
    }

    @Test
    fun getProductListTest(){
        Assert.assertNotNull(pageInfo.productList)
    }

    @Test
    fun getPageCountTest(){
        Assert.assertNotNull(pageInfo.pageCount)
    }

}