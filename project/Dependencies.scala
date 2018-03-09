import Common._
import sbt._
import sbt.Keys._

object Dependencies {
  // Versions
  lazy val scalaTestVersion = "3.0.4"
  lazy val typesafeConfigVersion = "1.3.2"

  //Spark and cassandra dependencies are set in library.sbt with runtime script

  // Testing libraries
  val scalaTest = "org.scalatest" %% "scalatest" % scalaTestVersion
  val typesafeConfig = "com.typesafe" % "config" % typesafeConfigVersion

  val testDependencies: Seq[ModuleID] =
    Seq(scalaTest, typesafeConfig).map(_ % "test")

}
