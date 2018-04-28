# ITMD 521 Spring 2018

## Week 6 assignment

### Objectives 

* Learn to setup install and configure latest release of Sqoop 
* Learn to install and use the Mysql Connector/J platform 
* Configure and execute a Sqoop import command 
* Configure and run a combined Hadoop/Sqoop job  

### Outcomes 

At the conclusion of this lab you will have gained experience installing, configuring, and executing Sqoop on top of a Hadoop cluster.   You will understand the nature of Sqoop’s connection to mysql as well as how to create a combined Hadoop/Sqoop job to execute importing and analysis in a single command. 

### Part I

Assuming the completion of week 05 homework and a working Sqoop instance and the running of the sample code from chapter 15 MaxWidgitID.java.  You are to use the same syntax to create a Sqoop application that will run the Sqoop import and the MapReduce code in a single class.  You will need to modify the sample code provided in the book.

1) If your last name is **A-K** the mode for temperature per your year 
1) If your last name **L-Z** the average temperature for the year 
1) Execute ```hadoop fs -cat <outputfile>```, place a screenshot of these results (using the year you were assigned) in deliverable 1.

### Part II 

Repeat the work above this time add a ```WHERE``` clause selecting only record number 1000-5000 and temperature between 2 and 20 Celcius. Execute ```hadoop fs -cat <outputfile>```, place the screenshot in delvierable 2. 

### Deliverable 1

![image](https://user-images.githubusercontent.com/35637485/36766362-23b8a1ea-1bfb-11e8-9fea-e0e7defe8362.png)

### Deliverable 2

![image](https://user-images.githubusercontent.com/35637485/36884006-a63bdd72-1da3-11e8-8770-e3eef6f657ab.png)

### Notes

#### Instructions for Deliverable 1:

* Year : 1985.txt
* Input Hadoop filename : /user/vagrant/widgets
* Output Hadoop filename : /user/vagrant/average_temperature
* Command to execute the jar file : hadoop jar avgTemp.jar AvgWidgetTemp -libjars $SQOOP_HOME/sqoop-1.4.7.jar
* Java and Jar files are uploaded https://github.com/illinoistech-itm/pshukla5/tree/master/itmd-521/Week-06/src/main/java
    * AvgWidgetTemp.java
    * avgTemp.jar

#### Instructions for Deliverable 2:

* Year : 1985.txt
* Sqoop extract is added to : https://github.com/illinoistech-itm/pshukla5/tree/master/itmd-521/Week-06/src/main/script
* Input Hadoop filename : /user/vagrant/week-06-sqoop
* Output Hadoop filename : /user/vagrant/avgTempSqp
* Command to execute the jar file : 
	* hadoop jar AvgWidgetTempPart2.jar AvgWidgetTempPart2 -libjars $SQOOP_HOME/sqoop-1.4.7.jar
* Java and Jar files are uploaded https://github.com/illinoistech-itm/pshukla5/tree/master/itmd-521/Week-06/src/main/java
    * AvgWidgetTempPart2.java
    * AvgWidgetTempPart2.jar
