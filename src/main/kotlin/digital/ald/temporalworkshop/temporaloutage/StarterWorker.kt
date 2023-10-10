package digital.ald.temporalworkshop.temporaloutage

import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowClientOptions
import io.temporal.common.converter.CodecDataConverter
import io.temporal.common.converter.DefaultDataConverter
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.serviceclient.WorkflowServiceStubsOptions
import io.temporal.worker.WorkerFactory


fun main() {
    val options = WorkflowServiceStubsOptions.newBuilder().build()
    val service = WorkflowServiceStubs.newServiceStubs(options)
    val client = WorkflowClient.newInstance(
        service,
        WorkflowClientOptions.newBuilder()
            .setDataConverter(
                CodecDataConverter(
                    DefaultDataConverter.newDefaultInstance(), listOf(MyCodec())
                )
            )
            .build()
    )

    val workerFactory = WorkerFactory.newInstance(client)
    val worker = workerFactory.newWorker("myQueue")
    worker.registerWorkflowImplementationTypes(WorkflowImpl::class.java)
    worker.registerActivitiesImplementations(ActivityImpl())

    workerFactory.start()
}
