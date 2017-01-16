val Scalaversion = "2.11.8"

lazy val commonSettings = Seq(
  organization := "no.knowit.finatra",
  version := "0.1-SNAPSHOT",
  scalaVersion := Scalaversion
)

lazy val finatra_demo = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    name := "finatra-demo",
    javacOptions ++= Seq("-source", "1.8", "-target", "1.8"),
    scalacOptions := Seq("-target:jvm-1.8"),
    libraryDependencies ++= Seq(
      "com.twitter" %% "finatra-http" % "2.6.0",
      "net.codingwell" %% "scala-guice" % "4.1.0")
  )
