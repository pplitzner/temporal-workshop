package digital.ald.temporalworkshop.c2_0setup

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod

class MyActivityImpl_c02_0: MyActivity_c02_0 {

    override fun doSomething() {
        Thread.sleep(5000)
    }

}

@ActivityInterface
interface MyActivity_c02_0 {

    @ActivityMethod
    fun doSomething()

}