name := "markdown-bench"

version := "0.1.0"

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  "io.github.gitbucket" % "markedj" % "1.0.13",
  "com.vladsch.flexmark" % "flexmark-all" % "0.28.24",
  "com.atlassian.commonmark" % "commonmark" % "0.10.0",
  "com.storm-enroute" %% "scalameter" % "0.8.2"
)