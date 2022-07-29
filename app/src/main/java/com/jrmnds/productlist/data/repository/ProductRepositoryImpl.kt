package com.jrmnds.productlist.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jrmnds.productlist.data.paging.ProductsPagingSource
import com.jrmnds.productlist.data.remote.ProductApiServices
import com.jrmnds.productlist.data.remote.dto.PagingInfoDTO
import com.jrmnds.productlist.data.remote.dto.ProductDTO
import com.jrmnds.productlist.domain.model.PageInfo
import com.jrmnds.productlist.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val services: ProductApiServices) : ProductRepository {

    override suspend fun getProducts(page: Int): PagingInfoDTO {
        return services.getAllProductsPerPage(page)
    }


}