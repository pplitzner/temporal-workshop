package digital.ald.temporalworkshop.c2_0setup

import io.temporal.activity.ActivityOptions
import io.temporal.workflow.SignalMethod
import io.temporal.workflow.Workflow
import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod
import org.slf4j.Logger
import java.time.Duration

class MyWorkflowImpl_c02_0: MyWorkflow_c02_0 {

    private var activityOptions: ActivityOptions = ActivityOptions.newBuilder()
        .setScheduleToCloseTimeout(Duration.ofMinutes(10))
        .build()
    
    private val log: Logger = Workflow.getLogger(this.javaClass)
    
    private val activity = Workflow.newActivityStub(MyActivity_c02_0::class.java, activityOptions)

    private var signalsReceived: Int = 0

    private var exit = false

    override fun runWorkflow() {
        log.info("Workflow started")

        while (true) {
            activity.doSomething()
            log.info("Current signal count: $signalsReceived")
            if (exit) {
                break
            }
        }

        log.info("Workflow finished")
    }

    override fun sendSignal() {
        log.info("Increase count")
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

