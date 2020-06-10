package demo

import com.fasterxml.jackson.databind.{DeserializationFeature, JsonNode, ObjectMapper}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import org.apache.spark.{SparkConf, SparkContext}
import org.elasticsearch.spark.rdd.EsSpark

object SparkElasticTest {


  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("spark-app").setMaster("local[2]")
    conf.set("spark.es.nodes", "192.168.24.91")
    conf.set("spark.es.port", "9200")
    conf.set("spark.es.nodes.wan.only", "false")
    conf.set("spark.es.resource", "test-assessment")

    val sc = new SparkContext(conf)


    //    val pairRdd=EsSpark.esRDD(sc)
//    pairRdd.foreach(f=>println(f))

  }
}
