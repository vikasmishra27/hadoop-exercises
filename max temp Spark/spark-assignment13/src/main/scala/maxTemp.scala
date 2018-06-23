/**
  * Import Packages
  */

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object maxTemp{
  def main(args : Array[String]): Unit = {
    /**
      * Set Conf and SparkContext
      */
    val conf = new SparkConf().setAppName("maxTemp-Longitude-PSHUKLA5").setMaster("local[2]").set("spark.executor.memory","1g")
    val sc = new SparkContext(conf)
    /**
      * Read File name as argument. If not 2 Exit
      */
    if(args.length != 2 ){
        print("InSufficient parameters.\n" +
          "spark-submit sparka-9_2.11-0.1.jar <<INPUT FILE>> <<OUTPUT FILE>>\n" +
          "..Exiting\n")
        System.exit(0)
    }

    //Read Text File
    val readText = sc.textFile(args(0))
    //Filter invalid temperature--> Temperature not 9999 and quality matches 01459
    val filteredText = readText.map(rec => rec.split("\t"))
                              .filter(rec => rec(1).toInt != 9999 && rec(2).matches("[01459]"))
    //Identify the range for each longitude
    val keyVal = filteredText.map(rec => (rec(0).toDouble/1000,rec(1),((rec(0).toInt/1000)/10)*10))
    //Pull maximum Temperature for each longitude group
    val groupMax = keyVal.map(rec=>(rec._3.toInt,rec._2.toFloat))
          .reduceByKey((k,v) => Math.max(k,v)).sortBy(rec=>rec._1)
    //Write group range and maximum temperature to output
    groupMax.map(rec=>{
      val first = rec._1
      val second = rec._2

      // combine all into 1 partition and write to filename provided.
    if(first >= 0){
        first.toString + " to " + (first + 9).toString + ".999 ========> " + second
      } else { (first - 9).toString  + ".999" + " to "  + first.toString + " ========> " + second}
    }).coalesce(1).saveAsTextFile(args(1))
  }
}
