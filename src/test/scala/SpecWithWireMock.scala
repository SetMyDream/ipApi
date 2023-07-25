import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers
import zio.*
import zio.test.*
import zio.test.Assertion.*
import zio.test.environment.TestEnvironment
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.*
import org.scalatest.Outcome
import org.scalatest.matchers.should.Matchers.shouldBe

class SpecWithWireMock extends AnyFlatSpec with Matchers {

  // Define the WireMockServer
  private val wireMockServer = new WireMockServer()

  // Before running the tests, start the WireMockServer
  override def withFixture(test: NoArgTest): Outcome = {
    wireMockServer.start()
    try super.withFixture(test)
    finally wireMockServer.stop()
  }

  // Test the getIPAddressFromApi function with WireMock
  "getIPAddressFromApi" should "return the IP address when available" in {
    // Set up the mock response
    val mockResponse =
      """{"ip":"1.1.1.1"}"""
    stubFor(get(urlEqualTo("/?format=json"))
      .willReturn(aResponse()
        .withStatus(200)
        .withBody(mockResponse)))

    // Call the function under test
    val result = Main.getIPAddressFromApi

    // Assert the result
    result shouldBe Some("1.1.1.1")
  }
}
