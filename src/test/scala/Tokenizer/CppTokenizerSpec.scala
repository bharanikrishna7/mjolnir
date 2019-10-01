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
      if (token == "\n") {
        token = "< newline >"
      }
      if (token.trim != "") {
        actual.append(token)
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
    "< newline >",
    "< newline >",
    "using",
    "namespace",
    "std",
    ";",
    "< newline >",
    "< newline >",
    "class",
    "Message",
    "< newline >",
    "{",
    "< newline >",
    "public",
    ":",
    "< newline >",
    "< newline >",
    "void",
    "display",
    "(",
    ")",
    "{",
    "< newline >",
    "cout",
    "<<",
    "\"Hello World\\n\"",
    ";",
    "< newline >",
    "}",
    "< newline >",
    "}",
    ";",
    "< newline >",
    "< newline >",
    "int",
    "main",
    "(",
    ")",
    "< newline >",
    "{",
    "< newline >",
    "Message",
    "c",
    ";",
    "< newline >",
    "c",
    ".",
    "display",
    "(",
    ")",
    ";",
    "< newline >",
    "< newline >",
    "return",
    "0",
    ";",
    "< newline >",
    "}"
  )
}
