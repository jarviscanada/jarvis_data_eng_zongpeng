* [Introduction](#introduction)
* [Spark Architecture](#spark-architecture)
* [Spark RDD Project](#spark-rdd-project)
* [Spark DF Project](#spark-df-project)

# Introduction
The purpose of this project is to leverage Apache Spark to process Big Data. Apache Spark is a unified analytics engine for general-purpose distributed data processing. In this project, two difference ways to use Spark: RDD and  Structured APIs are studied. I also have a comprehensive understanding of Spark's architecture. In the following section, Spark's architecture as well as how to leverage RDD and Structured API will be presented.

# Spark Architecture
### Spark Architecture diagram
![Diagram](./assets/SparkArchitecture.png)
### Cluster Manager
A cluster manager is responsible for launching the driver process and executor processes. It alse use a driver-worker relationship. There will be a cluster driver process on the master node and a cluster worker process in each worker node. They cooperate together to start the Spark application. 
### Driver & Executor
A Spark application consists of a driver process and mutiple executor processes created by the cluster manager. They cooperate together to execute job submitted by the user. 
#### Driver
The driver process is running in a worker node in the cluster. It is responsible for maintain information about the Spark application, responding to the user, and managing the work across the executors. This is an essential part of a Spark application.
#### Executor
The executors are responsible for computing the work assigned by the driver. Mainly, its job is to execute code and report the output back to the driver. 
### Resource Manager
In a cluster, a resource manager is required to use the computer resource efficiently. For a Spark application, it is compatible with lots of resource manager such as YARN or Mesos. Basicall, in a Spark application, driver process will negotiate with resource manager to find out where and when to launch a executor process. 
### Spark Session
As Spark is written in Scala, a translation is required if user code is written in other languages. Spark session is responsible for the translation process. The user code accepted by the Spark application JVM will be translated by Spark session into lower-level instructions which is executable by the executors.

# Spark RDD Project
### Resilient Distributed Datasets (RDD)
Resilient Distributed Datasets (RDD) is a fundamental data structure of Spark. It is an immutable distributed collection of objects which make it stable and fault-tolerance. It supports in-memory computation which makes Spark faster. Generally, dataframe oprations are built on the top of RDD and complie down to RDD for convenient and efficient distributed execution.
### Project Details
The purpose of RDD project is to get farmiliar to how to use RDD and understand the difference between transformation and action. Futhurmore, translation from SQL to RDD is done for couple examples.
![Diagram](./assets/spark-RDD.png)
# Spark DF Project
### Structured API
The Structured APIs are a set of tools, such as Datasets/DataFrames and SQL, for manipulating either raw, semi-structured, or highly structured data. Datasets and DataFrames are collections simulating a real table. They have well-defined rows and columns. The difference between them is that Datasets check for the conformation of data at compile time while DataFrames do it at runtime. SQL can also be used by simply type the query using SparkQL. Spark will translate these structured APIs into physical plan that can be read by the driver.
### Why is Structure APIs preferred?
During the translation from Datasets/DataFrames/SQL to physical plan, the job will undergo a  optimization process by catalyst optimizer. The job will be translated into several logical plans which will be passed through catalyst optimizer. The outcome, which is optimized logical plan will then be translated into appropriate physical plan for Spark to executed. Throught this process, the operations will be optimized and this is why structured APIs are preferred.
### Project Details
The purpose of this project is to get farmiliar with the syntax and operations of structured APIs. Furthurmore, several advanced bussiness questions are solved using structured APIs
![Diagram](./assets/spark-DF.png)
