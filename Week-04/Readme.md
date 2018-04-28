# ITMD 521 Spring 2018

## Week 4 assignment

### Part I

* Insert the assigned datasets below into your local hadoop cluster 
  + A-E 1997.txt [1997.txt.xz](https://drive.google.com/open?id=0Bys2c__9q7eBNzhMNXdUSFpNYlk)
  + F-R 1950.txt [1950.txt.xz](https://drive.google.com/open?id=0Bys2c__9q7eBQVJnbXFMSkstMTQ)
  + S-Z 1985.txt [1985.txt.xz](https://drive.google.com/open?id=0Bys2c__9q7eBUUN4TkllRXFxYTg)
  + Note that the files are compressed with xzip - you will need to use the vagrant shared file options to get them into your vagrant box - see Week 4 Tuesday video 
* Insert the data into this directory structure /user/$USER/ncdc/19XX/  (with XX being your year)
* Compile the source code in chapter-02 of the text book sample code into a single jar file named: ```mt.jar```
  + Place all your scripts (but not the datafile!) into the Week-04 github folder
* Run the MaxTemperature class against your dataset
* Display the content of the part-r-00000 and capture that in a screenshot to be displayed below

### Part II

* In your Vagrant box, install mysql-server and give it the password: **itmd521**
* Install and configure the proper mysql-Java connector - [mysql/J connector](https://dev.mysql.com/downloads/connector/j/)
* Write a script in Python to parse the dataset given you (using schemea in Chapter 02) and insert this data into a database named: **521** and a table named: **records**
  + Assume that the dataset.txt file is in the same directory as the script being executed
* Write a Java Application that will perform the same funtionality as the MapReduce program to find the Max Temperature in SQL.
* Provide any instructions or additional dependencies needed at the bottom of this document
  + We will run your code to see if the results are as delvivered.
  + Place all your scripts (but not the datafile!) into the Week-04 github folder
* take a screenshot of the output 


### Deliverable 1

![image](https://user-images.githubusercontent.com/35637485/35957178-d73981d6-0c5f-11e8-8ddf-04fa85886ca8.png)

### Deliverable 2

![image](https://user-images.githubusercontent.com/35637485/35994402-ec60f1ea-0cd5-11e8-8460-5839a8d37e38.png)

### Additional Notes

#### Execution:
#### Deliverable 1:

Jar file can be created using script : itmd-521/Week-04/MR-maxTemp/script/maxTemp.sh

This job will create the runnable mt.jar in src/main/java folder. In this project, the jar file is copied to target directory.

##### Command to execute:

hadoop jar mt.jar MaxTemperature ( HDFS INPUT dir ) ( HDFS Output Dir )
  
#### Deliverable 2:

Java program to find the Max Temperature in SQL

Name : mysql-maxTemp

Directory : src/

Java Program : maxTemp.java

Dependencies are imported from Manifest.txt file. Its on connector file mysql-connector-java-5.1.45-bin.jar available in src directory.

1985.txt file is hardcoded in java program. It should be kept in same folder as of program.

##### Command to execute:

javac *.java // Create class file from Java program

jar cfm mysql_mt.jar Manifest.txt *.class // Creating Jar file referencing Manifest file

java -jar mysql_mt.jar // Execute the program to get the output
  
