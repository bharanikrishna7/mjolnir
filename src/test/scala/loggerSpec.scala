
import org.apache.logging.log4j.{LogManager, Logger}
import org.scalatest.{FlatSpec, Matchers}

class loggerSpec extends FlatSpec with Matchers {
  /**
    * This is a sample test case. Please refer:
    * - selecting style: http://www.scalatest.org/user_guide/selecting_a_style
    * - flatSpec Documentation: http://doc.scalatest.org/3.0.1/#org.scalatest.FlatSpec
    */
  "Logger" should "initialize properly" in {
    try {
      val logger: Logger = LogManager.getLogger()
      logger.info(s"Logger working as expected")
      assert(true)
    } catch {
      case exception: Exception => fail("There were exceptions when trying to initialize/use logger.")
        assert(false)
    }
  }
}
