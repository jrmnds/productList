package com.jrmnds.productlist.domain.repository



import com.jrmnds.productlist.data.remote.dto.PagingInfoDTO

interface ProductRepository {
    suspend fun getProducts(page: Int): PagingInfoDTO
}