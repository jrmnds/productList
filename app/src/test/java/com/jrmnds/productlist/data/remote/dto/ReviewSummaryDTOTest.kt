package com.jrmnds.productlist.data.remote.dto

import com.jrmnds.productlist.domain.model.ReviewSummary
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert
import org.junit.Before
import org.junit.Test

internal class ReviewSummaryDTOTest {

    private lateinit var reviewSummaryDTO: ReviewSummaryDTO

    @Before
    fun setUp() {
        reviewSummaryDTO = ReviewSummaryDTO(2.2F, 3)
    }

    @Test
    fun getReviewSummaryDtoToReviewSummary(){
        val reviewSummary = reviewSummaryDTO.toReviewSummary()
        MatcherAssert.assertThat(reviewSummary, CoreMatchers.isA(ReviewSummary::class.java))
    }

    @Test
    fun getReviewSummaryDtoReviewAverage(){
        Assert.assertNotNull(reviewSummaryDTO.reviewAverage)
    }

    @Test
    fun getReviewSummaryDtoReviewCount(){
        Assert.assertNotNull(reviewSummaryDTO.reviewCount)
    }
}