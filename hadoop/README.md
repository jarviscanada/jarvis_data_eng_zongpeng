
Table of contents
* [Introduction](#introduction)
* [Hadoop Cluster](#hadoop-cluster)
	- [Hadoop Framework](#hadoop-framework)
	- [HDFS](#hdfs-the-storage-layer-of-hadoop)
	- [MapReduce](#mapreduce-the-data-processing-layer-of-hadoop)
	- [YARN](#yarn-the-resource-management-layer-of-hadoop)
* [Hive Project](#hive-project)
* [Future Improvements](#future-improvements)

# Introduction
The purpose of this project is to build a big data platform leveraging Hadoop, HDFS, and Hive. Through completing the project, I understand the structure of HDFS, why MapReduce is used, and what is Hive. I am able to leverage Hive to either write or read from HDFS. Moreover, possible optimization techniques are also implemented to lower query's processing time. 
In this project, Google Cloud Platform is used to build a hadoop cluster with 1 master node and 2 worker node. They are managed by YARN and user can connect to Hive Server by either CLI(beeline) through SSH or Zeppelin Notebook through browser. In this project, Zeppelin is mainly used, and there is a json file, which can be imported as a Zeppelin Notebook, under hive folder in this project. Basically, in the notebook, the data from Google storage is written to HDFS with a correct format and 2 optimizations are done to further increase the performance of the HiveQL queries.

# Hadoop Cluster
![Diagram](./assets/architecture.png)
## Hadoop Framework
Hadoop is an open-source software framework used for storing and processing Big Data in a distributed manner on large clusters of computers. It leverages the advantages of MapReduce. One single task is distributed to different computer in the cluster (worker nodes) to increase performance. Overall work flow is managed by YARN and Hive will help with writting or reading from data warehouse which is Hadoop Distribute File System. All concepts mention will be described in detail in the following sections.
## HDFS: the storage layer of Hadoop
HDFS (Hadoop Distributed File System) is the primary data storage system used by Hadoop applications. It employs a NameNode and one or more DataNodes to implement a DFS(Distributed File System) that provides high-performance access to data.
### NameNode - The Master
This node is the center of the hadoop cluster and locates at master node. It is in charge of maintaining, monitoring, and managing data node. All configuration about HDFS such as access privileges and file system namespace, and metadata for data nodes are stored in this node. 
As all metadata are stored in the memory, this type of node requires enough RAM space.
### DataNode - The Worker
This type of nodes are where the acutual data are stored and where read, write or data processing is handled. It locates at worker nodes. They will execute the tasks given by name node and send the result back to name node. 
Since the actual data are stored in this type of node. It requires a large disk space.
## MapReduce: the data processing layer of Hadoop
The reason MR(MapReduce) is used is to take advantage of DFS. As the processing speed of CPU core and RAM is very fast, the bottleneck for the performance is I/O operations(loading data from disk to memory). By having plenty of data node to pre-process different blocks of data(map phase), the performance is increased as the heavy processing part is done in parallel. Then the final light processing part is done by name node(reduce phase) to produce final results. The effect of this bottleneck in minimized. 
### Map Phase
This phase applies business logic to the data. The input data gets converted into key-value pairs based on the user-defined functions(mapper). This part is done by the data nodes. They will gather and process data based on the given bussiness logic. The output key-value pair will be sent to the reducer located at name node.
### Reduce Phase
The Reduce phase takes the output of Map Phase as input. It applies aggregation based on the requirements. Then, the output of this reducer located at name node will be the final result
## YARN: the resource management layer of Hadoop
### Resource Manager
The resource manager is running on the master node. It will keep track of workers' location and their hardware usage information. The main job of YARN is to manage and assign resources to different jobs. 
### Node Manager
The node manager is running on worker node where it can gather the resources(hardware) usage information and send it to resource manager. It can also start the container for a new job. 
## Hive
Hive is an ETL and Data warehousing tool developed on top of Hadoop Distributed File System (HDFS). Hive makes job easy for performing operations like data encapsulation and analysis of huge datasets. Hive works like RDBMS and HiveQL is a SQL-like language to interact with Hive. To connect to Hive, you can either do it by beeline CLI or by HiveServer2 through JDBC.
### Beeline CLI
When Hive is installed, a comman called beeline is also installed. It provide a way to connect to Hive through CLI. One example would be: `beeline -u "jdbc:hive2://jarvis-bootcamp-m:10000"`. The url can be changed according to host name and port number. If the connection is established successfully, the Hive can be used right away.
### Zeppelins
One disadvantage of using beeline CLI is that it's hard to manage when trying to execute a large number of queries. Therefore, more and more people are using notebooks which provide a clean UI for queries and output of those queries. Zeppelin is one of them. 
#### Zeppelin Configurations
To connect to hive, zeppelin must be running on the master node. Then a customized hive interpreter is created based on default JDBC interpreter. Instead, the database url has the format like `jdbc:hive2://[hostname]:[portnumber]` and the default driver should be `org.apache.hive.jdbc.HiveDriver`. To make sure Zeppelin can find the right driver, two dependencies are added: `org.apache.hive:hive-jdbc:[version]` and `org.apache.hadoop:hadoop-common:[version]`. The version can be found on maven repository. One last thing is to change the default user.
#### Usage
Using hive in Zeppelin is very easy after above configuration. Simple put `%hive` at top of Zeppelin block and then the following code will be interpreted by the hive interpreter created in above steps. The data used is World Development Index data from 1960 to 2016 which can be found and on Google Cloud Platform.

---
**Hardware Specifications**
In this project, the hadoop cluster is created containing 1 master node and 2 worker node. For each node, it has 2 cores, 13GB of RAM, and 100GB of disk space. 
# Hive Project
In this project, there are mainly three parts: loading data into HDFS, optimization of queries and comparison of performance between hive and spark. Each part is described in the screenshot below along with the code and output. Overall, the basic operations are studied as well as the more advanced optimization.

![Diagram](./assets/Zeppelin.png)

# Future Improvements
-	Try combining both partition optimization and columnar optimization and test how well it is.
-	Try examining performance difference between clumnar optimized solution and spark when the query becomes simpler. 
-	Try defining the best use cases for partition optimization and columnar optimization by running different queries.
