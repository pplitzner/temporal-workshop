package digital.ald.temporalworkshop.c01_3_signal

import io.temporal.activity.ActivityOptions
import io.temporal.workflow.QueryMethod
import io.temporal.workflow.SignalMethod
import io.temporal.workflow.Workflow
import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod
import java.time.Duration

class MyWorkflowImpl_c01_3: MyWorkflow_c01_3 {

    var activityOptions: ActivityOptions = ActivityOptions.newBuilder()
        .setScheduleToCloseTimeout(Duration.ofMinutes(10))
        .build()
    
    private val activity = Workflow.newActivityStub(MyActivity_c01_3::class.java, activityOptions)

    var activitiesExecuted: Int = 0
    
    override fun runWorkflow() {
        println("Workflow started")
        activity.doSomething()
        activitiesExecuted = activitiesExecuted.inc()

        activity.doAnotherThing()
        activitiesExecuted = activitiesExecuted.inc()

        println("Activities executed: $activitiesExecuted")
    }

    override fun activitiesExecuted() = activitiesExecuted

    override fun increaseActivitiesExecuted() {
        println("Increase count")
        activitiesExecuted = activitiesExecuted.inc()
    }
}

@WorkflowInterface
interface MyWorkflow_c01_3 {
    @WorkflowMethod
    fun runWorkflow()

    @QueryMethod
    fun activitiesExecuted() : Int

    @SignalMethod
    fun increaseActivitiesExecuted()
}

