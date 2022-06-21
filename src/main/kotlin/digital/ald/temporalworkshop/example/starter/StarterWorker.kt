package digital.ald.temporalworkshop.example.starter

import digital.ald.temporalworkshop.example.MailActivityImpl
import digital.ald.temporalworkshop.example.SpartakiadeWorkflowImpl
import io.temporal.client.WorkflowClient
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.serviceclient.WorkflowServiceStubsOptions
import io.temporal.worker.WorkerFactory

fun main() {
    val options = WorkflowServiceStubsOptions.newBuilder().build()
    val service = WorkflowServiceStubs.newServiceStubs(options)
    val workflowClient = WorkflowClient.newInstance(service)

    val workerFactory = WorkerFactory.newInstance(workflowClient)
    val worker = workerFactory.newWorker("myQueue")
    worker.registerWorkflowImplementationTypes(SpartakiadeWorkflowImpl::class.java)
    worker.registerActivitiesImplementations(MailActivityImpl())

    workerFactory.start()
}
