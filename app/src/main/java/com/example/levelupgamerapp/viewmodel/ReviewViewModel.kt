package com.example.levelupgamerapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelupgamerapp.data.local.entity.ReviewEntity
import com.example.levelupgamerapp.data.repository.ReviewRepository
import kotlinx.coroutines.launch
import java.time.LocalDate

class ReviewViewModel(private val repository: ReviewRepository, private val productId: Int) : ViewModel() {

    var reviews = mutableStateOf<List<ReviewEntity>>(emptyList())
        private set

    init {
        loadReviews()
    }

    private fun loadReviews() {
        viewModelScope.launch {
            reviews.value = repository.getReviewsForProduct(productId)
        }
    }

    fun addReview(rating: Int, comment: String, userId: Int) {
        viewModelScope.launch {
            val newReview = ReviewEntity(
                productId = productId,
                userId = userId,
                rating = rating,
                comment = comment,
                createdAt = LocalDate.now().toString()
            )
            repository.insertReview(newReview)

            loadReviews()
        }
    }
}
