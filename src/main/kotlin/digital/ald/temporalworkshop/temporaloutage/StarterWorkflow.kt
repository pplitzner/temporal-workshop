package digital.ald.temporalworkshop.temporaloutage

import digital.ald.temporalworkshop.example.api.SpartakiadeWorkflow
import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowOptions
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.serviceclient.WorkflowServiceStubsOptions


fun main() {
    val service = WorkflowServiceStubs.newLocalServiceStubs()
    val client = WorkflowClient.newInstance(service)
    val workflowOptions = WorkflowOptions.newBuilder()
        .setWorkflowId("workflowID")
        .setTaskQueue("myQueue")
        .build()
    val workflow = client.newWorkflowStub(TestWorkflow::class.java, workflowOptions)

    workflow.work()
}
