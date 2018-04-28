# Meetup Report #2

### Name of group and subject

NAME- Coding Temple

SPEAKER- Brandon Miller, Data Scientist at Allstate

DATE - April 4th 2018

SUBJECT- Introduction to Distributed Big Data Processing with Hadoop ( 2 hrs + Q&A)

### Short summary and your conclusion

The speaker introduced himself as Data scientist in Allstate Insurance where he deals with different data related scenarios and uses latest BIG DATA tools to address them.

Primarily he talked about using Apache Spark on HDFS framework. Short Summary is provided below

#### What is Hadoop & HDFS
Traditional systems process data serially through single servers or group of servers. This is acceptable in scenarios where data requested is not required immediately or may not be same day. Considering current technology industry growth and demand, need of immediate data with quality is more in demand. To overcome that, Hadoop architecture and framework comes very handy.
Hadoop is a framework that helps in processing large datasets across multiple servers which is called as distributed processing of data. 
The second part of Hadoop is HDFS which is a distributed filesystem for data storage.
Anyone can run queries or do analysis on top of this data using Hadoop framework with ease and speed.

#### Cluster architecture
Different to traditional architectures, on a Hadoop cluster, the data within HDFS and the MapReduce system are made available on every single machine in the cluster. Benefits: it adds redundancy in case one machine in the cluster goes down, and it brings the data processing software into the same machines where data is stored, which speeds information retrieval.

#### Typical Hadoop architecture of an organization. 

![image](https://user-images.githubusercontent.com/35637485/39381635-69d97c98-4a28-11e8-95c2-66ac44b7b8c4.png)

#### Skew
Hadoop is typically designed in a way so that all data nodes while processing the jobs are utilized evenly for faster processing and fair usage. Since this is configurable; in case of bad configuration, skew can be high and positive i.e. data node(s) are not fairly utilized. This may end of performance issues and wastage of space.

To make the system robust and efficient, jobs should be monitored timely and reports should be published. Dashboards are provided by Hadoop distribution companies which helps in identifying the issue with clusters. 

#### Brief introduction about Apache Spark
Distributed Analytics engine that runs on Hadoop. It uses similar principals for general processing as MapReduce but in more efficient and better way.

Basic difference between MapReduce and Spark is, In MapReduce the code is taken to each data node and execution happens on each node. In case of Spark, entire data is loaded to memory and with the help of Resilient Distributed Datasets processing happens on memory. 
This way Spark is much faster than Hadoop and one of best utilized tool by Data scientists currently.
Spark has many flavours, Spark-SQL, Dataframes; user defined libraries, machine learning lib, graphX.

Spark Programs can be written in Scala, Python, R and JAVA.

The speaker showed how easily data can be loaded to Spark Dataframes and execute SQL like queries. Later that can be outputted in any known format.(JSON, Parquet, AVRO, TEXT, CSV).


#### References:
He shared few reference sites as below:

community.databricks.com
cloudera.com
hortonworks.com

### CONCLUSION-

With this meetup, we learned how different Hadoop and SPARK architecture are. Both are two different paradigms. Hadoop runs on each node and store intermediate files on data nodes whereas spark brings everything on memory and performs execution. 
Hadoop is primarily used by developers to build MapReduce applications whereas new tools such as Spark helps not so technical folks as well to do research and analysis. 
The small demo on SPARK-SQL helped us to know the benefit of spark i.e. how quickly data can be retrieved with few simple lines of code.

Overall the session was really knowledgeable and hopefully we can leverage this knowledge in future.

### OBSERVATION/COMMENTS –

Over the last 2 decades, the data has grown exponentially and the way tools and technology has developed its pushing us towards looking at the data as ocean of information which can be useful in identifying issues and problems.

Traditional systems which are still useful if data volume is less and not growing exponentially, can also look into new technologies as SPARK which can also be used in batch environments.

Hadoop which started as project in google to filter webpages till today when we have lots of tools and technology which can write long MapReduce Java code into few lines.
The servers and data storage are becoming easily available with cheaper options. For ex: S3 AWS or making own small clusters.  We should be more focused on new technology as spark and understand them along with performance gains and architecture standpoint. That will help in understanding not only development side of it but also architecture side as well where we can understand configurations to use the system to best of its capabilities.
