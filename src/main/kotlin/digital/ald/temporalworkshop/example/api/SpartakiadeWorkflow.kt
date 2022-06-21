package digital.ald.temporalworkshop.example.api

import digital.ald.temporalworkshop.example.WorkshopStatus
import io.temporal.workflow.QueryMethod
import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod

@WorkflowInterface
interface SpartakiadeWorkflow {

    @WorkflowMethod
    fun applyForWorkshop(participant: String)

    @QueryMethod
    fun status(): WorkshopStatus

}