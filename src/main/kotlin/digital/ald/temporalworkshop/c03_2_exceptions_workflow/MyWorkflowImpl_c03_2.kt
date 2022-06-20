package digital.ald.temporalworkshop.c03_2_exceptions_workflow

import io.temporal.workflow.Workflow
import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod
import org.slf4j.Logger

class MyWorkflowImpl_c03_2: MyWorkflow_c03_2 {

    private val log: Logger = Workflow.getLogger(this.javaClass)

    override fun runWorkflow() {
        log.info("Workflow started")

        throw RuntimeException("Workflow crash!")

        log.info("Workflow finished")
    }

}

@WorkflowInterface
interface MyWorkflow_c03_2 {

    @WorkflowMethod
    fun runWorkflow()

}

