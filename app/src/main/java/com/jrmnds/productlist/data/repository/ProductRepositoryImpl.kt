package com.jrmnds.productlist.data.repository


import com.jrmnds.productlist.data.remote.ProductApiServices
import com.jrmnds.productlist.data.remote.dto.PagingInfoDTO
import com.jrmnds.productlist.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val services: ProductApiServices) : ProductRepository {

    override suspend fun getProducts(page: Int): PagingInfoDTO {
        return services.getAllProductsPerPage(page)
    }
}