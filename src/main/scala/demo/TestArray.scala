package demo

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

object TestArray {
  def main(args: Array[String]): Unit = {
    var array = ArrayBuffer[String]()
    val source = Source.fromFile("D:\\mufaku\\data\\gdelt/header.csv")
    for (line <- source.getLines()) {
      array += line.split(",")(0)
      //      println(line)
    }
    source.close()

    print(array(0))

  }
}
