package com.jrmnds.productlist.domain.model


data class PageInfo(
    val productList: List<Product>,
    val pageCount: Int
)
