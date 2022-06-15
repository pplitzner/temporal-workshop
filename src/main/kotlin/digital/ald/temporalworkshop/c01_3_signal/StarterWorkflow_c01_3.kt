package digital.ald.temporalworkshop.c01_3_signal

import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowOptions
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.serviceclient.WorkflowServiceStubsOptions

fun main(args: Array<String>) {
    val options = WorkflowServiceStubsOptions.newBuilder().build()
    val service = WorkflowServiceStubs.newServiceStubs(options)
    val client = WorkflowClient.newInstance(service)
    val workflowOptions = WorkflowOptions.newBuilder()
        .setWorkflowId("workflowID")
        .setTaskQueue("c01_3")
        .build()
    val workflow = client.newWorkflowStub(MyWorkflow_c01_3::class.java, workflowOptions)

    workflow.runWorkflow()

}
