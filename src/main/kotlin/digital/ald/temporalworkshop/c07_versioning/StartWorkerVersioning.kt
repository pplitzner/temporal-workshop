package digital.ald.temporalworkshop.c07_versioning

import io.temporal.client.WorkflowClient
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.serviceclient.WorkflowServiceStubsOptions
import io.temporal.worker.WorkerFactory

class StarterWorker

fun main(args: Array<String>) {
    val options = WorkflowServiceStubsOptions.newBuilder().build()
    val service = WorkflowServiceStubs.newServiceStubs(options)
    val workflowClient = WorkflowClient.newInstance(service)

    val workerFactory = WorkerFactory.newInstance(workflowClient)
    val worker = workerFactory.newWorker("myQueue")
    worker.registerWorkflowImplementationTypes(MyWorkflowImpl::class.java)
    worker.registerActivitiesImplementations(MyActivityImpl())

    workerFactory.start()
}
