package demo

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.ActorMaterializer
import org.apache.http.impl.client.BasicResponseHandler

import scala.util.{Failure, Success}
import scala.concurrent.Future

object JerseyClientTest {
  def main(args: Array[String]): Unit = {



    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.dispatcher

    val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(uri = "http://192.168.24.91:9200/test-assessment/_search?size=0"))



    responseFuture
      .onComplete {
        case Success(res) => println(Unmarshal(res.entity).to[String].)
        case Failure(_)   => sys.error("something wrong")
      }
  }
}
