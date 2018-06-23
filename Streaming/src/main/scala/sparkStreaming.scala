import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka._
import org.apache.spark.streaming.{Seconds, StreamingContext}
import kafka.serializer.StringDecoder

object sparkStreaming{
  def main(args : Array[String]) : Unit ={
    //println("Hello World")


    val conf = new SparkConf().setAppName("Demo Streaming Application").setMaster(args(0))
    val ssc = new StreamingContext(conf,Seconds(20))
    val kafkaParams = Map[String,String]("metadata.broker.list"->"nn01.itversity.com:6667,nn02.itversity.com:6667,rm01.itversity.com:6667")
    val topicSet = Set("StreamOnlineVK")
    val stream = KafkaUtils.createDirectStream[String,String,StringDecoder,StringDecoder](ssc,kafkaParams,topicSet)
    val messages = stream.map(s => s._2)
    val departmentMessages = messages.filter(msg => {
      val endPoint = msg.split(" ")(6)
      endPoint.split("/")(1) == "department"
    })
    val departments = departmentMessages.
      map(rec => {
        val endPoint = rec.split(" ")(6)
        (endPoint.split("/")(2), 1)
      })
    val departmentTraffic = departments.
      reduceByKey((total, value) => total + value)
    departmentTraffic.saveAsTextFiles("/user/vikasm415/deptwisetraffic/cnt")

    ssc.start()
    ssc.awaitTermination()


  }
}