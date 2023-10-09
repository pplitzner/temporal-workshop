 1. History
    2. Amazon giant binary -> break it into (micro) services -> communicate via queues (SimpleQueueServices)
    3. Amazon Simple workflow service
    4. Uber -> Cadence
    5. Temporal
2. Introduction
   - use-case for workflow engine
     - distributes systems are complex, unreliable, need scaling
     - Workflow engines orchestrate the sequence of tasks executed in a workflow
     - Temporal idea: workflow as code
       - a lot of other engines have their own description language like XML or JSON
       - but with a general purpose language you get a lot of feature out of the box
         - versioning
         - testing
         - IDE
         - Frameworks
   - Intro Temporal Architecture (Workflow, Activities, Temporal Server)
      - Workflow stateful/Activity stateless
      - https://docs.temporal.io/tasks/#activity-task-execution <- Activities, Queues, etc.
      - Activities/Workflows are just code -> Unit-Test
      - Workers run your code
      - The temporal server is a kind of backend orchestrating service that manages queues, states and durabel timers

2. Basic Workflow example
   - Two Activities with log messages
   - Note: Worker runs client code, thus has to be restarted
   1. Queries
       - add member variable, set it during workflow, query it after
   1. Signal
       - add boolean for signal + add waiting state + add signal method
   1. Return values
       - Let first activity return something that is used in second activity
       - **Best practice**: Use objects as inputs to avoid breaking workflow signature 
3. Temporal Web UI
   - List of workflows (workflowId + runId)
   - Namespaces (workflowId is unique for one namespace)
   - Filter options/Advanced search (only possible with Elastic)
   - Summary (input/result)
   - EventHistory
      - Event triple Workflow/Activity (Scheduled->Started->Completed)
      - Download as JSON
   - Query + Stacktrace
   - Terminate (only write operation)
   - Show waiting state + signal example
4. Setup
   - TaskQueue as connector, StarterWorker, StarterWorkflow
   - Start workflow without worker running -> Start worker
   - Namespaces
   - Stubs (Typed and untyped)
      - untyped signals durch signalName, auch individualisierbar durch Annotation
   - Dynamic Workflow(?)
      - Dynamic query and signal handlers
5. Exception Handling
   - WorkflowRetryOption
      - default no retries, can be reset
      - infinite execution time
   - ActivityRetryOptions
      - infinite retries
      - https://docs.temporal.io/concepts/what-is-a-retry-policy/
   1. ActivityFailure
      - Throw exception in activity method
      - show stacktrace in UI
      - show EventHistory
      - Fix and restart worker
   2. Activity timeout
      - startToCloseTimeOut -> Infinite Retry
        - _io.grpc.StatusRuntimeException: NOT_FOUND: invalid activityID or activity already timed out or invoking workflow is completed_
      - scheduleToClose -> Workflow failed
        - _ActivityFailure_ caused by _TimeoutFailure: message='activity ScheduleToClose timeout', timeoutType=TIMEOUT_TYPE_SCHEDULE_TO_CLOSE_
      - **Note**: Wenn Workflow in ActivityRetry hängt, kann man die RetryOptions nicht nachträglich ändern
   3. Workflow Exception
      - Dauer-Retry -> Code-Fix wird gereplayt wie bei Activities 
   4. Workflow Timeout
      - tctl wf describe -w workflowID
   5. Temporal Server down
      - _io.grpc.StatusRuntimeException: UNAVAILABLE: Network closed for unknown reason_
      - fängt sich wieder nach restart
   7. Activity.wrap()
      - continueAsNew(?)
6. Determinism
   - When does replay happen?
   - Show replay happening with log messages
   - Use workflow replayer to show exceptions
   - Log vs Workflow.log()
   - Swap activity execution
   - Thread.sleep() vs Workflow.sleep()
   - Random() int,
   - Date
7. Versioning
   - changeID "Tag" + version number
   - Java SDK bug(?)
   - Use workflow replayer
8. SDK requests/TCTL
9. Testing






Potential topics
* Saga compensation
* ChildWorkflows(?)
* Cancelling Workflows
* CanceledFailure/ActivityFailure
* Cron-Schedules
* Heartbeats/Polling
* Ground of truth: Workflow or internal DB?

// use WorkflowClient.execute to return future that contains Workflow result or failure, or
// use WorkflowClient.start to return WorkflowId and RunId of the started Workflow).

Any exception thrown from an activity is wrapped into a TemporalFailure resp. ActivityFailure

 Potentielle Fehlerbilder notieren
  - Workflow starten ohne Worker
  - Workflow und Worker auf untersch. Queues
  - Temporal Server nicht gestartet
