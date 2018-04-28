# ITMD 521 Spring 2018

## Week 11 assignment

### Objectives 

* Install and configure industry standard VPN software 
* Configure and connect to industry standard VPN 
* Configure hadoop 2.6.5 installation to connect to a remote Hadoop cluster 
* Understand and analyze the effect of combiners on large MapReduce jobs

### Outcomes 

At the conclusion of this lab you will understand the reasons behind and be able to conenct to a remote network via a VPN.  You will have configured your Hadoop installation to connect to a remote cluster and will have run large scale jobs and analyzed the effect of Reducer classes upon the run time of the job.


### Part I

Assuming that you have succesfully installed, configured, and connected to the remote network via the VPN provided, you need to clone all of the configuration files (see note below) and properly configure you hadoop installation to connect to the remote Hadoop Cluster

Execute this command from your itmd521-cluster vagrant box ```hadoop fs -ls /user/controller/ncdc/1990/``` and take a screenshot of the output.  Place that image in **Deliverable 1** below. 

### Part II 

Compile the MaxTemperature sample code from the hadoop-book chapter 02.  Place the code into a jar file mt.jar (you may already have this step done, which in that case you can reuse mt.jar).  Run the command ```hadoop jar mt.jar MaxTemperature /user/controller/ncdc/YEAROFYOURBIRTH/YEAROFYOURBIRTH.txt``` and ```hadoop jar mt.jar MaxTemperatureWithCombiner /user/controller/ncdc/YEAROFYOURBIRTH/YEAROFYOURBIRTH.txt```

Repeat the above with this command:  ```hadoop jar mt.jar MaxTemperature /user/controller/ncdc/60-70/60-70.txt``` and ```hadoop jar mt.jar MaxTemperatureWithCombiner /user/controller/ncdc/60-70/60-70.txt```

Run each of these three times, capture a screenshot(s) of only these jobs (not others) include them in **Deliverable 2**   This will give you a total of 12 jobs

Note the execution time and graph all of the occurances (six job runs vs time to execute).  Place an image of that graph in **Deliverable 2**

### Part III

Using the textbook and the previous chapters, explain the effect of the reducer upon the outcomes of the job execution time from Part II in technical detail (It is faster or slower is not an acceptable answer).  Cite page sources from the book explaining how you arrived at your answer.

### Deliverable 1
![image](https://user-images.githubusercontent.com/35637485/38069475-370b022c-32dc-11e8-9629-7dee4383cda6.png)


### Deliverable 2
#### YEARBYBIRTH = 1989
#### Execution 1(Without Combiner):
![image](https://user-images.githubusercontent.com/35637485/38069562-9cc3a8da-32dc-11e8-8337-7da30803724d.png)
#### Execution 1(With Combiner):
![image](https://user-images.githubusercontent.com/35637485/38069563-9f2464de-32dc-11e8-8c65-4c5ea46bdca3.png)
#### Execution 2(Without Combiner):
![image](https://user-images.githubusercontent.com/35637485/38069565-a1d735ee-32dc-11e8-8dda-03d593b74da4.png)
#### Execution 2(With Combiner):
![image](https://user-images.githubusercontent.com/35637485/38069566-a41f5c64-32dc-11e8-9641-0f46e8e562c3.png)
#### Execution 3(Without Combiner):
![image](https://user-images.githubusercontent.com/35637485/38069569-a6253d1c-32dc-11e8-8046-9c0350ce6017.png)
#### Execution 3(With Combiner):
![image](https://user-images.githubusercontent.com/35637485/38069572-a899e3b8-32dc-11e8-81ef-aa7a447aa3df.png)
#### Execution 4(Without Combiner):
![image](https://user-images.githubusercontent.com/35637485/38069574-aad9735a-32dc-11e8-93f2-78d94438abef.png)
#### Execution 4(With Combiner):
![image](https://user-images.githubusercontent.com/35637485/38069577-adb8e916-32dc-11e8-89b8-6aa179992ffc.png)
#### Execution 5(Without Combiner):
![image](https://user-images.githubusercontent.com/35637485/38069578-aff7dd22-32dc-11e8-8804-15b4434e8521.png)
#### Execution 5(With Combiner):
![image](https://user-images.githubusercontent.com/35637485/38069579-b239f610-32dc-11e8-9a87-9d6e263a8cd7.png)
#### Execution 6(Without Combiner):
![image](https://user-images.githubusercontent.com/35637485/38069582-b48f7016-32dc-11e8-8ee6-f0e3f37c122a.png)
#### Execution 6(With Combiner)
![image](https://user-images.githubusercontent.com/35637485/38069585-b8fe6bca-32dc-11e8-9808-24eaa32a0bd2.png)

#### Graphs:

#### YearByBirth(1989)
![image](https://user-images.githubusercontent.com/35637485/38069703-5b9136ba-32dd-11e8-9996-9e90fe7d4524.png)

#### Decade(60-70)

![image](https://user-images.githubusercontent.com/35637485/38069709-65eabb22-32dd-11e8-859c-ad5f7b1ca7e4.png)

### Deliverable 3
#### Reference:

	Hadoop the definitive guide , 4th edition. 
	
		Chapter 2 MapReduce : Combiner Functions(PG : 34 on ebook)
		Chapter 7 How MapReduce Works : Shuffle and Sort(PG : 197 on ebook)
		
#### Shuffle and Sort with combiner.

Each Map tasks once complete, divides the data into partitions corresponding to reducers defined. Within each partition, the background process performs an in-memory sort by key, and if there is a combiner function, it is run on the output of the sort. Running the combiner function makes for a more compact map output, so there is less data to write to local disk and to transfer to the reducer. 

The reduce task needs the map output for its particular partition from several map tasks across the cluster. As soon as map tasks are finished, reduce tasks start copying the output.

#### 1989.txt

In our file 1989.txt,  

With no combiner when map process completes, each map sends corresponding data to reducer phase. i.e. reducer waits for all map tasks to finish then process and creates the final output file. Since the data transfer accross the network is huge, we are seeing 104426148 records or 1148690892 bytes coming as input to reducer taking > 6 minutes.

	job_1516079406019_0919(Without Combiner)==>

	Looking at job tracker, captured below information in Map reduce Framework, 

		total bytes shuffled across reducer are 1148690892
	
		total reduce input records are 104426148
	
With Combiner defined, each map process has its own local reducer called combiner which executes locally before sending the 
output to final reducer.
This brings down the overhead on final reducer to process everything. As shown in below, we are seeing 209 records or 5563 
bytes coming as input to reducer taking approx 4 minutes.

	job_1516079406019_0984 (With Combiner)==>

	In Map reduce Framework, 

		total bytes shuffled across reducer are 5563
	
		total reduce input records are 209
	


#### Setup Remote Hadoop Cluster Notes

Copy all *.xml and .sh files into your ```~/hadoop-2.6.5/etc/hadoop``` directory overwritting the defaults 

Copy the hosts file content into your ```/etc/hosts file``` -- note ```/etc/hosts``` is owned by root so you need to use ```sudo```
