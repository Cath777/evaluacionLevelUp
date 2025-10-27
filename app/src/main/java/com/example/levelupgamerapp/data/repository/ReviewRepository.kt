package com.example.levelupgamerapp.data.repository

import com.example.levelupgamerapp.data.local.dao.ReviewDao
import com.example.levelupgamerapp.data.local.entity.ReviewEntity

class ReviewRepository(private val reviewDao: ReviewDao) {

    suspend fun getReviewsForProduct(productId: Int): List<ReviewEntity> {
        return reviewDao.getReviewsForProduct(productId)
    }

    suspend fun insertReview(review: ReviewEntity) {
        reviewDao.insertReview(review)
    }
}
