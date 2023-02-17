package joebarker.remote.coffeeList

import joebarker.remote.BaseRemote
import joebarker.repository.boundary.remote.CoffeeListRemote
import joebarker.repository.response.CoffeeResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CoffeeListRemoteImpl(
    private val remoteCalls: CoffeeListRemoteCalls = retrofit.create(CoffeeListRemoteCalls::class.java)
) : BaseRemote(), CoffeeListRemote {

    override fun getCoffeeList(): Flow<List<CoffeeResponse>> {
        return flow {
            emit(remoteCalls.retrieveCoffees().execute().body() ?: listOf())
        }
    }
}
