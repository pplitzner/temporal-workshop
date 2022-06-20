package digital.ald.temporalworkshop.c03_0_exceptions_activity

import io.temporal.activity.ActivityOptions
import io.temporal.common.RetryOptions
import io.temporal.workflow.Workflow
import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod
import org.slf4j.Logger
import java.time.Duration

class MyWorkflowImpl_c03_0: MyWorkflow_c03_0 {

    var activityOptions: ActivityOptions = ActivityOptions.newBuilder()
        .setScheduleToCloseTimeout(Duration.ofSeconds(10)) // fails workflow
        .setStartToCloseTimeout(Duration.ofSeconds(10)) // infinite retry
        .setRetryOptions(RetryOptions.newBuilder().setBackoffCoefficient(1.0).build())
        .build()
    
    private val activity = Workflow.newActivityStub(MyActivity_c03_0::class.java, activityOptions)

    private val log: Logger = Workflow.getLogger(this.javaClass)

    override fun runWorkflow() {
        log.info("Workflow started")

        activity.callUnstableExternalApi()

//        activity.callBrokenExternalApi()

        log.info("Workflow finished")
    }

}

@WorkflowInterface
interface MyWorkflow_c03_0 {

    @WorkflowMethod
    fun runWorkflow()

}

