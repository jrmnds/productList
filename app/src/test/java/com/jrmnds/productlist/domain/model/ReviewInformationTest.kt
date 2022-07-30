package com.jrmnds.productlist.domain.model

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(ReviewSummary::class)
internal class ReviewInformationTest {

    private lateinit var reviewInformation: ReviewInformation

    @Mock
    private lateinit var reviewSummary: ReviewSummary

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        reviewInformation = ReviewInformation(reviewSummary)
    }

    @Test
    fun getReviewSummaryTest(){
        Assert.assertNotNull(reviewInformation.reviewSummary)
    }

}