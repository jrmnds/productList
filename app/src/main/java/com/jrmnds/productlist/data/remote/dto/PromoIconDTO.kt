package com.jrmnds.productlist.data.remote.dto

import android.os.Parcelable
import com.jrmnds.productlist.domain.model.PromoIcon
import kotlinx.parcelize.Parcelize

@Parcelize
data class PromoIconDTO(
    val text: String?,
    val type: String?
): Parcelable {
    fun toPromoIcon(): PromoIcon{
        return PromoIcon(
            text,
            type
        )
    }
}
