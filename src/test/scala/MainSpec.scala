import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers
import org.scalatest.matchers.should.Matchers.shouldBe
import zio.*
import zio.test.*
import zio.test.Assertion.*
import zio.test.environment.{TestConsole, TestEnvironment}

class MainSpec extends AnyFlatSpec with Matchers {

  "parseJson" should "return the value for the specified key" in {
    val jsonString = """{"ip":"1.1.1.1"}"""
    val key = "ip"

    val result = Main.parseJson(jsonString, key)

    result shouldBe "1.1.1.1"
  }

  it should "throw an exception if the key is not found case bad key" in {
    val jsonString = """{"ip":"1.1.1.1"}"""
    val key = "wrong_key"

    assertThrows[NoSuchElementException] {
      Main.parseJson(jsonString, key)
    }
  }

  it should "throw an exception if the key is not found case bad json" in {
    val jsonString = """{"ips":"1.1.1.1"}"""
    val key = "ip"

    assertThrows[NoSuchElementException] {
      Main.parseJson(jsonString, key)
    }
  }
}
