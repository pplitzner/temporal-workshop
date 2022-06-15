package digital.ald.temporalworkshop.c01_2_query

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod

class MyActivityImpl_c01_2: MyActivity_c01_2 {

    override fun doSomething() {
        println("Activity did something")
    }

    override fun doAnotherThing() {
        println("Activity did another thing")
    }

}

@ActivityInterface
interface MyActivity_c01_2 {

    @ActivityMethod
    fun doSomething()

    @ActivityMethod
    fun doAnotherThing()
}