package digital.ald.temporalworkshop.c07_versioning

import io.temporal.activity.ActivityOptions
import io.temporal.internal.statemachines.UnsupportedVersion
import io.temporal.workflow.SignalMethod
import io.temporal.workflow.Workflow
import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod
import java.time.Duration

class MyWorkflowImpl : MyWorkflow {

    private var activityOptions: ActivityOptions = ActivityOptions.newBuilder()
        .setScheduleToCloseTimeout(Duration.ofMinutes(10))
        .build()

    private val activity = Workflow.newActivityStub(MyActivity::class.java, activityOptions)

    private var unblock = false

    override fun runWorkflow() {
        try {
            println("Start workflow")
            val vDU1122 = Workflow.getVersion("[DU-1122]", Workflow.DEFAULT_VERSION, 1)
            val vDU1123 = Workflow.getVersion("[DU-1123]", Workflow.DEFAULT_VERSION, 1)
            val vDU1124 = Workflow.getVersion("[DU-1124]", Workflow.DEFAULT_VERSION, 1)


            activity.doSomething()
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
