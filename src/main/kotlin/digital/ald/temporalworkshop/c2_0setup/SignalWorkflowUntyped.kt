package digital.ald.temporalworkshop.c2_0setup

import io.temporal.client.WorkflowClient
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.serviceclient.WorkflowServiceStubsOptions

fun main() {
    val options = WorkflowServiceStubsOptions.newBuilder().build()
    val service = WorkflowServiceStubs.newServiceStubs(options)
    val client = WorkflowClient.newInstance(service)
    val workflow = client.newUntypedWorkflowStub("workflowID")

//    workflow.signal("sendSignal")

    workflow.signal("stop")
}
