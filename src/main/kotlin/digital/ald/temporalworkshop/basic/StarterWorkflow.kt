package digital.ald.temporalworkshop.basic

import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowOptions
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.serviceclient.WorkflowServiceStubsOptions

class StarterWorkflow

fun main(args: Array<String>) {
    val options = WorkflowServiceStubsOptions.newBuilder().build()
    val service = WorkflowServiceStubs.newServiceStubs(options)
    val client = WorkflowClient.newInstance(service)
    val workflowOptions = WorkflowOptions.newBuilder()
        .setWorkflowId("workflowID")
        .setTaskQueue("myQueue")
        .build()
    val workflow = client.newWorkflowStub(MyWorkflow::class.java, workflowOptions)

//    workflow.runWorkflow()
    WorkflowClient.execute(workflow::runWorkflow)

}
