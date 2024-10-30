package joebarker.remote

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import joebarker.remote.coffeeList.CoffeeListRemoteCalls
import joebarker.remote.coffeeList.CoffeeListRemoteImpl
import joebarker.remote.coffeeReview.CoffeeReviewRemoteCalls
import joebarker.remote.coffeeReview.CoffeeReviewRemoteImpl
import joebarker.repository.boundary.remote.CoffeeListRemote
import joebarker.repository.boundary.remote.CoffeeReviewRemote
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object HiltRemoteModule {
    @Provides
    fun provideCoffeeListRemote(impl: CoffeeListRemoteImpl): CoffeeListRemote = impl

    @Provides
    fun provideCoffeeReviewRemote(impl: CoffeeReviewRemoteImpl): CoffeeReviewRemote = impl

    private const val BASE_URL = "https://api.sampleapis.com/"

    @Provides
    fun provideRetroFit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideCoffeeListRemoteCalls(retrofit: Retrofit) = retrofit.create(CoffeeListRemoteCalls::class.java)

    @Provides
    fun provideCoffeeReviewRemoteCalls(retrofit: Retrofit) = retrofit.create(CoffeeReviewRemoteCalls::class.java)
}