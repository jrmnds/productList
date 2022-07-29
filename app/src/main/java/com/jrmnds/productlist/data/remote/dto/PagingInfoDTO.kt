package com.jrmnds.productlist.data.remote.dto

import android.os.Parcelable
import com.jrmnds.productlist.data.remote.dto.ProductDTO
import com.jrmnds.productlist.domain.model.PageInfo
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class PagingInfoDTO(
    @Json(name = "products")
    val productList: List<ProductDTO>,
    val currentPage: Int,
    val pageSize: Int,
    val totalResults: Int,
    val pageCount: Int
) : Parcelable {
    fun toPageInfo(): PageInfo {
        return PageInfo(
            productList.map {
                it.toProduct()
            },
            pageCount
        )
    }
}