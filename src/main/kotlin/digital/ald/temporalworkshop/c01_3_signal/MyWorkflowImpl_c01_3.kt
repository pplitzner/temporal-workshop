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

    var signalsReceived: Int = 0
    
    override fun runWorkflow() {
        println("Workflow started")
        for (i in 1..10) {
            activity.doSomething()
            println("Current signal count: $signalsReceived")
        }

        println("Signal received during execution: $signalsReceived")
    }

    override fun signalsReceived() = signalsReceived

    override fun sendSignal() {
        println("Increase count")
        signalsReceived = signalsReceived.inc()
    }
}

@WorkflowInterface
interface MyWorkflow_c01_3 {
    @WorkflowMethod
    fun runWorkflow()

    @QueryMethod
    fun signalsReceived() : Int

    @SignalMethod
    fun sendSignal()
}

