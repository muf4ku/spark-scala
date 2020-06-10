name := "untitled"

version := "0.1"
val CirceVersion = "0.9.3"
val Http4sVersion = "0.18.11"
scalaVersion := "2.11.12"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.5.16",
  "com.typesafe.akka" %% "akka-stream" % "2.5.16",
  "com.typesafe.akka" %% "akka-http" % "10.1.5",
  "com.sun.jersey" % "jersey-client" % "1.19.4",
  "org.http4s" %% "http4s-blaze-client" % Http4sVersion,
  "org.http4s" %% "http4s-blaze-server" % Http4sVersion,
  "org.http4s" %% "http4s-dsl"          % Http4sVersion,
  "org.http4s" %% "http4s-circe"        % Http4sVersion,
  "io.circe"   %% "circe-generic"       % CirceVersion,
  "org.apache.spark" %% "spark-core" % "2.3.0",
  "org.elasticsearch" %% "elasticsearch-spark" % "2.2.0",
  "org.scala-lang" % "scala-library" % scalaVersion.value,
  "org.scala-lang" % "scala-compiler" % scalaVersion.value % "scala-tool"
)