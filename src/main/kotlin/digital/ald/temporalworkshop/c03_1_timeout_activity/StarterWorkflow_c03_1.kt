package digital.ald.temporalworkshop.c03_1_timeout_activity

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
        .setTaskQueue("c03_1")
        .build()
    val workflow = client.newWorkflowStub(MyWorkflow_c03_1::class.java, workflowOptions)

    WorkflowClient.execute(workflow::runWorkflow)

}
