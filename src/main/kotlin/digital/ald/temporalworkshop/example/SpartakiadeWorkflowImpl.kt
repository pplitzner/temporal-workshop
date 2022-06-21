package digital.ald.temporalworkshop.example

import digital.ald.temporalworkshop.example.api.MailActivity
import digital.ald.temporalworkshop.example.api.SpartakiadeWorkflow
import io.temporal.activity.ActivityOptions
import io.temporal.workflow.Workflow
import java.time.Duration

class SpartakiadeWorkflowImpl: SpartakiadeWorkflow {

    var activityOptions: ActivityOptions = ActivityOptions.newBuilder()
        .setStartToCloseTimeout(Duration.ofMinutes(10))
        .build()
    
    private val mailActivity = Workflow.newActivityStub(MailActivity::class.java, activityOptions)

    var status: WorkshopStatus = WorkshopStatus.INIT

    override fun applyForWorkshop(participant: String) {

        mailActivity.sendWelcomeMail(participant)
        status = WorkshopStatus.WELCOME_MAIL_SENT

    }

    override fun status(): WorkshopStatus = status
}

enum class WorkshopStatus {
    WELCOME_MAIL_SENT,
    INIT
}

