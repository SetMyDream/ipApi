import com.fasterxml.jackson.databind.ObjectMapper

import scala.io.Source
import zio._
import zio.console._

import java.io.IOException


object Main extends App {

  def getIPAddressFromApi(apiUrl: String): Option[String] = {
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
      val node = jsonNode.get(key)
      if (node == null) {
        throw new NoSuchElementException(s"Key '$key' not found in JSON.")
      } else {
        node.asText()
      }
    } catch {
      case _: IOException => throw new NoSuchElementException("Error parsing JSON")
    }
  }

  private val apiUrl = "https://api.ipify.org/?format=json"
  
  val program: ZIO[Console, Throwable, Unit] = for {
    ipAddress <- ZIO.effectTotal(getIPAddressFromApi(apiUrl))
    _ <- ipAddress match {
      case Some(ip) => putStrLn(ip)
      case None => putStrLn("Error fetching IP address from the API.")
    }
  } yield ()

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] =
    program.exitCode
}
