package digital.ald.temporalworkshop.c01_1_returnvalues

import io.temporal.client.WorkflowClient
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.serviceclient.WorkflowServiceStubsOptions
import io.temporal.worker.WorkerFactory

fun main() {
    val options = WorkflowServiceStubsOptions.newBuilder().build()
    val service = WorkflowServiceStubs.newServiceStubs(options)
    val workflowClient = WorkflowClient.newInstance(service)

    val workerFactory = WorkerFactory.newInstance(workflowClient)
    val worker = workerFactory.newWorker("c01_1")
    worker.registerWorkflowImplementationTypes(MyWorkflowImpl_c01_1::class.java)
    worker.registerActivitiesImplementations(MyActivityImpl_c01_1())

    workerFactory.start()
}
