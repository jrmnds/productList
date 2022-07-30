package com.jrmnds.productlist.data.remote.dto

import android.os.Parcelable
import com.jrmnds.productlist.domain.model.ReviewSummary
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReviewSummaryDTO(
    val reviewAverage: Float?,
    val reviewCount: Int?
): Parcelable {
    fun toReviewSummary(): ReviewSummary {
        return ReviewSummary(
            reviewAverage,
            reviewCount
        )
    }
}