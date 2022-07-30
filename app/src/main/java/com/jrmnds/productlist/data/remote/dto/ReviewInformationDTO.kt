package com.jrmnds.productlist.data.remote.dto

import android.os.Parcelable
import com.jrmnds.productlist.domain.model.ReviewInformation
import com.jrmnds.productlist.domain.model.ReviewSummary
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReviewInformationDTO(
    val reviewSummary: ReviewSummaryDTO?,
): Parcelable {

    fun toReviewInformation(): ReviewInformation{
        return ReviewInformation(
            reviewSummary = reviewSummary?.toReviewSummary()
        )
    }

}