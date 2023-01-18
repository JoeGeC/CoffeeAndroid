package joebarker.config

import android.content.Context
import joebarker.domain.useCase.GetCoffeeListUseCaseImpl
import joebarker.domain.useCase.ReviewCoffeeUseCaseImpl
import joebarker.local.LocalProvider
import joebarker.remote.coffeeList.CoffeeListRemoteImpl
import joebarker.remote.coffeeReview.CoffeeReviewRemoteImpl
import joebarker.repository.CoffeeListRepositoryImpl
import joebarker.repository.CoffeeReviewRepositoryImpl

class Config(context: Context) {
    private val localProvider by lazy { LocalProvider(context) }

    private val coffeeListRemote by lazy { CoffeeListRemoteImpl() }
    private val coffeeReviewRemote by lazy { CoffeeReviewRemoteImpl() }

    private val coffeeListRepository by lazy {
        CoffeeListRepositoryImpl(localProvider.coffeeListLocal, coffeeListRemote)
    }

    private val coffeeReviewRepository by lazy {
        CoffeeReviewRepositoryImpl(coffeeReviewRemote)
    }

    val coffeeListUseCase by lazy { GetCoffeeListUseCaseImpl(coffeeListRepository) }
    val coffeeReviewUseCase by lazy { ReviewCoffeeUseCaseImpl(coffeeReviewRepository) }
}