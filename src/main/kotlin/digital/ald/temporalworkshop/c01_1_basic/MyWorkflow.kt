package digital.ald.temporalworkshop.c01_1_basic

import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod

@WorkflowInterface
interface MyWorkflow {
    @WorkflowMethod
    fun runWorkflow()
}