package joebarker.remote.coffees

import joebarker.remote.BaseRemote
import joebarker.repository.boundary.remote.CoffeeListRemote
import joebarker.repository.response.CoffeeListResponse
import joebarker.repository.response.EitherResponse
import joebarker.repository.response.ErrorResponse

class CoffeeListRemoteImpl(
    private val remoteCalls: CoffeeListRemoteCalls
) : BaseRemote(), CoffeeListRemote {

    override fun getCoffeeList(): EitherResponse<CoffeeListResponse?, ErrorResponse?> =
        tryRemote { remoteCalls.retrieveCoffees() }

}
