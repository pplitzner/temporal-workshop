package digital.ald.temporalworkshop.c06_0_determinism

import io.temporal.activity.ActivityOptions
import io.temporal.workflow.SignalMethod
import io.temporal.workflow.Workflow
import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod
import org.slf4j.Logger
import java.time.Duration

class MyWorkflowImpl_c06_0: MyWorkflow_c06_0 {

    private var activityOptions: ActivityOptions = ActivityOptions.newBuilder()
        .setStartToCloseTimeout(Duration.ofMinutes(10))
        .build()
    
    private val log: Logger = Workflow.getLogger(this.javaClass)
    
    private val activity = Workflow.newActivityStub(MyActivity_c06_0::class.java, activityOptions)

    private var exit = false

    override fun runWorkflow() {
        log.info("Workflow started")
        println("System log: wf started")

        while (true) {
//            Thread.sleep(10000)
            activity.doSomething()
//            val rndInt = Random.nextInt()
//            if (exit || rndInt%2==0) {
            if (exit) {
                break
            }
        }

        log.info("Workflow finished")
    }

    override fun stopWorkflow() {
        exit = true
    }

}

@WorkflowInterface
interface MyWorkflow_c06_0 {

    @WorkflowMethod
    fun runWorkflow()

    @SignalMethod(name = "stop")
    fun stopWorkflow()

}

