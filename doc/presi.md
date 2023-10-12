# Welcome

---

# Vorstellung
`whois pplitzner`
That's me

---

# Goals

---

# Setup

---

# Motivation

----

![](https://pplitzner.github.io/images/temporal-devopenspace/img1.png)

----

![](https://pplitzner.github.io/images/temporal-devopenspace/img2.png)

----

![](https://pplitzner.github.io/images/temporal-devopenspace/img3.png)

----

![](https://pplitzner.github.io/images/temporal-devopenspace/img4.png)

----

![](https://pplitzner.github.io/images/temporal-devopenspace/img5.png)

----

![](https://pplitzner.github.io/images/temporal-devopenspace/img6.png)

----

![](https://pplitzner.github.io/images/temporal-devopenspace/img7.png)

----

![](https://pplitzner.github.io/images/temporal-devopenspace/img8.png)

----

![](https://pplitzner.github.io/images/temporal-devopenspace/img9.png)

----

![](https://pplitzner.github.io/images/temporal-devopenspace/img10.png)

----

![](https://pplitzner.github.io/images/temporal-devopenspace/img14.png)

---

# Temporal

----


![](https://pplitzner.github.io/images/temporal-devopenspace/temporal_history2.png)

----

## What is a Workflow

* Sequence of Steps
* Start/End
* May contain
  * Long-running process
  * Conditional logic
  * Cycles
  * (External) Exception handling
  * Different stake holders

----

### Workflow example

![](https://pplitzner.github.io/images/temporal-devopenspace/expense-report-workflow-diagram.png)

----

## Temporal Components

* Workflows<!-- .element: class="fragment" data-fragment-index="1" --> <i class="fa fa-list-ol"></i><!-- .element: class="fragment" data-fragment-index="1" -->
* Activities<!-- .element: class="fragment" data-fragment-index="2" --> <i class="fa fa-wrench"></i><!-- .element: class="fragment" data-fragment-index="2" -->
* Queries/Signals<!-- .element: class="fragment" data-fragment-index="3" --> <i class="fa fa-bolt"></i><!-- .element: class="fragment" data-fragment-index="3" -->
* Timers<!-- .element: class="fragment" data-fragment-index="4" --> <i class="fa fa-clock-o" aria-hidden="true"></i><!-- .element: class="fragment" data-fragment-index="4" -->
---

* Workers<!-- .element: class="fragment" data-fragment-index="5" --> <i class="fa fa-cogs"></i><!-- .element: class="fragment" data-fragment-index="5" -->
* Temporal Server<!-- .element: class="fragment" data-fragment-index="6" --> <i class="fa fa-server"></i><!-- .element: class="fragment" data-fragment-index="6" -->

Note:
Temporal achieves this with the following components:
Workflow = Workflow
Steps = Activities
Exception Handling = Queries/Signals
Long running = timers

---

## Temporal architecture

![](https://pplitzner.github.io/images/temporal-devopenspace/temporal_architecture.png)

---

# Please, show me code!

----

## Workflow <i class="fa fa-list-ol"></i>

``` kotlin [1-14|1-2,4,6|5,7-8|10-14]
import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod

@WorkflowInterface
interface HelloWorkflow {
    @WorkflowMethod
    fun sayHello(name: String): String
}

class HelloWorkflowImpl: HelloWorkflow {
    override fun sayHello(name: String): String {
        return "Hello $name"
    }
}
```

----

## Worker <i class="fa fa-cogs"></i>

``` kotlin [1-13|7|8|9|10|11|12]
import io.temporal.client.WorkflowClient
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.worker.WorkerFactory
import io.temporal.worker.registerWorkflowImplementationType

fun main(){
    val service = WorkflowServiceStubs.newLocalServiceStubs()
    val client = WorkflowClient.newInstance(service)
    val factory = WorkerFactory.newInstance(client)
    val worker = factory.newWorker("hello-queue")
    worker.registerWorkflowImplementationType<HelloWorkflowImpl>()
    factory.start()
}
```

<!-- .slide: style="font-size: 36px;" -->

----

## Start a workflow (CLI)

``` bash
temporal workflow start --type HelloWorkflow --task-queue hello-queue --input '"Devopenspace"'
```
<!-- .slide: style="font-size: 36px;" -->

Note:
since Workflows might run for months or years. You can use the temporal     workflow show command to retrieve the result
Bei workflow error
temporal workflow list -> Alle workflows zeigen
temporal workflow show --workflow-id id --fields long --output json


----

## Start a workflow (SDK)

``` kotlin [1-13|7-8|9-10|11-12|13]
import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowOptions
import io.temporal.client.newWorkflowStub
import io.temporal.serviceclient.WorkflowServiceStubs

fun main(){
    val service = WorkflowServiceStubs.newLocalServiceStubs()
    val client = WorkflowClient.newInstance(service)
    val workflowOptions = WorkflowOptions.newBuilder()
        .setTaskQueue("hello-queue").build()
    val workflow = client
        .newWorkflowStub<HelloWorkflow>(workflowOptions)
    workflow.sayHello("Devopenspace")
}
```
Note:
First two lines are exactly the same as for the worker.
They are just to create the client
<!-- .slide: style="font-size: 36px;" -->

---

# Temporal Web UI

---

## Exercise
Greet yourself in German ("*Hallo*")
<span style="color:#fff; font-size: 0.5em;">(dont forget the input)</span>
```
temporal workflow start --type HelloWorkflow --task-queue hello-queue --input '"Patrick"'
```
<!-- .code-wrapper: style="font-size: 32px;" -->




---

# Real World

Car Leasing <i class="fa fa-car"></i>

----

## Welcome Mail

![](https://pplitzner.github.io/images/temporal-devopenspace/car_leasing_01.png)

----

## Activities <i class="fa fa-wrench"></i>
* Used for operations that may fail
  * external services
  * DB calls
  * read/write to storage
* Can be retried
* Should be stateless and deterministic

----

### Activity definition
``` kotlin [1-14|1-2,4,6,9,12]
import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod

@ActivityInterface
interface CustomerActivity {
    @ActivityMethod
    fun isKnownCustomer(email:String): Boolean

    @ActivityMethod
    fun sendWelcomeMail(email: String)

    @ActivityMethod
    fun sendCarInfoMail(email: String, carName: String)
}
```

Note:
Activities have to be registered with the worker!

----

### Activity registration (Worker)
``` kotlin
worker.registerActivitiesImplementations(
    CustomerActivityImpl()
)
```
### Activity usage (Workflow)
``` kotlin
val activityOptions = ActivityOptions.newBuilder()
    .setStartToCloseTimeout(Duration.ofSeconds(5))
    .build()
val customerActivity = Workflow.newActivityStub(
    CustomerActivity::class.java,
    activityOptions
)

customerActivity.isKnownCustomer(email)
```
<!-- .code-wrapper: style="font-size: 32px;" -->


----


### Activity Retries

What happens if something breaks?

| Retry Option     | default   |
| -------------------- | --------- |
| Initial Interval     | 1s        |
| Backoff Coefficient  | 2         |
| Maximum Interval     | 100s      |
| Maximum Attempts     | Unlimited |











