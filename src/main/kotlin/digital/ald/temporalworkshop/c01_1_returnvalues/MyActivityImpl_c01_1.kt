package digital.ald.temporalworkshop.c01_1_returnvalues

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod

class MyActivityImpl_c01_1: MyActivity_c01_1 {

    override fun loadUserData(userId: Long): String {
        println("Fetching data for user id $userId...")
        return "usernameX01"
    }

    override fun fetchAddressData(username: String): UserWithAddress {
        println("Fetching address data for $username")
        return UserWithAddress()
            .apply {
            name = username
            address = "Nedderfeld 95, 22529 Hamburg"
        }

    }

}

@ActivityInterface
interface MyActivity_c01_1 {

    @ActivityMethod
    fun loadUserData(userId: Long): String

    @ActivityMethod
    fun fetchAddressData(username: String): UserWithAddress
}