package com.jrmnds.productlist.data.remote

import com.jrmnds.productlist.data.remote.dto.PagingInfoDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApiServices {
    @GET("search")
    suspend fun getAllProductsPerPage(@Query("page") pageNumber: Int): PagingInfoDTO
}