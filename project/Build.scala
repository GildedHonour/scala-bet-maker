import sbt._
import sbt.Keys._

object BetZillaBuild extends Build {

  private val defaultAdditionalSettings = defaultSettings ++ Seq(libraryDependencies ++= Dependency.all)

  private def createProject(id: String) = Project(id, file(id), settings = defaultAdditionalSettings)

  lazy val root = Project("betzilla", file("."), settings = defaultSettings,
    aggregate = Seq(betcore, test, betdaq, betfair, common, mainApp)
  )

  lazy val betcore = createProject("betcore")
  lazy val common = createProject("common") dependsOn betcore
  lazy val betdaq = createProject("betdaq") dependsOn common
  lazy val mainApp = createProject("app") dependsOn(common, betfair, betdaq)
  lazy val test = createProject("test") dependsOn betfair

  val unmanagedListing = unmanagedJars in Compile := {
    (file(".").getAbsoluteFile ** "*.jar").classpath
  }

  lazy val betfair = Project(
    "betfair",
    file("betfair"),
    settings = defaultAdditionalSettings ++ unmanagedListing
  ) dependsOn common

  lazy val buildSettings = Seq(
    organization := "betzilla",
    version := "1.0-SNAPSHOT",
    scalaVersion := "2.10.3"
  )

  lazy val defaultSettings = Defaults.defaultSettings ++ buildSettings ++ Seq(
    scalacOptions ++= Seq("-encoding", "UTF-8", "-optimise", "-deprecation", "-unchecked"),
    javacOptions ++= Seq("-Xlint:unchecked", "-Xlint:deprecation"),
    parallelExecution in Test := false,
    compileOrder := CompileOrder.JavaThenScala
  )
}

object Dependency {
  val akkaActor = "com.typesafe.akka" %% "akka-actor" % "2.2.1"
  val lombok = "org.projectlombok" % "lombok" % "0.11.4"
  val coollection = "com.wagnerandade" % "coollection" % "0.2.0" from
    "http://cloud.github.com/downloads/wagnerandrade/coollection/coollection-0.2.0.jar"

  val javatuples = "org.javatuples" % "javatuples" % "1.1"
  val netdatabinder = "net.databinder.dispatch" %% "dispatch-core" % "0.11.0"
  val httpc = "org.apache.httpcomponents" % "httpclient" % "4.2.5"
  val scalaj = "org.scalaj" %% "scalaj-http" % "0.3.10" exclude("junit", "junit")
  val scalaTest = "org.scalatest" %% "scalatest" % "1.9.1" % "test"
  val jUnit = "com.novocode" % "junit-interface" % "0.10" % "test"
  val json4s = "org.json4s" %% "json4s-native" % "3.2.5"
  val tsConfig = "com.typesafe" % "config" % "1.0.2"
  val jacksonDataBind = "com.fasterxml.jackson.core" % "jackson-databind" % "2.2.2"

  val all = Seq(
    akkaActor, scalaTest, jUnit, lombok, coollection, javatuples, netdatabinder, json4s, httpc, scalaj, tsConfig,
    jacksonDataBind
  )
}
