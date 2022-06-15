1. Introduction
   - use-case for workflow engine
   - Intro Temporal (Workflow, Activities, Temporal Server)
      - Workflow stateful/Activity stateless

2. Basic Workflow example
   - Two Activities with log messages
   - Note: Worker runs client code, thus has to be restarted
   1. Queries
       - add member variable, set it during workflow, query it after
   1. Signal
       - add boolean for signal + add waiting state + add signal method
   1. Return values
       - Let first activity return something that is used in second activity
3. Setup
   - TaskQueue as connector, StarterWorker, StarterWorkflow
   - Start workflow without worker running -> Start workflow
   - Namespaces
   - Stubs (Typed and untyped)
   - Dynamic Workflow(?)
      - Dynamic query and signal handlers
4. Temporal Web UI
   - List of workflows (workflowId + runId)
   - Filter options/Advanced search (only possible with Elastic)
   - Summary (input/result)
   - EventHistory
      - Event triple Workflow/Activity (Scheduled->Started->Completed)
      - Download as JSON
   - Query + Stacktrace
   - Terminate (only write operation)
   - Show waiting state + signal example
5. Exception Handling
   - WorkflowRetryOption
      - default no retries, can be reset
      - infinite execution time
   - ActivityRetryOptions
      - infinite retries
   1. ActivityFailure
      - Throw exception in activity method
      - show stacktrace in UI
      - show EventHistory
      - Fix and restart worker
   2. Activity timeout
   3. Temporal Server down
   4. Workflow Exception
   5. Workflow Timeout
   6. Activity.wrap()
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
    Saga compensation
    ChildWorkflows(?)
    Cancelling Workflows
        CanceledFailure/ActivityFailure
    Cron-Schedules
    Heartbeats/Polling


 Potentielle Fehlerbilder notieren
  - Workflow starten ohne Worker
  - Workflow und Worker auf untersch. Queues
  - Temporal Server nicht gestartet