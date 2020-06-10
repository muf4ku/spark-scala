package demo

import java.util.Date

import com.fasterxml.jackson.databind.{DeserializationFeature, JsonNode, ObjectMapper}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

object TestSpark2 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("spark-app").setMaster("local[2]")

    val sc = new SparkContext(conf)

    val rdd = sc.textFile("D:\\mufaku\\data\\gdelt/xaa")

    println(new Date())

    rdd.map(f => parseStringToObject(f))
      .foreach(
        f => {
          println(f.get("SOURCEURL").asText())
        }
      )
    println(new Date())
  }

  def parseStringToObject(data: String): JsonNode = {
    var array = ArrayBuffer[String]()
    val source = Source.fromFile("D:\\mufaku\\data\\gdelt/header.csv")
    for (line <- source.getLines()) {
      array += line.split(",")(0)
      //      println(line)
    }
    source.close()

    var map: Map[String, String] = Map()

    val e = data.split("\t")
    var i = 0;
    for (dt <- e) {
      map += ("" + array(i) -> dt)
      i += 1
    }
    val mapper = new ObjectMapper() with ScalaObjectMapper
    mapper.registerModule(DefaultScalaModule)
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    val map2 = mapper.readValue[JsonNode](mapper.writeValueAsString(map))
    return map2
  }
}
