package digital.ald.temporalworkshop.basic

import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod

@WorkflowInterface
interface MyWorkflow {
    @WorkflowMethod
    fun runWorkflow()
}