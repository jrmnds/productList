package com.jrmnds.productlist.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jrmnds.productlist.common.Constants.STARTING_KEY
import com.jrmnds.productlist.domain.model.Product
import com.jrmnds.productlist.domain.repository.ProductRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ProductsPagingSource @Inject constructor(private val repository: ProductRepository) : PagingSource<Int, Product>(){

    override fun getRefreshKey(state: PagingState<Int, Product>): Int = STARTING_KEY

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        val pageNumber = params.key?: STARTING_KEY

        return try {
            val response = repository.getProducts(pageNumber).toPageInfo()
            val productList = response.productList
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
        }
    }
}