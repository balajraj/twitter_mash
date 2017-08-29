name := "twitter_git_mash"
version := "1.0"
scalaVersion := "2.11.8"


libraryDependencies ++= Seq(
  // For the console exercise, the logback dependency
  // is only important if you want to see all the
  // debugging output. If you don't want that, simply
  // omit it.
  "ch.qos.logback"          %  "logback-classic" % "1.2.3",
  "net.databinder.dispatch" %% "dispatch-core"   % "0.13.1"
)

libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.2"
libraryDependencies += "com.typesafe.akka" % "akka-actor_2.11" % "2.3.8"
libraryDependencies += "org.json4s" %% "json4s-jackson" % "3.5.2"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0"
libraryDependencies += "com.typesafe.akka" % "akka-testkit_2.11" % "2.5.3" % "test"
libraryDependencies += "org.mockito" % "mockito-core" % "1.8.5" % "test"
libraryDependencies += "org.scalamock" %% "scalamock-scalatest-support" % "3.5.0" % Test
