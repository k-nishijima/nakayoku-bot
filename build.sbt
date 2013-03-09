import AssemblyKeys._

name := "nakayoku-bot"

version := "1.0"

scalaVersion := "2.9.2"

resolvers ++= Seq(
  "twitter4j.org" at "http://twitter4j.org/maven2",
  "ATILIKA dependencies" at "http://www.atilika.org/nexus/content/repositories/atilika"
)

libraryDependencies ++= Seq(
    "org.atilika.kuromoji" % "kuromoji" % "0.7.7",
    "joda-time" % "joda-time" % "2.1",
    "org.joda" % "joda-convert" % "1.1",
    "org.twitter4j" % "twitter4j-core" % "3.0.3",
	"org.scalatest" % "scalatest_2.9.0" % "1.9.1" % "test"
)

scalacOptions ++= Seq("-encoding", "UTF-8")

assemblySettings
