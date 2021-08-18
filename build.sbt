name := "in-class-code"

version := "4.0"

scalaVersion := "2.13.6"

libraryDependencies += "org.scalatest" % "scalatest_2.13" % "3.2.9" % "test"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.9"

libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "2.0.0"

libraryDependencies += "org.bitbucket.inkytonik.kiama" % "kiama_2.13" % "2.5.0"

Test / parallelExecution := false
