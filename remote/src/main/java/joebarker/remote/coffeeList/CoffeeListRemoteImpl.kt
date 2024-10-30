package joebarker.remote.coffeeList

import joebarker.remote.BaseRemote
import joebarker.repository.boundary.remote.CoffeeListRemote
import joebarker.repository.response.CoffeeResponse
import joebarker.repository.response.EitherResponse
import joebarker.repository.response.ErrorResponse
import javax.inject.Inject

class CoffeeListRemoteImpl @Inject constructor(
    private val remoteCalls: CoffeeListRemoteCalls
) : BaseRemote(), CoffeeListRemote {

    override fun getCoffeeList(): EitherResponse<List<CoffeeResponse>?, ErrorResponse?> =
        tryRemote { remoteCalls.retrieveCoffees() }

}
