package digital.ald.temporalworkshop.c07_versioning

import io.temporal.activity.ActivityOptions
import io.temporal.internal.statemachines.UnsupportedVersion
import io.temporal.workflow.SignalMethod
import io.temporal.workflow.Workflow
import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod
import org.slf4j.Logger
import java.time.Duration

class MyWorkflowImpl : MyWorkflow {

    private var activityOptions: ActivityOptions = ActivityOptions.newBuilder()
        .setStartToCloseTimeout(Duration.ofMinutes(10))
        .build()

    private val log: Logger = Workflow.getLogger(this.javaClass)

    private val activity = Workflow.newActivityStub(MyActivity::class.java, activityOptions)

    private var unblock = false

    override fun runWorkflow() {
        try {
            log.info("Start workflow")

            activity.doSomething()

            Workflow.await { unblock }

            activity.doAnotherThing()

        } catch (ex: UnsupportedVersion) {
            println(ex)
        } catch (ex: Throwable) {
            println(ex)
        }
    }

    override fun unblock() {
        unblock = true
    }
}

@WorkflowInterface
interface MyWorkflow {
    @WorkflowMethod
    fun runWorkflow()

    @SignalMethod
    fun unblock()
}
