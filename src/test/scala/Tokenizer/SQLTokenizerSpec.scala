package Tokenizer

import org.apache.logging.log4j.{Logger, LogManager}
import org.scalatest.FlatSpec
import com.chekuri.floki.io.reader
import com.chekuri.Tokenizer

import scala.collection.mutable.ListBuffer

class SQLTokenizerSpec extends FlatSpec {
  val logger: Logger = LogManager.getLogger(this.getClass)

  "Tokenizer" should "correctly grab tokens from a SQL file" in {
    val sampleSQLReader = new reader("src/test/resources/SampleSQLCode.sql")
    val tokenizer: Tokenizer = new Tokenizer(sampleSQLReader, true, false)
    var token: String = tokenizer.getToken
    var actual: ListBuffer[String] = new ListBuffer[String]()

    while (!token.equals(null) && !(token.equals("\u0000"))) {
      if (token.trim != "" && token != "\n") {
        actual.append(token)
      }
      if (token == "\n") {
        token = "< newline >"
      }
      logger.info(s" -- $token")
      token = tokenizer.getToken
    }

    sampleSQLReader.close()
    assert(actual.toList == expected)
  }

  val expected: List[String] = List(
    "CREATE",
    "TABLE",
    "employees",
    "(",
    "id",
    "INT",
    "(",
    "11",
    ")",
    "PRIMARY",
    "KEY",
    "AUTO_INCREMENT",
    ",",
    "fname",
    "VARCHAR",
    "(",
    "50",
    ")",
    ",",
    "mname",
    "VARCHAR",
    "(",
    "50",
    ")",
    ",",
    "lname",
    "VARCHAR",
    "(",
    "50",
    ")",
    "NOT",
    "NULL",
    ",",
    "email",
    "VARCHAR",
    "(",
    "50",
    ")",
    "NOT",
    "NULL",
    ")",
    "SELECT",
    "id",
    ",",
    "fname",
    ",",
    "lname",
    ",",
    "email",
    "FROM",
    "employees",
    "WHERE",
    "fname",
    "IS",
    "NOT",
    "NULL",
    "AND",
    "email",
    "LIKE",
    "'%@bmail.com'",
    ";"
  )
}
