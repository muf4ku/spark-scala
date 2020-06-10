package demo


//import com.fasterxml.jackson.databind.ObjectMapper

import com.fasterxml.jackson.databind.{DeserializationFeature, JsonNode, ObjectMapper}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import org.apache.spark.{SparkConf, SparkContext}

object TestSpark1 {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("spark-app").setMaster("local[2]")

    val sc = new SparkContext(conf)

    val rdd = sc.textFile("D:\\mufaku\\data\\covid")

    rdd.map(f=>parseStringToObject(f))
      .foreach(
        f => {
          println(f.get("email").asText())
        }
      )
  }
  def parseStringToObject( data:String ) : JsonNode = {
    val mapper = new ObjectMapper() with ScalaObjectMapper
    mapper.registerModule(DefaultScalaModule)
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    val map=mapper.readValue[JsonNode](data)
    return map
  }
}
