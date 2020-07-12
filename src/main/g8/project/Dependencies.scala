import sbt._

object Dependencies {
  lazy val catsEffVersion = "2.1.3"
  lazy val catsVersion = "2.1.1"
  lazy val doobieVersion = "0.8.8"
  lazy val flywayVersion = "6.4.4"
  lazy val newtypeVersion = "0.4.4"
  lazy val pureConfigVersion = "0.13.0"
  lazy val refinedVersion = "0.9.14"
  lazy val CirceVersion = "0.13.0"
  lazy val Http4sVersion = "0.21.5"
  lazy val LogbackVersion = "1.2.3"
  lazy val Specs2Version = "4.10.0"
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.1.1"
  lazy val logback = "ch.qos.logback" % "logback-classic" % LogbackVersion
  lazy val pureConfig =
    "com.github.pureconfig" %% "pureconfig" % pureConfigVersion
  lazy val refined = "eu.timepit" %% "refined" % refinedVersion
  lazy val circe = "io.circe" %% "circe-generic" % CirceVersion
  lazy val newtype = "io.estatico" %% "newtype" % newtypeVersion
  lazy val flyway = "org.flywaydb" % "flyway-core" % flywayVersion
  lazy val http4s = Seq(
    "org.http4s" %% "http4s-blaze-client" % Http4sVersion,
    "org.http4s" %% "http4s-blaze-server" % Http4sVersion,
    "org.http4s" %% "http4s-circe" % Http4sVersion,
    "org.http4s" %% "http4s-dsl" % Http4sVersion
  )
  lazy val spec2 = "org.specs2" %% "specs2-core" % Specs2Version
  lazy val doobie = "org.tpolecat" %% "doobie-core" % doobieVersion
  lazy val catsEffect = "org.typelevel" %% "cats-effect" % catsEffVersion
}
