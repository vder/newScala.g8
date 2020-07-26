import Dependencies._

ThisBuild / scalaVersion := "2.13.3"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .enablePlugins(FlywayPlugin)
  .settings(
    name := "streams_playground",
    flywayUrl := "jdbc:postgresql://localhost:54340/exchange",
    flywayUser := "vder",
    flywayPassword := "gordon",
    libraryDependencies ++= Seq(
      spec2 % Test,
      logback,
      pureConfig,
      refined,
      circe,
      newtype,
      flyway,
      doobie,
      catsEffect,
      doobieHikari,
      doobiePostgres,
      slf4j
    ),
    libraryDependencies ++= http4s,
    addCompilerPlugin(
      "org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full
    ),
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
