package digital.ald.temporalworkshop.example.starter

import digital.ald.temporalworkshop.example.api.SpartakiadeWorkflow
import io.temporal.client.WorkflowClient
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.serviceclient.WorkflowServiceStubsOptions


fun main() {

    val options = WorkflowServiceStubsOptions.newBuilder().build()
    val service = WorkflowServiceStubs.newServiceStubs(options)
    val client = WorkflowClient.newInstance(service)
    val wf = client.newWorkflowStub(SpartakiadeWorkflow::class.java, "workflowID")
    println(wf.status())
}