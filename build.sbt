ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.2.1"


ThisBuild / libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.15" % Test,
  "dev.zio" %% "zio" % "1.0.12",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.15.1"

)


lazy val root = (project in file("."))
  .settings(
    name := "ipApi"
  )
