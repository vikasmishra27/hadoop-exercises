# ITMD 521 Spring 2018

## Week 13 Spark Assignment

### Objectives 

* Understand how to deploy a local Spark Cluster
* Understand the differences and similarities between MapReduce and Spark
* Understand the SparkContext concept 
* Understand how to use PySpark and Spark-Shell to process big data into meaningful results

### Outcomes 

At the conclusion of this lab you will have a basic understanding of the terminology and concepts of Spark and its advantages and disadvantages over the MR platform


### Part I

In chapter 19 of the textbook, you are to install the Spark open-source cluster-computing framework on your local Hadoop Cluster (Vagrant Box). 

Using these datasets, as a basis using your python script from earlier in the semester, create a hybrid data set containing only, **longitude**, **air temperature**, and **air temperature quality code**, seperated by a **TAB**, **/t**

* A-E 1997.txt 1997.txt.xz
* F-R 1950.txt 1950.txt.xz
* S-Z 1985.txt 1985.txt.xz

Provide a spark script (in either python or scala) that will find the max temperature per 10 degree of longitude (agregate the 

ranges so 20.000 to 29.999, 30.000 to 39.999, for example)


### Deliverable 1

In your Week-13 folder include all scripts and program instructions needed to retrieve and reproduce your results.  Upload your part-r-0000 file (results) as well)  Write and instruction/assumptions needed in the Assumption section below.

#### Python Script and Details

https://github.com/illinoistech-itm/pshukla5/blob/master/itmd-521/Week-13/python/extract_records.py

This script will process 1985.txt and create 1985_week13.txt.

Columns : LONGITUDE, AIR_TEMP, QUAL_CD4

#### Spark Scala Code
https://github.com/illinoistech-itm/pshukla5/blob/master/itmd-521/Week-13/spark-assignment13/src/main/scala/maxTemp.scala

The details about scala code are written in comments.

#### Spark Scala build and Execution details

To create jar file following steps needs to be followed:

1. goto Week-13/spark-assignment13

2. Execute sbt package. It should create the jar file as available in

    Week-13/spark-assignment13/target/scala-2.11/sparka-9_2.11-0.1.jar
    
3. Once created execute as shown below:

    spark-submit sparka-9_2.11-0.1.jar 1985_week13.txt \<output file\>

#### Output file

output file is available in below location:

https://github.com/illinoistech-itm/pshukla5/blob/master/itmd-521/Week-13/spark-assignment13/target/scala-2.11/assignment_output/part-00000

First Column => range of longitudes 

seperated by arrow

Second Column => Maximum temperature for the group

For Ex:

160 to 169.99 ========> 600.0

### Assumptions and Instructions
1. sbt console is available and installed.

2. build.sbt is created to import packages. https://github.com/illinoistech-itm/pshukla5/blob/master/itmd-521/Week-13/spark-assignment13/build.sbt

3. "sbt update" is executed in ubuntu to import all required packages.

4. scala version : 2.11.8

   Spark-Core Version : 2.2.0
   
5. As per the requirement statement i.e. find the max temperature per 10 degree of longitude; first column is range every 10 degree of longitute and second column is maximum temperature in that range.
6. In case of -ve longitude, range is selected on lower side; for ex : -1 to -9.99, -10 to 19.99 and so on.

