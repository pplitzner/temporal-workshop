package digital.ald.temporalworkshop.c01_1_returnvalues

import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowOptions
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.serviceclient.WorkflowServiceStubsOptions

fun main() {
    val options = WorkflowServiceStubsOptions.newBuilder().build()
    val service = WorkflowServiceStubs.newServiceStubs(options)
    val client = WorkflowClient.newInstance(service)
    val workflowOptions = WorkflowOptions.newBuilder()
        .setWorkflowId("workflowID")
        .setTaskQueue("c01_1")
        .build()
    val workflow = client.newWorkflowStub(MyWorkflow_c01_1::class.java, workflowOptions)

    workflow.runWorkflow(123L)

}
