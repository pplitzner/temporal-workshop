package digital.ald.temporalworkshop.c2_0setup

import io.temporal.client.WorkflowClient
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.serviceclient.WorkflowServiceStubsOptions
import io.temporal.worker.WorkerFactory

fun main() {
    val options = WorkflowServiceStubsOptions.newBuilder().build()
    val service = WorkflowServiceStubs.newServiceStubs(options)
    val workflowClient = WorkflowClient.newInstance(service)

    val workerFactory = WorkerFactory.newInstance(workflowClient)
    val worker = workerFactory.newWorker("c02_0")
    worker.registerWorkflowImplementationTypes(MyWorkflowImpl_c02_0::class.java)
    worker.registerActivitiesImplementations(MyActivityImpl_c02_0())

    workerFactory.start()
}
