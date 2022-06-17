package digital.ald.temporalworkshop.c2_0setup

import io.temporal.activity.ActivityOptions
import io.temporal.workflow.SignalMethod
import io.temporal.workflow.Workflow
import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod
import java.time.Duration

class MyWorkflowImpl_c02_0: MyWorkflow_c02_0 {

    private var activityOptions: ActivityOptions = ActivityOptions.newBuilder()
        .setScheduleToCloseTimeout(Duration.ofMinutes(10))
        .build()
    
    private val activity = Workflow.newActivityStub(MyActivity_c02_0::class.java, activityOptions)

    private var signalsReceived: Int = 0

    private var exit = false

    override fun runWorkflow() {
        println("Workflow started")

        while (true) {
            activity.doSomething()
            println("Current signal count: $signalsReceived")
            if (exit) {
                break
            }
        }

        println("Workflow finished")
    }

    override fun sendSignal() {
        println("Increase count")
        signalsReceived = signalsReceived.inc()
    }

    override fun stopWorkflow() {
        exit = true
    }

}

@WorkflowInterface
interface MyWorkflow_c02_0 {

    @WorkflowMethod
    fun runWorkflow()

    @SignalMethod
    fun sendSignal()

    @SignalMethod(name = "stop")
    fun stopWorkflow()

}

