package joebarker.remote.coffeeList

import joebarker.remote.BaseRemote
import joebarker.repository.boundary.remote.CoffeeListRemote
import joebarker.repository.response.CoffeeResponse
import joebarker.repository.response.EitherResponse
import joebarker.repository.response.ErrorResponse
import kotlinx.coroutines.flow.Flow

class CoffeeListRemoteImpl(
    private val remoteCalls: CoffeeListRemoteCalls = retrofit.create(CoffeeListRemoteCalls::class.java)
) : BaseRemote(), CoffeeListRemote {

    override fun getCoffeeList(): Flow<EitherResponse<List<CoffeeResponse>, ErrorResponse>> {
        return tryRemoteFlow { remoteCalls.retrieveCoffees() }
    }
}
