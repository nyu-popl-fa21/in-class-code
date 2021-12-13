name := "in-class-code"

version := "4.0"

scalaVersion := "2.13.4"

libraryDependencies += "org.scalatest" % "scalatest_2.13" % "3.2.9" % "test"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.9"

libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "2.0.0"

libraryDependencies += "org.bitbucket.inkytonik.kiama" % "kiama_2.13" % "2.5.0"

libraryDependencies += "com.typesafe.akka" % "akka-actor_2.13" % "2.6.17"

libraryDependencies +=
  "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.4"

Test / parallelExecution := false
