# ITMD 521 Spring 2018

## Week 12 assignment

### Objectives 

* Understand the effect (positive and negative) of using multiple reducers on an MR job 
* Understand the effect of using Intermediate Compression on an MR job
* Understand the benefit of using custom counters in the output of an MR job 
* Understand how to modify and change the jobname of an MR job

### Outcomes 

At the conclusion of this lab you will have run through a matrix of MR jobs with various optimizations relating to the number of reducers, use of intermediate compression, and the use of a combiner class.  In addition you will modify the sample MR code to include try/catch logic and a custom counter bad and invalid records.


### Part I

Using the single year of your birth, modify the sample MaxTemperature class to include a custom counter to count all bad (malformed) records and count all invalid ranged records (for instance anyhting greater than 50.0 celsius).  Disply the output of these two counters at the end of your MR output and take a screenshot of the output.  Place that image in **Deliverable 1** below. Submit your modified code into week 12 Github folder as well.

### Part II - Reducers

Using the dataset for the year you were born, run the MaxTemperature and MaxTempertature jobs each using 1, and then 2 reducers; four jobs total.

Capture a screenshot(s) of only these jobs (not others) include them in **Deliverable 2**   

Note the execution time and graph all of the occurances.  Place an image of that graph in **Deliverable 2**

Using the textbook and the previous chapters, explain the effect of the reducer upon the outcomes of the job execution time from Part II in technical detail (It is faster or slower is not an acceptable answer).  **Cite page sources from the book explaining how you arrived at your answer.**

### Part III - Reducers Large

Using the linux command [md5](https://en.wikipedia.org/wiki/MD5 "md5") to take a hash of your Hawk ID: ```echo "hajek" | md5sum```.  Take a screenshot of this output and include it in **Deliverable 3**. 

Based on the output string use two datasets mentioned below:

1) If first character is even number: use 50.txt
1) If first character is odd number use: 60.txt
1) If first character is a letter use: 60-70.txt
1) In addition to the above everyone needs to use 60-90.txt as well.

* Enable **intermediate compression**, Page 118 of epub, for all of your MR jobs in this section.
* Compile your code to contain the job.setName("Initials here and a description") value
* Run 8 jobs on each dataset (8x2=16 total), 
* Run your first dataset assigned above: MaxTemperature
    + with 1, 2, 4, 8 reducers
* Run your first dataset assigned above: MaxTemperatureWithCombiner
    + with 1, 2, 4, 8 reducers
*  * Run your second dataset assigned above: MaxTemperature
    + with 1, 2, 4, 8 reducers
* Run your second dataset assigned above: MaxTemperatureWithCombiner
    + with 1, 2, 4, 8 reducers  

Capture a screenshot(s) of only these jobs (not others) include them in **Deliverable 3**   

Note the execution time and graph all of the occurances.  Place an image of those graphs in **Deliverable 3**

Using the textbook and the previous chapters, explain the effect of the reducer upon the outcomes of the job execution time from Part II in technical detail (It is faster or slower is not an acceptable answer).  **Cite page sources from the book explaining how you arrived at your answer.**  

**Submit your *.java files to your repo in week 12 as well.**

Submit your Github repo URL to blackboard by 11:59 pm April 5th.

### Deliverable 1

As shown in image, BADRANGE is populated as 2357125 for Year 1989.
Malformed count is 40318

![image](https://user-images.githubusercontent.com/35637485/38426286-e1b400ac-397b-11e8-8a40-a8eae1208300.png)


#### Snapshot added for job execution

![image](https://user-images.githubusercontent.com/35637485/38345389-8970c2f4-3854-11e8-86ec-3c05c96b0da6.png)


### Deliverable 2

Part 2

1. application_1516079406019_1661
   Without combiner(Reducer 1)

![image](https://user-images.githubusercontent.com/35637485/38345652-e5fb9520-3855-11e8-8388-515f7e2e5558.png)


2. application_1516079406019_1665
   With Combiner(Reducer 1)
![image](https://user-images.githubusercontent.com/35637485/38345658-ebcba65c-3855-11e8-890a-58f471e144f6.png)


3. application_1516079406019_1668
   Without combiner(Reducer 2)
![image](https://user-images.githubusercontent.com/35637485/38345661-ef37288e-3855-11e8-8dfb-903af45b6944.png)


4. application_1516079406019_1669
   With Combiner(Reduce 2)
![image](https://user-images.githubusercontent.com/35637485/38345669-f1baec3a-3855-11e8-9780-10370448a04e.png)

#### Explanation of effect of reducers:

    Reference : 
    Chapter 2: MapReduce : Pg 30-32(Data Flow) 

When multiple reducers are provided, the map tasks partition their output each creating one partition for each reduce task.

Bases on the description above, in our case below; first process creates 1 file whereas 2nd process creates 2 files.

1 file with 1 reducer:

vagrant@itmd521x-cluster:~/pragyaAssignments/hadoop-521/github/pshukla5/itmd-521/Week-12/mr-cluster/target$ hadoop fs -ls /output/pshukla/1989-1

Found 2 items

-rw-r--r--   3 vagrant supergroup          0 2018-04-07 00:17 /output/pshukla/1989-1/_SUCCESS

-rw-r--r--   3 vagrant supergroup          9 2018-04-07 00:17 /output/pshukla/1989-1/part-r-00000


2 files with 2 reducers:( 1 file is 0 byte as output record was just 1)

vagrant@itmd521x-cluster:~/pragyaAssignments/hadoop-521/github/pshukla5/itmd-521/Week-12/mr-cluster/target$ hadoop fs -ls /output/pshukla/1989-2

Found 3 items

-rw-r--r--   3 vagrant supergroup          0 2018-04-07 00:30 /output/pshukla/1989-2/_SUCCESS

-rw-r--r--   3 vagrant supergroup          9 2018-04-07 00:30 /output/pshukla/1989-2/part-r-00000

-rw-r--r--   3 vagrant supergroup          0 2018-04-07 00:30 /output/pshukla/1989-2/part-r-00001


When data shuffles from map to reduce task, each reduce task is fed by many map tasks. 

So in our scenario 2; when 2 reducers are set each split from map is copied to both the reducers causing more time to execute the process.

When 1 reducer is provided, as shown shuffled maps and merged maps are 61.

        Shuffled Maps =61
		Failed Shuffles=0
		Merged Map outputs=61

Whereas When 2 reducers are provided, as shown shuffled maps and merged maps doubles to 122.

        Shuffled Maps =122
		Failed Shuffles=0
		Merged Map outputs=122
        
This shuffling increases the processing time when reducers are increased. Same is also deplicted in Graph plot as well.

Graph plot:

![image](https://user-images.githubusercontent.com/35637485/38345902-39ad79b2-3857-11e8-82e5-93c455289c0c.png)


### Deliverable 3

After executing MD5. First character is numeric and even, 

##### 50.txt

![image](https://user-images.githubusercontent.com/35637485/38345934-8357c522-3857-11e8-837e-d621d9d9e501.png)

With and without combiner:

Job with combiner has name ended with combiner. For ex: Max-Temperature-combiner

With 1 reducer:
![image](https://user-images.githubusercontent.com/35637485/38442760-f5859bb2-39ae-11e8-878c-1cacf5e413f4.png)

With 2 reducers:
![image](https://user-images.githubusercontent.com/35637485/38442771-ffb36baa-39ae-11e8-807b-c730f418e213.png)

With 4 reducers:
![image](https://user-images.githubusercontent.com/35637485/38442783-0e567c38-39af-11e8-8df3-db4b6ebee7cb.png)

With 8 reducers:
![image](https://user-images.githubusercontent.com/35637485/38442789-14b74486-39af-11e8-8a87-1e9e3e08a3ea.png)

##### Graph:

![image](https://user-images.githubusercontent.com/35637485/38444254-3d1cf74a-39b4-11e8-9b23-0b60c04cdebc.png)


##### 60-90.txt

With 1 reducer:

![image](https://user-images.githubusercontent.com/35637485/38474898-67b6bac0-3b69-11e8-83b1-7a6d10e945f1.png)


With 2 reducer:

![image](https://user-images.githubusercontent.com/35637485/38474904-75bd5d36-3b69-11e8-88b3-c1b098539a53.png)

With 4 reducer:
![image](https://user-images.githubusercontent.com/35637485/38474911-8304169c-3b69-11e8-804e-970d501e5888.png)


With 8 reducer:
![image](https://user-images.githubusercontent.com/35637485/38474917-8ccaa7ea-3b69-11e8-81c3-e0dbbaf818c4.png)


##### Graph:

![image](https://user-images.githubusercontent.com/35637485/38475118-3fbfa3fe-3b6b-11e8-9030-980caeaf1a99.png)


### Explanation of Compression on above executions:

	Reference : Chapter 5 : Hadoop I/O ( Pg 100-108) Compression
	
File compression is helpful in cases when we have large datasets. Compression reduces space needed and speeds up data transfer across the network or to do from disk.

When compression is enabled to map output, intermediate map output files are written to disk as compressed which reduces data movement accross reducers.

As shown in image below, shuffling and input and output of reducer process shows significant high records.

![image](https://user-images.githubusercontent.com/35637485/38452337-eda1cd86-3a07-11e8-9b91-2bd742f86b86.png)


When Combiner is enabled, this further helps in reducing the data as map outputs the data to combiner and then data is compresses and written to disk.

As shown in image below, shuffling and input and output of reducer process shows very low record count once compression with combiner is enabled.

![image](https://user-images.githubusercontent.com/35637485/38452333-d180ac3a-3a07-11e8-849f-cc7e4b1d815e.png)

Multiple executions to show the effect of compression with or without compression are plotted in graphs above.


In case 2, the job executions are very much uneven because of how YARN scheduled the jobs.

	Reference Chapter 4 : YARN(Scheduler Options)

With the Fair Scheduler, resource allocation happens dynamically between jobs. Any large job when running gets all the resources it needs. When next small job kicks off, it is allocated half of cluster resources to get its fair trade.
There is a lag between the time the second job starts and when it receives its fair share, since it has to wait for resources to free up as containers used by the first job complete. After the small job completes and no longer requires resources, the large job goes back to using the full cluster capacity again. The overall effect is both high cluster utilization and timely small job completion.


### Submit your *.java files to your repo in week 12 as well.**

src/main/java contains all java files.

MaxTemperatureMapper.java => Mapper logic.

MaxTemperatureReducer.java ==> Reducer Logic

MaxTemperatureRed-8.java => MaxTemperature all no-combiner main code. (reducers 0,2,4,8)

MaxTemperatureWithCombiner-8.java => MaxTemperature all with-combiner main code. (reducers 0,2,4,8)
