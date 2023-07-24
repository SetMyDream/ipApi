import com.fasterxml.jackson.databind.ObjectMapper

import scala.io.Source
import zio.*
import zio.console.*

import java.io.IOException


object Main extends App {

  def getIPAddressFromApi: Task[Option[String]] = Task.effect {
    val apiUrl = "https://api.ipify.org/?format=json"
    try {
      val jsonString = Source.fromURL(apiUrl).mkString
      val ipAddress = parseJson(jsonString, "ip")
      Some(ipAddress)
    } catch {
      case _: IOException => None
    }
  }

  def parseJson(str: String, key: String): String = {
    val mapper = new ObjectMapper()
    try {
      val jsonNode = mapper.readTree(str)
      jsonNode.get(key).asText()
    } catch {
      case _: IOException => throw new RuntimeException("Error parsing JSON")
    }
  }

  val program: ZIO[Console, Throwable, Unit] = for {
    ipAddress <- getIPAddressFromApi
    _ <- putStrLn(ipAddress.getOrElse("Error fetching IP address from the API."))
  } yield ()

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] =
    program.exitCode
}
