package digital.ald.temporalworkshop.versioning

import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowOptions
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.serviceclient.WorkflowServiceStubsOptions
import kotlin.random.Random

class StarterWorkflow

fun main(args: Array<String>) {
    val options = WorkflowServiceStubsOptions.newBuilder().build()
    val service = WorkflowServiceStubs.newServiceStubs(options)
    val client = WorkflowClient.newInstance(service)
    val workflowOptions = WorkflowOptions.newBuilder()
        .setWorkflowId(Random.nextInt(0,100).toString())
        .setTaskQueue("myQueue")
        .build()
    val workflow = client.newWorkflowStub(MyWorkflow::class.java, workflowOptions)

    WorkflowClient.execute(workflow::runWorkflow)

}
