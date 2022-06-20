package digital.ald.temporalworkshop.c06_0_determinism

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
        .setTaskQueue("c06_0")
        .build()
    val workflow = client.newWorkflowStub(MyWorkflow_c06_0::class.java, workflowOptions)

    WorkflowClient.execute(workflow::runWorkflow)

}
