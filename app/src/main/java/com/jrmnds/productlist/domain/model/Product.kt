package com.jrmnds.productlist.domain.model

import com.jrmnds.productlist.data.remote.dto.PromoIconDTO
import com.jrmnds.productlist.data.remote.dto.ReviewInformationDTO


data class Product(
    val productId: Int,
    val productName: String,
    val reviewInformation: ReviewInformation?,
    val productLabels: List<String>,
    val salesPriceIncVat: Double,
    val listPriceIncVat: Double?,
    val listPriceExVat: Double?,
    val productImage: String,
    val promoIcon: PromoIcon?,
    val nextDayDelivery: Boolean
)
