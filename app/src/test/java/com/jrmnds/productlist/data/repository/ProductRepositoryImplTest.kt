package com.jrmnds.productlist.data.repository

import com.jrmnds.productlist.data.remote.ProductApiServices
import com.jrmnds.productlist.data.remote.dto.PagingInfoDTO
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner


@RunWith(PowerMockRunner::class)
@PrepareForTest(PagingInfoDTO::class)
internal class ProductRepositoryImplTest {


    private lateinit var productRepositoryImpl: ProductRepositoryImpl

    @Mock
    private lateinit var services: ProductApiServices

    @Mock
    private lateinit var pageInfoDTO: PagingInfoDTO

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        PowerMockito.mockStatic(PagingInfoDTO::class.java)
        productRepositoryImpl = ProductRepositoryImpl(services)
    }

    @Test
    fun testGetProductsSuccess(){
        runBlocking {
            PowerMockito.`when`(services.getAllProductsPerPage(1)).thenReturn(
                pageInfoDTO
            )

            val response = productRepositoryImpl.getProducts(1)

            MatcherAssert.assertThat(response, CoreMatchers.isA(pageInfoDTO.javaClass))
        }
    }
}