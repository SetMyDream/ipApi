ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.2.1"

val ZioVersion = "1.0.12"

ThisBuild / libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.15" % Test,
  "dev.zio" %% "zio" % ZioVersion,
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.15.1",
  "dev.zio" %% "zio-test" % ZioVersion % Test

)


lazy val root = (project in file("."))
  .settings(
    name := "ipApi"
  )
