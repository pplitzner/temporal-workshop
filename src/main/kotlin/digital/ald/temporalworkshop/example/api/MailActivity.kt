package digital.ald.temporalworkshop.example.api

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod

@ActivityInterface
interface MailActivity {

    @ActivityMethod
    fun sendWelcomeMail(participant: String)

}