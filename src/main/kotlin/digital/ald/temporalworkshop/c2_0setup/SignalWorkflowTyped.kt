package digital.ald.temporalworkshop.c2_0setup

import io.temporal.client.WorkflowClient
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.serviceclient.WorkflowServiceStubsOptions

fun main() {
    val options = WorkflowServiceStubsOptions.newBuilder().build()
    val service = WorkflowServiceStubs.newServiceStubs(options)
    val client = WorkflowClient.newInstance(service)
    val workflow = client.newWorkflowStub(MyWorkflow_c02_0::class.java, "workflowID")

    workflow.sendSignal()

    workflow.stopWorkflow()
}
