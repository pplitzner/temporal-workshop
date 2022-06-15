package digital.ald.temporalworkshop.c01_3_signal

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod

class MyActivityImpl_c01_3: MyActivity_c01_3 {

    override fun doSomething() {
        Thread.sleep(20000)
        println("Activity did something")
    }

    override fun doAnotherThing() {
        println("Activity did another thing")
    }

}

@ActivityInterface
interface MyActivity_c01_3 {

    @ActivityMethod
    fun doSomething()

    @ActivityMethod
    fun doAnotherThing()
}