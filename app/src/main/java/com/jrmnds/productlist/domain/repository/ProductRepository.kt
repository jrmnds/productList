package com.jrmnds.productlist.domain.repository


import androidx.paging.PagingData
import com.jrmnds.productlist.data.remote.dto.PagingInfoDTO
import com.jrmnds.productlist.data.remote.dto.ProductDTO
import com.jrmnds.productlist.domain.model.PageInfo
import com.jrmnds.productlist.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getProducts(page: Int): PagingInfoDTO

}