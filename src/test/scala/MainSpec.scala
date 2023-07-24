import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers
import org.scalatest.matchers.should.Matchers.shouldBe
import zio._
import zio.test._
import zio.test.Assertion._

class MainSpec extends AnyFlatSpec with Matchers {

  "parseJson" should "return the value for the specified key" in {
    val jsonString = """{"ip":"1.1.1.1"}"""
    val key = "ip"

    val result = Main.parseJson(jsonString, key)

    result shouldBe "1.1.1.1"
  }

  "getIPAddressFromApi" should "return the IP address from the API response" in {
    // Mock the API response with a sample JSON string
    val mockResponse =
      """{"ip":"188.163.96.72"}"""

    // Call the function with the mock response
    val result = Main.getIPAddressFromApi

    // Assert the result is as expected using ZIO Test's assert method
    assertM(result)(isSome(equalTo("188.163.96.72")))
  }
}
