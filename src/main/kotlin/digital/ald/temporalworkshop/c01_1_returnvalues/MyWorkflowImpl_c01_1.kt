package digital.ald.temporalworkshop.c01_1_returnvalues

import io.temporal.activity.ActivityOptions
import io.temporal.workflow.Workflow
import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod
import java.time.Duration

class MyWorkflowImpl_c01_1: MyWorkflow_c01_1 {

    var activityOptions: ActivityOptions = ActivityOptions.newBuilder()
        .setScheduleToCloseTimeout(Duration.ofMinutes(10))
        .build()
    
    private val activity = Workflow.newActivityStub(MyActivity_c01_1::class.java, activityOptions)

    override fun runWorkflow(userId: Long): UserWithAddress {

        val userName = activity.loadUserData(userId)

        val userWithAddress = activity.fetchAddressData(userName)

        val message = "Successfully loaded data for ${userWithAddress.name} with address ${userWithAddress.address}"
        println(message)

        return userWithAddress
    }

}

@WorkflowInterface
interface MyWorkflow_c01_1 {
    @WorkflowMethod
    fun runWorkflow(userId: Long): UserWithAddress

}

