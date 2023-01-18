package joebarker.coffee

import joebarker.coffee.viewModel.CoffeeReviewViewModel
import joebarker.domain.boundary.presentation.CoffeeReviewUseCase
import joebarker.domain.entity.CoffeeReview
import joebarker.domain.entity.Either
import joebarker.domain.entity.ErrorEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyNoInteractions

class CoffeeReviewViewModelShould {
    private val review = CoffeeReview(1, "Joe", "2023-01-17", "Description", 10)
    private val emptyReview = CoffeeReview(0, "", "", "", 0)

    @Test
    fun `Submit review to use case`(){
        val useCase = mock<CoffeeReviewUseCase> {
            onBlocking { submitReview(review) }.doReturn(Either.Success(""))
        }
        val viewModel = CoffeeReviewViewModel(useCase)

        runBlocking {
            viewModel.submitReview(review, Dispatchers.Unconfined)
        }

        assertTrue(viewModel.successfulSubmit.value)
        assertFalse(viewModel.isLoading.value)
        assertFalse(viewModel.error.value)
        assertFalse(viewModel.nameError.value)
    }

    @Test
    fun `Show error when something goes wrong `(){
        val useCase = mock<CoffeeReviewUseCase> {
            onBlocking { submitReview(review) }.doReturn(Either.Failure(ErrorEntity("error")))
        }
        val viewModel = CoffeeReviewViewModel(useCase)

        runBlocking {
            viewModel.submitReview(review, Dispatchers.Unconfined)
        }

        assertFalse(viewModel.successfulSubmit.value)
        assertFalse(viewModel.isLoading.value)
        assertTrue(viewModel.error.value)
    }

    @Test
    fun `Flag if name wasn't filled in`(){
        val useCase = mock<CoffeeReviewUseCase>()
        val viewModel = CoffeeReviewViewModel(useCase)

        viewModel.submitReview(emptyReview)

        assertTrue(viewModel.nameError.value)
        verifyNoInteractions(useCase)
    }

    @Test
    fun `Flag if rating wasn't filled in`(){
        val useCase = mock<CoffeeReviewUseCase>()
        val viewModel = CoffeeReviewViewModel(useCase)

        viewModel.submitReview(emptyReview)

        assertTrue(viewModel.ratingError.value)
        verifyNoInteractions(useCase)
    }

}