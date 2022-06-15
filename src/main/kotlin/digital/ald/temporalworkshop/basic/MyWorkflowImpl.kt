package digital.ald.temporalworkshop.basic

import io.temporal.activity.ActivityOptions
import io.temporal.workflow.Workflow
import java.time.Duration

class MyWorkflowImpl: MyWorkflow {

    var activityOptions: ActivityOptions = ActivityOptions.newBuilder()
        .setScheduleToCloseTimeout(Duration.ofMinutes(10))
        .build()
    
    private val activity = Workflow.newActivityStub(MyActivity::class.java, activityOptions)
    
    override fun runWorkflow() {
        println("Workflow started")
        activity.doSomething()
        activity.doAnotherThing()

    }
}

