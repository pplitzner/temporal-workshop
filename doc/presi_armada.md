# Motivation


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

## Was ist ein Workflow?

----

![](https://pplitzner.github.io/images/flex_workflow_01.png)

----

### Was ist ein Workflow?

* Deterministische Abfolge von Schritten
* Start/Ende
* Kann enthalten
  * Langlebige Prozesse
  * Bedingte Abzweigungen
  * Zyklen
  * (External) Exception handling
  * Verschiedene Systeme

----

## Temporal Komponenten

* Workflows <i class="fa fa-list-ol"></i>
* Activities <i class="fa fa-wrench"></i>
* Queries/Signals <i class="fa fa-bolt"></i>
* Timers <i class="fa fa-clock-o" aria-hidden="true"></i>
---

* Workers <i class="fa fa-cogs"></i>
* Temporal Server <i class="fa fa-server"></i>

Note:
Temporal achieves this with the following components:
Workflow = Workflow
Steps = Activities
Exception Handling = Queries/Signals
Long running = timers

---

## Temporal architecture

![](https://pplitzner.github.io/images/temporal_architecture.png)

---

# \<Code\>

----

![](https://pplitzner.github.io/images/flex_workflow_bpmn.png)

----

### Workflow <i class="fa fa-list-ol"></i>

``` kotlin [1-14|1|3|5|7-13|8|10|12|14]
fun customerJourney(car: String, email: String) {
    
    sendWelcomeMail(email)
    
    val success = creditCheck(email)
    
    if(success) {
        leaseCar(car, email)
        
        Workflow.sleep(Duration.ofDays(14))
        
        customerActivity.checkupMail(email)
    }
    return success
}
```
<!-- .slide: style="font-size: 34px;" -->

----

## Workflow <i class="fa fa-list-ol"></i>

``` kotlin [1-7|1,4]
@WorkflowInterface
interface CustomerJourneyWorkflow {
    
    @WorkflowMethod
    fun customerJourney(car: String, email: String): Boolean
    
}
```
## Activity <i class="fa fa-wrench"></i>

``` kotlin [1-6|1]
@ActivityInterface
interface CustomerJourneyActivity {
    fun sendWelcomeMail(email: String)
    fun sendCheckupMail(email: String)
    fun creditCheck(email: String): Boolean
    fun leaseCar(car: String, email: String)
}
```
<!-- .slide: style="font-size: 36px;" -->
Note:
Interfacedefinition ist quasi die Schnittstelle zum Temporal Server

----

## Worker <i class="fa fa-cogs"></i>

``` kotlin [1-10|1,6|7,8|9]
const val QUEUE_NAME = "customer-journey-queue"
fun main(){
    val service = WorkflowServiceStubs.newLocalServiceStubs()
    val client = WorkflowClient.newInstance(service)
    val factory = WorkerFactory.newInstance(client)
    val worker = factory.newWorker(QUEUE_NAME)
    worker.registerWorkflowImplementationType<CustomerJourneyWorkflowImpl>()
    worker.registerActivitiesImplementations(CustomerJourneyActivityImpl())
    factory.start()
}
```

<!-- .slide: style="font-size: 36px;" -->

----

## Start a workflow (SDK)

``` kotlin [1-12|2-3|7-8]
fun main(){
    val service = WorkflowServiceStubs.newLocalServiceStubs()
    val client = WorkflowClient.newInstance(service)
    val workflowOptions = WorkflowOptions.newBuilder()
        .setTaskQueue(QUEUE_NAME)
        .build()
    val workflow = client.newWorkflowStub<CustomerJourneyWorkflow>(workflowOptions)
    val result = workflow.customerJourney("Renault ZOE", "patrick.plitzner@aldautomotive.com")
    println(result)
}
```
Note:
First two lines are exactly the same as for the worker.
They are just to create the client
<!-- .slide: style="font-size: 36px;" -->


----

## Start a workflow (CLI)

``` bash
temporal workflow start --type CustomerJourneyWorkflow --task-queue customer-journey-queue --input '"Renault ZOE"' --input '"patrick.plitzner@aldautomotive.com"'
```
<!-- .slide: style="font-size: 36px;" -->

Note:
since Workflows might run for months or years. You can use the temporal     workflow show command to retrieve the result
Bei workflow error
temporal workflow list -> Alle workflows zeigen
temporal workflow show --workflow-id id --fields long --output json

---

# Temporal Web UI


---

## Activities <i class="fa fa-wrench"></i>
* Verwendet für Aufrufe, die fehlschlagen können
  * externe Services
  * DB-Aufrufe
  * lesender/schreibender Speicherzugriff
* Können erneut ausgeführt werden
* Sollten zustandslos und deterministisch sein


Note:
Loglevel auf ALL stellen, um non-deterministic errors zu sehen

----

### Activity Retries

Was passiert, wenn etwas fehlschlägt?

| Retry Option     | default   |
| -------------------- | --------- |
| Initial Interval     | 1s        |
| Backoff Coefficient  | 2         |
| Maximum Interval     | 100s      |
| Maximum Attempts     | Unlimited |











