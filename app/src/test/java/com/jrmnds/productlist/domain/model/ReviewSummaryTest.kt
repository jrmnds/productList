package com.jrmnds.productlist.domain.model

import org.junit.Assert
import org.junit.Before
import org.junit.Test

internal class ReviewSummaryTest{

    private lateinit var reviewSummary: ReviewSummary

    @Before
    fun setUp(){
        reviewSummary = ReviewSummary(2.5F, 5)
    }

    @Test
    fun getReviewAverageTest(){
        Assert.assertNotNull(reviewSummary.reviewAverage)
    }

    @Test
    fun getReviewCountTest(){
        Assert.assertNotNull(reviewSummary.reviewCount)
    }
}