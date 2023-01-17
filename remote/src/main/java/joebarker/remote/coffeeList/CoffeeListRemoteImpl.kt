package joebarker.remote.coffeeList

import joebarker.remote.BaseRemote
import joebarker.repository.boundary.remote.CoffeeListRemote
import joebarker.repository.response.CoffeeResponse
import joebarker.repository.response.EitherResponse
import joebarker.repository.response.ErrorResponse

class CoffeeListRemoteImpl(
    private val remoteCalls: CoffeeListRemoteCalls = retrofit.create(CoffeeListRemoteCalls::class.java)
) : BaseRemote(), CoffeeListRemote {

    override fun getCoffeeList(): EitherResponse<List<CoffeeResponse>?, ErrorResponse?> =
        tryRemote { remoteCalls.retrieveCoffees() }

}
