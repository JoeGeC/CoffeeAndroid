package joebarker.config

import android.content.Context
import joebarker.domain.useCase.GetCoffeeListUseCaseImpl
import joebarker.local.LocalProvider
import joebarker.remote.coffeeList.CoffeeListRemoteImpl
import joebarker.repository.CoffeeListRepositoryImpl

class Config(context: Context) {
    private val localProvider by lazy { LocalProvider(context) }

    private val coffeeListRemote by lazy { CoffeeListRemoteImpl() }

    private val coffeeListRepository by lazy {
        CoffeeListRepositoryImpl(localProvider.coffeeListLocal, coffeeListRemote)
    }

    val coffeeListUseCase by lazy { GetCoffeeListUseCaseImpl(coffeeListRepository) }
}