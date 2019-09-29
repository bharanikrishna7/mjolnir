ThisBuild / version := "0.1.0"
ThisBuild / scalaVersion := "2.13.0"
ThisBuild / organization := "com.chekuri"
ThisBuild / developers := List(
  Developer(
    "bharanikrishna7",
    "Venkata Bharani Krishna Chekuri",
    "bharanikrishna7@gmail.com",
    url("https://github.com/bharanikrishna7/")
  )
)

lazy val root = (project in file("."))
  .settings(
    name := "mjolnir",
    // Library Dependencies for Log4j2
    libraryDependencies += "org.apache.logging.log4j" % "log4j-api" % "2.11.2",
    libraryDependencies += "org.apache.logging.log4j" % "log4j-core" % "2.11.2",
    // Libraries for testing
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.8",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test",
    // Utility Library (floki)
    libraryDependencies += "floki" % "floki" % "1.2.0" from "https://github.com/bharanikrishna7/floki/releases/download/1.2.0/floki_2.13-1.2.0.jar"
  )
