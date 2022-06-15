package digital.ald.temporalworkshop.c01_0_basic

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
        .setTaskQueue("myQueue")
        .build()
    val workflow = client.newWorkflowStub(MyWorkflow::class.java, workflowOptions)

    workflow.runWorkflow()

}
