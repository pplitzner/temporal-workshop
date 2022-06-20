package digital.ald.temporalworkshop.c01_2_query

import io.temporal.activity.ActivityOptions
import io.temporal.workflow.QueryMethod
import io.temporal.workflow.Workflow
import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod
import java.time.Duration

class MyWorkflowImpl_c01_2: MyWorkflow_c01_2 {

    var activityOptions: ActivityOptions = ActivityOptions.newBuilder()
        .setStartToCloseTimeout(Duration.ofMinutes(10))
        .build()
    
    private val activity = Workflow.newActivityStub(MyActivity_c01_2::class.java, activityOptions)

    var activitiesExecuted: Int = 0
    
    override fun runWorkflow() {
        activity.doSomething()
        activitiesExecuted = activitiesExecuted.inc()

        activity.doAnotherThing()
        activitiesExecuted = activitiesExecuted.inc()
    }

    override fun activitiesExecuted() = activitiesExecuted
}

@WorkflowInterface
interface MyWorkflow_c01_2 {
    @WorkflowMethod
    fun runWorkflow()

    @QueryMethod
    fun activitiesExecuted() : Int
}

