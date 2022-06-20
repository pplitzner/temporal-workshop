package digital.ald.temporalworkshop.c06_0_determinism

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod

class MyActivityImpl_c06_0: MyActivity_c06_0 {

    override fun doSomething() {
        Thread.sleep(10000)
    }

}

@ActivityInterface
interface MyActivity_c06_0 {

    @ActivityMethod
    fun doSomething()

}