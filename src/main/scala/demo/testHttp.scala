package demo
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpResponse, HttpRequest}
import akka.stream.ActorMaterializer
import scala.concurrent.duration._

import scala.util.{Failure, Success}

object testHttp {
  def main(args: Array[String]) {
    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = system.dispatcher


    val url = "http://192.168.24.91:9200/test-assessment/_search?size=0"
    println(url)
    val responseFuture = Http().singleRequest(HttpRequest(uri = url))
    var nodeCount: Int = 0
    responseFuture.andThen {
      case Success(resp: HttpResponse) => {
        //println(resp.status.intValue())
        //println(resp.status.defaultMessage())
        //val aaaa = resp.entity.dataBytes.runFold(ByteString(""))(_ ++ _)
        //println(aaaa.value.get.get.decodeString("UTF-8"))
        //println(resp.entity.dataBytes.via(Framing.delimiter(ByteString("\n"),maximumFrameLength = 256,allowTruncation = true)).map(_.utf8String))
        val entity = resp.entity.toStrict(5 seconds).map(_.data.decodeString("UTF-8"))
        println(entity)
        println(entity.value.getOrElse("none value"))
        //nodeCount=JsonUtil.nodeCount(entity.value.get.get)
      }
      case Failure(ex:Exception) => {
        println("http request error:"+ex.getMessage)
      }
    }
  }
}
