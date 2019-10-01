import com.chekuri.floki.file
import com.chekuri.Tokenizer
import com.chekuri.floki.io.reader
import org.apache.logging.log4j.{Logger, LogManager}

import scala.io.{BufferedSource, Source}

object Mjolnir extends App {
  val logger: Logger = LogManager.getLogger(this.getClass)

  logger.info("Mjolnir is a library.")
  logger.info("Running Mjolnir will demo some of it's features.")

  def testHelloWorldCpp = {
    val helloWorldReader = new reader("src/test/resources/HelloWorld.cpp")
    var tokenizer: Tokenizer = new Tokenizer(helloWorldReader, false)
    var token: String = tokenizer.getToken
    while (!token.equals(null) && !(token.equals("\u0000"))) {
      if (token == "\n") {
        token = "< newline >"
      }
      logger.info(s" -- $token")
      token = tokenizer.getToken
    }

    helloWorldReader.close()
  }

  def testSampleSQLCode = {
    val sampleSQLReader = new reader("src/test/resources/SampleSQLCode.sql")
    var tokenizer: Tokenizer = new Tokenizer(sampleSQLReader, true)
    var token: String = tokenizer.getToken
    while (!token.equals(null) && !(token.equals("\u0000"))) {
      if (token == "\n") {
        token = "< newline >"
      }
      logger.info(s" -- $token")
      token = tokenizer.getToken
    }

    sampleSQLReader.close()
  }

  testHelloWorldCpp
  testSampleSQLCode
}
