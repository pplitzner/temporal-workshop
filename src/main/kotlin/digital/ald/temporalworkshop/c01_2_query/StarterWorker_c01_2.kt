package digital.ald.temporalworkshop.c01_2_query

import digital.ald.temporalworkshop.c01_3_signal.MyActivityImpl_c01_3
import digital.ald.temporalworkshop.c01_3_signal.MyWorkflowImpl_c01_3
import io.temporal.client.WorkflowClient
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.serviceclient.WorkflowServiceStubsOptions
import io.temporal.worker.WorkerFactory

fun main() {
    val options = WorkflowServiceStubsOptions.newBuilder().build()
    val service = WorkflowServiceStubs.newServiceStubs(options)
    val workflowClient = WorkflowClient.newInstance(service)

    val workerFactory = WorkerFactory.newInstance(workflowClient)
    val worker = workerFactory.newWorker("c01_3")
    worker.registerWorkflowImplementationTypes(MyWorkflowImpl_c01_3::class.java)
    worker.registerActivitiesImplementations(MyActivityImpl_c01_3())

    workerFactory.start()
}