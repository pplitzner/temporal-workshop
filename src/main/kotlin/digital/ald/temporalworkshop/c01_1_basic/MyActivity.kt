package digital.ald.temporalworkshop.c01_1_basic

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod

@ActivityInterface
interface MyActivity {

    @ActivityMethod
    fun doSomething()

    @ActivityMethod
    fun doAnotherThing()
}