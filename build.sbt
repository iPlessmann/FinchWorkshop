import sbt._
import Keys._

name := "FinchWorkshop"
version := "0.0.1"
scalaVersion := "2.11.8"

resolvers += Resolver.sonatypeRepo("snapshots")

resolvers += "TM" at "http://maven.twttr.com"

lazy val buildSettings = Seq(
  organization := "com.github.finagle",
  version := "0.12.0",
  scalaVersion := "2.12.1",
  crossScalaVersions := Seq("2.11.8", "2.12.1")
)

scalafmtConfig in ThisBuild := Some(file(".scalafmt.conf"))

lazy val finagleVersion       = "6.34.0"
lazy val twitterServerVersion = "1.20.0"
lazy val circeVersion         = "0.5.3"
lazy val finchVersion         = "0.11.0-M4"
lazy val scalaCheckVersion    = "1.12.5"
lazy val scalaTestVersion     = "2.2.6"
lazy val gatlingVersion       = "2.1.7"
lazy val enumVersion          = "3.0"

val rootDependencies = Seq(
  "com.twitter"        %% "finagle-http"                  % finagleVersion,
  "com.twitter"        %% "finagle-stats"                 % finagleVersion,
  "io.circe"           %% "circe-core"                    % circeVersion,
  "io.circe"           %% "circe-generic"                 % circeVersion,
  "io.circe"           %% "circe-parser"                  % circeVersion,
  "com.github.finagle" %% "finch-core"                    % finchVersion,
  "com.github.finagle" %% "finch-circe"                   % finchVersion,
  "com.twitter"        %% "twitter-server"                % twitterServerVersion,
  "org.julienrf"       % "enum_2.11"                      % enumVersion,
  "com.atlassian.oai"  % "swagger-request-validator-core" % "1.0.7",
  "com.typesafe"       % "config"                         % "1.3.1",
  "com.typesafe.slick" %% "slick"                         % "3.0.0",
  "com.h2database"     % "h2"                             % "1.3.175",
  "org.scalacheck"     %% "scalacheck"                    % "1.12.5",
  compilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
)

val testDependencies = Seq(
  "org.scalacheck"        %% "scalacheck"                    % scalaCheckVersion,
  "org.scalatest"         %% "scalatest"                     % scalaTestVersion,
  "io.circe"              %% "circe-yaml"                    % "0.4.0",
  "org.yaml"              % "snakeyaml"                      % "1.17",
  "com.atlassian.oai"     % "swagger-request-validator-core" % "1.0.7",
  "io.gatling.highcharts" % "gatling-charts-highcharts"      % gatlingVersion,
  "io.gatling"            % "gatling-test-framework"         % gatlingVersion,
  "com.github.finagle"    %% "finch-test"                    % finchVersion
)

resolvers ++= Seq(
  Resolver.sonatypeRepo("snapshots")
)

lazy val root =
  project
    .in(file("."))
    .settings(
      libraryDependencies ++= Seq(
        "org.scala-lang" % "scala-reflect" % scalaVersion.value
        //for tomcat webapp
      ) ++ rootDependencies ++ testDependencies.map(_ % "test"))

fork in run := true

reformatOnCompileSettings
