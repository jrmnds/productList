package com.jrmnds.productlist.data.remote.dto

import com.jrmnds.productlist.domain.model.ReviewInformation
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(ReviewSummaryDTO::class)
internal class ReviewInformationDTOTest {

    private lateinit var reviewInformationDTO: ReviewInformationDTO

    @Mock
    private lateinit var reviewSummaryDTO: ReviewSummaryDTO

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        reviewInformationDTO = ReviewInformationDTO(reviewSummaryDTO)
    }

    @Test
    fun testReviewInformationDtoToReviewInformation(){
        val reviewInformation = reviewInformationDTO.toReviewInformation()
        MatcherAssert.assertThat(reviewInformation, CoreMatchers.isA(ReviewInformation::class.java))
        Assert.assertNotNull(reviewInformation)
    }

    @Test
    fun getReviewInformationDtoReviewSummaryDto(){
        Assert.assertNotNull(reviewInformationDTO.reviewSummary)
    }
}