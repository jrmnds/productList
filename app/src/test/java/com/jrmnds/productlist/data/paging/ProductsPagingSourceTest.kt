package com.jrmnds.productlist.data.paging

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.jrmnds.productlist.data.remote.dto.PagingInfoDTO
import com.jrmnds.productlist.data.remote.dto.ProductDTO
import com.jrmnds.productlist.data.remote.dto.PromoIconDTO
import com.jrmnds.productlist.data.remote.dto.ReviewInformationDTO
import com.jrmnds.productlist.domain.model.PageInfo
import com.jrmnds.productlist.domain.model.Product
import com.jrmnds.productlist.domain.repository.ProductRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import java.lang.NullPointerException


@ExperimentalCoroutinesApi
@RunWith(PowerMockRunner::class)
@PrepareForTest(PagingInfoDTO::class, PageInfo::class,
    ReviewInformationDTO::class, PromoIconDTO::class)
internal class ProductsPagingSourceTest {

    private lateinit var productsPagingSource: ProductsPagingSource

    private lateinit var pageInfo: PageInfo

    private lateinit var productDTO: ProductDTO

    @Mock
    private lateinit var repository: ProductRepository

    @Mock
    private lateinit var pageInfoDto: PagingInfoDTO

    @Mock
    private lateinit var reviewInformationDTO: ReviewInformationDTO

    @Mock
    private lateinit var promoIconDTO: PromoIconDTO



    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        productDTO = ProductDTO(1, "productNameTest", reviewInformationDTO,
            mutableListOf(), 1, 1.1, 1.1, 1.1,
            "productImageTest", "testTilte", promoIconDTO, true)
        pageInfoDto = PagingInfoDTO(mutableListOf(productDTO), 1, 2, 3, 5)
        pageInfo = PageInfo(mutableListOf(productDTO.toProduct()), 1)
    }


    @Test
    fun testPagingLoaderFailureNullPointerException(){
        runTest {
            PowerMockito.`when`(repository.getProducts(1)).thenReturn(null)
            productsPagingSource = ProductsPagingSource(repository, null)

            val result = PagingSource.LoadResult.Error<Int, Product>(throwable = NullPointerException())

            Assert.assertEquals(result.toString(), productsPagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = 0,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            ).toString())
        }
    }
    
    @Test
    fun testPagingLoaderSuccess(){
        runTest {
            PowerMockito.`when`(repository.getProducts(0)).thenReturn(pageInfoDto)
            productsPagingSource = ProductsPagingSource(repository, null)

            val result = PagingSource.LoadResult.Page(
                data = pageInfo.productList,
                prevKey = null,
                nextKey = 1
            )

            MatcherAssert.assertThat(result, CoreMatchers.isA(PagingSource.LoadResult.Page::class.java))
            Assert.assertEquals(result, productsPagingSource.load(
                PagingSource.LoadParams.Refresh(
                    0,
                    1,
                    false
                )
            ))
        }
    }


    @Test
    fun testPagingLoaderSuccessWithProductName(){
        runTest {
            productsPagingSource = ProductsPagingSource(repository, "productNameTest")

            PowerMockito.`when`(repository.getProducts(0)).thenReturn(pageInfoDto)

            val result = PagingSource.LoadResult.Page(
                data = pageInfo.productList,
                prevKey = null,
                nextKey = 1
            )

            MatcherAssert.assertThat(result, CoreMatchers.isA(PagingSource.LoadResult.Page::class.java))
            Assert.assertEquals(result, productsPagingSource.load(
                PagingSource.LoadParams.Refresh(
                    0,
                    1,
                    false
                )
            ))
        }
    }

    @Test
    fun testPagingLoaderSuccessPrepend(){
        runTest {
            PowerMockito.`when`(repository.getProducts(0)).thenReturn(pageInfoDto)
            productsPagingSource = ProductsPagingSource(repository, null)

            val result = PagingSource.LoadResult.Page(
                data = pageInfo.productList,
                prevKey = null,
                nextKey = 1
            )

            MatcherAssert.assertThat(result, CoreMatchers.isA(PagingSource.LoadResult.Page::class.java))
            Assert.assertEquals(result, productsPagingSource.load(
                PagingSource.LoadParams.Prepend(
                    0,
                    1,
                    false
                )
            ))
        }
    }

    @Test
    fun testPagingLoaderSuccessPrependWithName(){
        runTest {
            PowerMockito.`when`(repository.getProducts(0)).thenReturn(pageInfoDto)
            productsPagingSource = ProductsPagingSource(repository, "productNameTest")

            val result = PagingSource.LoadResult.Page(
                data = pageInfo.productList,
                prevKey = null,
                nextKey = 1
            )

            MatcherAssert.assertThat(result, CoreMatchers.isA(PagingSource.LoadResult.Page::class.java))
            Assert.assertEquals(result, productsPagingSource.load(
                PagingSource.LoadParams.Prepend(
                    0,
                    1,
                    false
                )
            ))
        }
    }
}