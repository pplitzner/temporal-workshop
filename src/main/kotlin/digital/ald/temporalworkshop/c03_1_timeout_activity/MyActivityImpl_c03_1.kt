package digital.ald.temporalworkshop.c03_1_timeout_activity

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod

class MyActivityImpl_c03_1: MyActivity_c03_1 {

    override fun callLongRunningOperation() {
        Thread.sleep(15000)
    }

}

@ActivityInterface
interface MyActivity_c03_1 {

    @ActivityMethod
    fun callLongRunningOperation()

}