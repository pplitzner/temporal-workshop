package digital.ald.temporalworkshop.temporaloutage

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod
import io.temporal.activity.ActivityOptions
import io.temporal.workflow.Workflow
import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod
import java.time.Duration

@ActivityInterface
interface TestActivity {
    @ActivityMethod
    fun go(s:String)
}

@WorkflowInterface
interface TestWorkflow {
    @WorkflowMethod
    fun work()
}

class ActivityImpl : TestActivity {
    override fun go(s: String) {
        println("Invoking activity with $s")
        println("Wait...")
        Thread.sleep(5000)
        println("Call 1")
        println("Call 2")
        println("Activity DONE")
    }
}

class WorkflowImpl : TestWorkflow {
    override fun work() {
        val activity = Workflow.newActivityStub(TestActivity::class.java, ActivityOptions.newBuilder().apply {
            setStartToCloseTimeout(
                Duration.ofSeconds(35)
            )
        }.build())
        activity.go("bla")
        Workflow.sleep(5000)
        activity.go("bla")
    }
}