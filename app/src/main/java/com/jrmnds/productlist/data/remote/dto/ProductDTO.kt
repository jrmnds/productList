package com.jrmnds.productlist.data.remote.dto

import android.os.Parcelable
import androidx.paging.PagingData
import com.jrmnds.productlist.domain.model.Product
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductDTO(
    val productId: Int,
    val productName: String,
    val reviewInformation: ReviewInformationDTO?,
    @Json(name = "USPs")
    val productLabels: List<String>,
    val availabilityState: Int,
    val salesPriceIncVat: Double,
    val listPriceIncVat: Double?,
    val listPriceExVat: Double?,
    val productImage: String,
    val coolbluesChoiceInformationTitle: String?,
    val promoIcon: PromoIconDTO?,
    val nextDayDelivery: Boolean
) : Parcelable {
    fun toProduct(): Product {
        return Product(
            productId,
            productName,
            reviewInformation?.toReviewInformation(),
            productLabels,
            salesPriceIncVat,
            listPriceIncVat,
            listPriceExVat,
            productImage,
            promoIcon?.toPromoIcon(),
            nextDayDelivery
        )
    }
}
