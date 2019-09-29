import com.chekuri.floki.file
import com.chekuri.Tokenizer
import com.chekuri.floki.io.reader
import org.apache.logging.log4j.{Logger, LogManager}

import scala.io.{BufferedSource, Source}

object Mjolnir extends App {
  val logger: Logger = LogManager.getLogger(this.getClass)

  logger.info("Mjolnir works best when used with companion.")
  val listFiles: List[String] =
    file.listFilesInDirectory("src/test/resources/")
  for (aFile <- listFiles) {
    logger.info(s" -- $aFile")
  }

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
