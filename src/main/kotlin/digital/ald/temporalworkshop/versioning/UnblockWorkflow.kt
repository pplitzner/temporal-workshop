package digital.ald.temporalworkshop.versioning

import io.temporal.client.WorkflowClient
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.serviceclient.WorkflowServiceStubsOptions

class UnblockWorkflow

fun main(args: Array<String>) {
    val options = WorkflowServiceStubsOptions.newBuilder().build()
    val service = WorkflowServiceStubs.newServiceStubs(options)
    val client = WorkflowClient.newInstance(service)
    val workflow = client.newWorkflowStub(MyWorkflow::class.java, "4")

    workflow.unblock()
}
