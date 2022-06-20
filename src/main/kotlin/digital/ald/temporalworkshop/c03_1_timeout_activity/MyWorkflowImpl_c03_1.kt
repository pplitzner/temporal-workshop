package digital.ald.temporalworkshop.c03_1_timeout_activity

import io.temporal.activity.ActivityOptions
import io.temporal.common.RetryOptions
import io.temporal.workflow.Workflow
import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod
import org.slf4j.Logger
import java.time.Duration

class MyWorkflowImpl_c03_1: MyWorkflow_c03_1 {

    var activityOptions: ActivityOptions = ActivityOptions.newBuilder()
        .setStartToCloseTimeout(Duration.ofSeconds(12))
//        .setScheduleToCloseTimeout(Duration.ofSeconds(10))
        .setRetryOptions(RetryOptions.newBuilder().setBackoffCoefficient(1.0).build())
        .build()
    
    private val activity = Workflow.newActivityStub(MyActivity_c03_1::class.java, activityOptions)

    private val log: Logger = Workflow.getLogger(this.javaClass)

    override fun runWorkflow() {
        log.info("Workflow started")

        activity.callLongRunningOperation()

        log.info("Workflow finished")
    }

}

@WorkflowInterface
interface MyWorkflow_c03_1 {

    @WorkflowMethod
    fun runWorkflow()

}

