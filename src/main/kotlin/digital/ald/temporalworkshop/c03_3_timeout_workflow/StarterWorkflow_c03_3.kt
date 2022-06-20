package digital.ald.temporalworkshop.c03_3_timeout_workflow

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
        .setTaskQueue("c03_3")
//        .setWorkflowRunTimeout(Duration.ofSeconds(2))
        .build()
    val workflow = client.newWorkflowStub(MyWorkflow_c03_3::class.java, workflowOptions)

    WorkflowClient.execute(workflow::runWorkflow)

}
