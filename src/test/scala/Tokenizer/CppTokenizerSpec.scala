package Tokenizer

import org.scalatest.FlatSpec
import com.chekuri.floki.io.reader
import com.chekuri.Tokenizer
import org.apache.logging.log4j.{Logger, LogManager}

import scala.collection.mutable.ListBuffer

class CppTokenizerSpec extends FlatSpec {
  val logger: Logger = LogManager.getLogger(this.getClass)

  "Tokenizer" should "correctly grab tokens from a C++ file" in {
    val helloWorldReader = new reader("src/test/resources/HelloWorld.cpp")
    val tokenizer: Tokenizer = new Tokenizer(helloWorldReader, false)
    var token: String = tokenizer.getToken
    var actual: ListBuffer[String] = new ListBuffer[String]()

    while (!token.equals(null) && !(token.equals("\u0000"))) {
      if (token.trim != "") {
        actual.append(token)
      }
      if (token == "\n") {
        token = "< newline >"
      }
      logger.info(s" -- $token")
      token = tokenizer.getToken
    }

    helloWorldReader.close()
    assert(actual.toList == expected)
  }

  val expected: List[String] = List(
    "#",
    "include",
    "<",
    "iostream",
    ">",
    "using",
    "namespace",
    "std",
    ";",
    "class",
    "Message",
    "{",
    "public",
    ":",
    "void",
    "display",
    "(",
    ")",
    "{",
    "cout",
    "<<",
    "\"Hello World\\n\"",
    ";",
    "}",
    "}",
    ";",
    "int",
    "main",
    "(",
    ")",
    "{",
    "Message",
    "c",
    ";",
    "c",
    ".",
    "display",
    "(",
    ")",
    ";",
    "return",
    "0",
    ";",
    "}"
  )
}
