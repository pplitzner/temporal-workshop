package digital.ald.temporalworkshop.c01_3_signal

import io.temporal.client.WorkflowClient
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.serviceclient.WorkflowServiceStubsOptions

fun main() {
    val options = WorkflowServiceStubsOptions.newBuilder().build()
    val service = WorkflowServiceStubs.newServiceStubs(options)
    val client = WorkflowClient.newInstance(service)
    val workflow = client.newWorkflowStub(MyWorkflow_c01_3::class.java, "workflowID")

    workflow.sendSignal()

    workflow.stopWorkflow()
}
