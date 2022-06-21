package digital.ald.temporalworkshop.example

import digital.ald.temporalworkshop.example.api.MailActivity

class MailActivityImpl: MailActivity {

    override fun sendWelcomeMail(participant: String) {
        println("Hello $participant and welcome to Spartakiade!")
    }

}