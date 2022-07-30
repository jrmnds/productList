package com.jrmnds.productlist.data.paging


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jrmnds.productlist.common.Constants.STARTING_KEY
import com.jrmnds.productlist.domain.model.PageInfo

import com.jrmnds.productlist.domain.model.Product
import com.jrmnds.productlist.domain.repository.ProductRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ProductsPagingSource @Inject constructor(private val repository: ProductRepository,
                                               private val productName: String?) : PagingSource<Int, Product>(){

    override fun getRefreshKey(state: PagingState<Int, Product>): Int = STARTING_KEY

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        val pageNumber = params.key?: STARTING_KEY

        return try {
            val response = repository.getProducts(pageNumber).toPageInfo()
            var productList = response.productList
            if(productName != null){
               productList = productList.filter { product ->
                    product.productName.contains(productName, ignoreCase = true)
                }
            }
            val pageCount = response.pageCount
            LoadResult.Page(
                data = productList,
                prevKey = null,
                nextKey = if (pageNumber < pageCount) pageNumber.plus(1) else null
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException){
            LoadResult.Error(exception)
        } catch (exception: NullPointerException){
            LoadResult.Error(exception)
        }
    }
}