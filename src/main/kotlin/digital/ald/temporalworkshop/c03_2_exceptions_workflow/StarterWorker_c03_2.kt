package digital.ald.temporalworkshop.c03_2_exceptions_workflow

import io.temporal.client.WorkflowClient
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.serviceclient.WorkflowServiceStubsOptions
import io.temporal.worker.WorkerFactory

fun main() {
    val options = WorkflowServiceStubsOptions.newBuilder().build()
    val service = WorkflowServiceStubs.newServiceStubs(options)
    val workflowClient = WorkflowClient.newInstance(service)

    val workerFactory = WorkerFactory.newInstance(workflowClient)
    val worker = workerFactory.newWorker("c03_2")
    worker.registerWorkflowImplementationTypes(MyWorkflowImpl_c03_2::class.java)

    workerFactory.start()
}
