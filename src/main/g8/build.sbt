import Dependencies._

ThisBuild / scalaVersion := "2.13.3"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "$name$",
    libraryDependencies ++= Seq(
      spec2 % Test,
      logback,
      pureConfig,
      refined,
      circe,
      newtype,
      flyway,
      doobie,
      catsEffect
    ),
    libraryDependencies ++= http4s,
    addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.10.3"),
    addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1"),
    scalacOptions ++= Seq(
      "-deprecation",
      "-encoding",
      "UTF-8",
      "-language:higherKinds",
      "-language:postfixOps",
      "-feature",
      "-Xfatal-warnings"
    )
  )

enablePlugins(FlywayPlugin)
flywayUrl := "jdbc:postgresql://localhost:54340/exchange"
flywayUser := "vder"
flywayPassword := "gordon"

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
