package digital.ald.temporalworkshop.versioning

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod

class MyActivityImpl: MyActivity {

    override fun doSomething() {
        println("Activity did something")
    }

    override fun doAnotherThing() {
        println("Activity did another thing")
    }

    override fun updateStatus() {
        println("Status updated")
    }

    override fun featureDU1122() {
        println("Feature DU-1122")
    }

    override fun featureDU1122_hotfix() {
        println("Feature DU-1122 HOTFIX")
    }
}

@ActivityInterface
interface MyActivity {

    @ActivityMethod
    fun doSomething()

    @ActivityMethod
    fun doAnotherThing()

    @ActivityMethod
    fun updateStatus()

    @ActivityMethod
    fun featureDU1122()

    @ActivityMethod
    fun featureDU1122_hotfix()
}