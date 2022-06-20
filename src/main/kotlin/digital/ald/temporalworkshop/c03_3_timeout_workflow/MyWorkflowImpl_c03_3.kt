package digital.ald.temporalworkshop.c03_3_timeout_workflow

import io.temporal.workflow.Workflow
import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod
import org.slf4j.Logger

class MyWorkflowImpl_c03_3: MyWorkflow_c03_3 {

    private val log: Logger = Workflow.getLogger(this.javaClass)

    override fun runWorkflow() {
        log.info("Workflow started")

        while (true){
            Workflow.sleep(10000)
            log.info("loop")
        }

        log.info("Workflow finished")
    }

}

@WorkflowInterface
interface MyWorkflow_c03_3 {

    @WorkflowMethod
    fun runWorkflow()

}

