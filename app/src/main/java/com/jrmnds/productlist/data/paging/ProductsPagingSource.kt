package com.jrmnds.productlist.data.paging

import android.util.Log
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jrmnds.productlist.data.remote.ProductApiServices
import com.jrmnds.productlist.data.remote.dto.ProductDTO
import com.jrmnds.productlist.common.Constants.STARTING_KEY
import com.jrmnds.productlist.domain.model.Product
import com.jrmnds.productlist.domain.repository.ProductRepository
import javax.inject.Inject

class ProductsPagingSource @Inject constructor(private val repository: ProductRepository) : PagingSource<Int, Product>(){

    override fun getRefreshKey(state: PagingState<Int, Product>): Int = STARTING_KEY

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        val pageNumber = params.key?: STARTING_KEY

        return try {
            val response = repository.getProducts(pageNumber).toPageInfo()
            val productList = response.productList
            val pageCount = response.pageCount
            Log.e("DATA", productList.toString())

            LoadResult.Page(
                data = productList.orEmpty(),
                prevKey = null,
                nextKey = if (pageNumber < pageCount) pageNumber.plus(1) else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}