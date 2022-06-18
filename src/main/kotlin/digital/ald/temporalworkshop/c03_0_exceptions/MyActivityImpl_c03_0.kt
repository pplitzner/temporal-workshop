package digital.ald.temporalworkshop.c03_0_exceptions

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod

class MyActivityImpl_c03_0: MyActivity_c03_0 {

    override fun callUnstableExternalApi() {
        Thread.sleep(2000)
        throw IllegalStateException("Unable to call external API")
    }

}

@ActivityInterface
interface MyActivity_c03_0 {

    @ActivityMethod
    fun callUnstableExternalApi()

}