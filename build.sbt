ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.2.1"

val ZioVersion = "1.0.12"

ThisBuild / libraryDependencies ++= Seq(
  "dev.zio" %% "zio" % ZioVersion,
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.15.1",
  "com.typesafe" % "config" % "1.4.1",
  "org.scalatest" %% "scalatest" % "3.2.15" % Test,
  "dev.zio" %% "zio-test" % ZioVersion % Test,
  "org.mock-server" % "mockserver-netty" % "5.11.2" % Test
)
ThisBuild / libraryDependencies ~= { deps =>
  deps.map(_.exclude("org.scala-lang.modules", "scala-xml_2.12"))
}

lazy val root = (project in file("."))
  .settings(
    name := "ipApi"
  )
