CHAPTER 1: ABOUT THIS COURSE
Welcome
Meet the Instructors
Format and Duration
Course Outcomes
Exercise Environment Orientation
(Optional activity) Setting Up a Local Development Environment
Course Conventions
Getting Help
CHAPTER 2: WHAT IS TEMPORAL?
Chapter 2 Goals
Introducing Temporal
What is a Workflow?
Workflow Examples
Architectural Overview
Options for Running a Temporal Cluster
Temporal SDKs
Temporal Command-Line Interface: temporal
CHAPTER 3: DEVELOPING A WORKFLOW
Chapter 3 Goals
Writing a Workflow Definition
Input Parameters and Return Values
* Values Must Be Serializable
* Data Confidentiality
* Avoid Passing Large Amounts of Data
Initializing the Worker
* WorkflowServiceStubs: represents a gRPC connection to a Temporal Cluster
* WorkflowClient: Used to communicate with Temporal server (can actually be the same used for starting workflows)
* factory.start(): Worker will start long polling task queue
CHATPER 4: EXECUTING A WORKFLOW
Chapter 4 Goals
Executing a Workflow from the Command Line
Hands-On Exercise #1: Hello Workflow
Exercise #1 Recap: Hello Workflow
Executing a Workflow from Application Code
CHAPTER 5: VIEWING WORKFLOW EXECUTION HISTORY
Chapter 5 Goals
Viewing Workflow History with the Temporal CLI
Viewing Workflow History from the Web UI
Hands-On Exercise #2: Hello Web UI
CHAPTER 6: MODIFYING AN EXISTING WORKFLOW
Chapter 6 Goals
Making Changes to a Workflow
Restarting the Worker Process
CHAPTER 7: DEVELOPING AN ACTIVITY
Chapter 7 Goals
What Are Activities?
Registering Activities
Executing Activities
CHAPTER 8: HANDLING ACTIVITY FAILURE
Chapter 8 Goals
How Temporal Handles Activity Failure
Activity Retry Policy Example
Hands-On Exercise #3: Farewell Workflow
CHAPTER 9: UNDERSTANDING WORKFLOW EXECUTION
Chapter 9 Goals
About This Example
Code Walkthrough
* When the worker encounters an activity invocation in the worklow code it conludes the WorkflowTask by sending WorkflowTasCompleted to the server
CHAPTER 10: CONCLUSION
Chapter 10 Goals
Essential Points
Hands-On Exercise #4: Finale Workflow
Parting Words
For More Information


---

# Goals

102
## Apply Temporal best practices for application development

Illustrate the logical and physical view of a Temporal application deployment in production
Use Timers to introduce delays in Workflow Execution
Use logging to report information from Workflows and Activities during execution
Support backwards-compatible evolution of input parameters and return values with structs
Configure a Temporal Client to access Temporal Cloud or a secure self-hosted cluster
Understand how to return errors from Activities and Workflows
Learn how returning errors from Workflows and Activities affects Workflow Execution
Discover how to avoid, identify, and correct common sources of non-determinism in a Workflow

## Validate application behavior through automated testing

Write and run unit tests for Workflows and Activities
Develop mocks to isolate Workflow tests from Activity implementations
Test compatibility with past executions using Workflow Replay
Download a Workflow Execution History in JSON format for use in compatibility tests

## Evaluate an Event History to debug problems with Workflow Execution

Understand the different states of Workflows and Activities
Use the Web UI to trace both open and closed Workflow Executions
Understand the role of Sticky Task Queues in Workflow Execution
View the stack trace of a current Workflow Execution
Understand the relationship between Temporal SDK calls, Commands, and Events
Explain what happens when a Worker fails and how this affects you as a developer
Understand why Temporal Workflows must be deterministic and how this affects the code you write

## Safely incorporate changes to Temporal Applications running in production

Explore considerations for deploying both new and updated applications to production
Identify current Workflow Executions (advanced visibility)
Terminate workflows using the CLI, SDK, and Web UI
Understand the difference between Workflow cancellation and termination
Observe what happens when you run a Workflow that contains non-deterministic code
Reset a Workflow Execution in order to recover from a bad deployment
Understand which types of changes can safely be deployed without versioning
Explain how to define and use versioning to support incompatible changes to a Workflow

# UNDERSTANDING KEY CONCEPTS IN TEMPORAL
Durable Execution System
Temporal Application Structure
Client-Server Communication in Temporal
Integrating Temporal into Other Applications
How Errors Affect Workflow Execution

# IMPROVING YOUR TEMPORAL APPLICATION CODE
Backwards-Compatible Evolution of Input Parameters and Return Values
Using Appropriate Timeouts
Choosing Names for Task Queues
Choosing Workflow IDs
Workflow ID Reuse Policy and Retention Period
* Rather than storing workflows via a long retention period, the archival feature should be used.
Logging in Workflows and Activities
Accessing Results
Hands-On Exercise #1: Using Structs for Data

# USING TIMERS IN A WORKFLOW DEFINITION
What is a Timer?
Use Cases for Timers
Timer APIs Provided by the Go SDK
Pausing Workflow Execution for a Specified Duration
Running Code at a Specific Point in the Future
What Happens to a Timer if the Worker Crashes?
Hands-On Exercise #2: Observing Durable Execution
 
# TESTING YOUR TEMPORAL APPLICATION CODE
Unit Testing Primer for Go (Optional)
Validating Correctness of Temporal Application Code
Testing Activities
Testing Workflows
Mocking Activities for Workflow Tests
Hands-On Exercise #3: Testing the Translation Workflow

# UNDERSTANDING EVENT HISTORY
Workflow Execution Overview
How Workflow Code Maps to Commands
Overview of Event History
Event History Limits
Event Structure and Attributes
How Commands Map to Events
Workflow and Activity Task States
Sticky Execution
# DEBUGGING WORKFLOW EXECUTION
Debugging a Workflow that Does Not Progress
* Web UI shows all workers having been registered for the task queue during the last 5min. Even stopped workers!
Interpreting Event History for Workflow Executions
* Completed events store event IDs of associated scheduled and started events
Terminating a Workflow Execution with the Web UI
Identifying and Fixing a Bug in an Activity Definition
Hands-On Exercise #4: Debugging and Fixing an Activity Failure

# DEPLOYING YOUR APPLICATION TO PRODUCTION
The Deployment Landscape
* Frontend Service
  * An API gateway that validates and routes inbound calls
* History Service
  * Maintains history and moves execution progress forward
* Matching Service
  * Hosts Tas Queues and matches Workers with Tasks
* Worker Service (has nothing to do with a Temporal Worker!)
  * Runs internal system workflows
Configuring a Temporal Client for Production
Overview of Temporal Application Deployment

# UNDERSTANDING WORKFLOW DETERMINISM
History Replay: How Temporal Provides Durable Execution
* After a crash a (new) Workers queries the workflow history and replays it
* The worker compiles a list of exepected commands from the event history
* It then executes the code and checks if every command issued is the same and in the same order as in the expected command list
Why Temporal Requires Determinism for Workflows
Common Sources of Non-Determinism
* Examples of Changes That May Lead to Non-Deterministic Errors
* Adding or removing an Activity
* Switching the Activity Type used in a call to ExecuteActivity
* Adding or removing a Timer
* Altering the execution order of Activities or Timers relative to one another
Identifying Non-Deterministic Code through Static Analysis
How Workflow Changes Can Lead to Non-Deterministic Errors
Deployment Leads to Non-Deterministic Error
Using Workflow Reset to Recover from a Bad Deployment
* Reset is possible from the web ui

# VERSIONING YOUR WORKFLOWS
What is Workflow Versioning?
Identifying Open Executions by Workflow Type
Approaches for Safely Deploying Incompatible Changes
Versioning Workflows with the GetVersion API
Testing Backwards Compatibility of a Change
Hands-On Exercise #5: Versioning Workflows with the GetVersion API

# Essential Points
The following is a summary of the most essential things that you have learned during the course.

## Overview
Temporal is an open source durable execution system, which can significantly increase your productivity as a developer by providing tools and APIs that make it possible to develop applications that scale easily and run reliably even under adverse conditions.

## Temporal Applications
Temporal applications include both code you develop and features provided by a Temporal SDK. As a developer, you are responsible for developing Workflow Definitions and Activity Definitions—code that represents your business logic—as well as configuring the Worker and Client components provided by the SDK.

Should a Worker crash during a Workflow Execution, another Worker will automatically reconstruct the previous state and continue the execution from that point forward, as if the crash had never occurred at all. It achieves this by using a technique called History Replay, which in turn requires that execution of the Workflow Definition is deterministic. This affects how you should approach Temporal application development and the following are recommended best practices to follow:

### Recommended best practices for Temporal Application Development
Use structs, rather than individual fields, as input parameters and return values in Workflow and Activity definitions. This helps to support backwards-compatible evolution of the data supplied and returned in their execution.
Since Temporal imposes limits on the size of Events in the history associated with a Workflow Execution, avoid using large amounts of data for the inputs and outputs of Workflows and Activities.
Avoid common sources of non-determinism, such as random numbers and system time, in your Workflow code. The workflowcheck tool can identify many sources of non-determinism in a Workflow Definition. In many cases, the SDK provides Workflow-safe alternatives. For example, instead of using Go's time.Sleep function, you can use workflow.Sleep to introduce a delay during Workflow Execution.
Although Workflow Definitions must execute deterministically, there is no such constraint on Activity Definitions. If an Activity fails due to a bug in the code, you can deploy a fix while the Workflow is still running. Activity failure is normal and expected, since they are retried automatically, but failing a Workflow is much less common.
Use Temporal's logging API, which won't result in duplicate messages during History Replay, but consider integrating a third-party logging implementation. This will give you better control over which messages are logged, how they are displayed, and where they are stored.

## Testing Temporal Applications
Automated testing is an investment in the quality of your code. Temporal's Go SDK provides support for automated testing via its testsuite package, which offers test environments for Workflow and Activity Execution, as well as a test suite for defining collections of tests.

You'll run these the same as you would other tests in Go. You may use third-party libraries, such as Testify, to extend the capabilities of these tests by adding support for assertions and mock objects. Using Mocks in a Workflow test allows you to verify its business logic in isolation from the Activity implementation.

The time-skipping feature of the Workflow testing environment fires Timers immediately, allowing you to quickly run tests for long-running Workflows. Another novel feature of Temporal is the ability to test the compatibility of a modification by replaying previous executions, which you can download using the Web UI or command-line tool, with the updated Workflow Definition.

## Workflow Execution
Workflow Execution begins with a request from a Client, which specifies the Workflow Definition and input data to use. A single Workflow Definition can be executed any number of times, potentially using different input each time. These executions can run concurrently, although each must have a Workflow ID that is unique among all other Workflow Executions in the same namespace, and the uniqueness requirement of the Workflow ID may be further constrained by the Workflow ID Reuse Policy associated with an execution.

Although not required in the Go SDK, we recommend that you specify a Workflow ID when you start a Workflow Execution. You should choose a value that is meaningful to your business logic and which will be unique among all Workflow Executions (not just those for the same Workflow Type) running in the same namespace at any given point in time.

Once started, a Workflow Execution immediately enters the open state, which simply means that it's running. As the Worker executes code in the Workflow Definition, it may encounter calls to certain Temporal APIs, such as workflow.ExecuteActivity or workflow.Sleep, that require some interaction with the Temporal Cluster. When this occurs, the Worker issues a Command to the Temporal Cluster specifying the requested action and the details needed to achieve it. For example, a call to workflow.Sleep causes the Worker to issue a StartTimer Command, which specifies the duration of the Timer. In response, the Temporal Cluster starts the Timer and logs the TimerStarted event. When the Timer fires, the cluster logs a TimerFired event and adds a new Workflow Task to its queue. The Worker subsequently polls the Task Queue, accepts this Task, and resumes execution.

Although any available Worker can accept tasks, a performance optimization known as "sticky execution" favors using the same Worker for multiple Workflow Tasks during a given execution. Since the Temporal Cluster does not assign tasks to Workers, the cluster does this by adding subsequent Workflow Tasks to a private queue shared with the Worker. This makes History Replay faster and more efficient, since Workers are often able to restore execution state using Event History data they have cached.

Eventually, the Workflow Execution will transition to the closed state, meaning that it has come to an end. If the function associated with the Workflow Definition returns a result, it closes with a status of Completed, indicating that execution was successful. If it instead returns an error, it closes with a status of Failed. The other four reasons it can close are because it was canceled, terminated, timed out, or continued-as-new. Cancellation and termination are similar in that they can both end the Workflow Execution, but cancelation is a more graceful way to achieve this. Cancellation allows for cleanup, while termination does not. An analogy to UNIX would be that cancellation is like the kill command, while termination is like kill -9.

## Event History
The Event History documents a Workflow Execution, from the perspective of the Temporal Cluster. It is an ordered append-only list of Events, each of which has a timestamp and Event Type, plus an Event ID that represents its position within the history. Events may have additional attributes, which vary based on the Event Type. For example, the WorkflowExecutionCompleted Event contains the result of that execution, while a WorkflowExecutionFailed Event contains the error that caused the execution to fail.

Temporal maintains limits on both the size and count of Items in the Event History. The cluster begins to log warnings when Workflow Executions exceed 10K events. Workflow Executions with Event Histories that exceed 50K events or 50 MB may be terminated. We recommend not exceeding a few thousand Events in a single Workflow Execution, since this should provide sufficient time to address the issue. Using Continue-As-New is one approach for this, since it continues running the code in a new Workflow Execution, and therefore, a new Event History.

The retention period, which is set on a per-namespace basis, defines how long the Event History and other data associated with Workflow Executions are retained after they close. It's important to note that the countdown to the retention period only begins when the Workflow Execution ends, so the retention period has no effect on the ones that are still running, regardless of how long they take to finish.

## Building and Running Temporal Applications
Temporal does not mandate specific tools or processes for building, deploying, or running applications. For example, you may choose to build them using a go build command, perhaps as part of a script or Makefile. You can deploy them by copying the resulting executable to the production system, perhaps using a CI/CD tool to manage the build and deployment process. You can run the application directly on physical hardware ("bare metal"), in virtual machines, or in containers (either with or without Kubernetes).

In production, you will typically deploy and run multiple copies of your application concurrently, since each instance can increase the overall scalability and availability.

## Temporal Cluster, Temporal Cloud, and Temporal Server
The term Temporal Cluster refers to the Temporal Server software, plus the database it requires for persistence and any other optional components, such as Elasticsearch or Grafana. Temporal Cloud is a managed service that provides an alternative to self-hosting a Temporal cluster. Although Temporal Cloud is obviously quite different from an operational perspective, since it relieves you from having to set up and manage a self-hosted cluster, they are equivalent from the perspective of an application developer.

The Temporal Server consists of a Frontend Service and three backend services:

* The History Service maintains the Event History and progress of Workflow Executions
* The Matching Service manages the Task Queue, matching Workers with available Workflow and Activity Tasks.
* The Worker Service runs internal system Workflows, which are not visible to users. Despite the name, it's unrelated to the Worker that executes your application code.
* The Frontend Service is responsible for accepting requests from clients and routing them to the appropriate backend service if necessary. These requests, as well as other internode communication, uses gRPC and can be secured with TLS.

## Deploying to Production
By default, Temporal Clients connect to a Frontend Service via TCP port 7233 on localhost. You can customize this in the ClientOptions used to create a Client. As you move your application between environments—for example, from development to production—you may need to change them for the Worker, which contains a Client. This typically involves specifying a different address used to access the Frontend Service, and depending on your requirements, may involve specifying a namespace, custom Logger implementation, custom Client ID, and/or TLS options. This is usually just a few lines of code and the only modification required when deploying to production.

Once deployed, you must take care when deploying changes. It's safe to change Activity Definitions, but changing a Workflow Definition can potentially lead to non-deterministic errors if there are open Workflow Executions for that Workflow Type and the change affects the Commands generated when the code is run. You can test compatibility by replaying the history for one or more past Workflow Executions using the updated version of the code.

There are three ways of versioning changes to a Workflow Definition that uses the Go SDK.

Using different Task Queues for each version
Using different Workflow Types for each version
Using the GetVersion API to define each versioned change as a logical branch of execution
The GetVersion approach is the most comprehensive. You can remove support for a version once the last open execution based on it closes. Applying a List Filter in the Web UI is a convenient way to identify open executions for a given version.