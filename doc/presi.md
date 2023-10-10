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

Workflows <i class="fas fa-project-diagram"></i>
Activities <i class="fas fa-running"></i>
Queries/Signals <i class="fas fa-bolt"></i>
Timers <i class="fas fa-clock"></i>
Workers <i class="fas fa-cogs"></i>
Temporal Server <i class="fas fa-server"></i>
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

# Code

----

## Workflow

``` kotlin [1-14|1-2,4,6|5,7-8|10-14]
import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod

@WorkflowInterface
interface HelloWorkflow {
    @WorkflowMethod
    fun sayHello()
}

class HelloWorkflowImpl: HelloWorkflow {
    override fun sayHello() {
        println("Hello Devopenspace")
    }
}
```

----

## Worker

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

## Start a workflow

```
temporal workflow start --type HelloWorkflow --task-queue hello-queue
```
<!-- .slide: style="font-size: 36px;" -->

---








