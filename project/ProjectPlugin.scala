import sbt.Keys._
import sbt._
import com.typesafe.sbt.SbtPgp.autoImport._
import scoverage.ScoverageKeys._
object ProjectPlugin extends AutoPlugin {

  override def trigger: PluginTrigger = allRequirements

  object autoImport {

    lazy val V = new {
      val Scala211 = "2.11.12"
      val Scala210 = "2.10.7"
      val ScalaTest = "3.0.4"
      val TypesafeConfig = "1.3.2"
      val ScalaCheck = "1.13.5"
    }

    lazy val V220 = new {
      val CassandraConnector = "2.0.7"
      val Spark = "2.2.0"
    }

    lazy val V221 = new {
      val CassandraConnector = "2.0.7"
      val Spark = "2.2.1"
    }

    lazy val V230 = new {
      val CassandraConnector = "2.3.0"
      val Spark = "2.3.0"
    }

    lazy val V231 = new {
      val CassandraConnector = "2.3.1"
      val Spark = "2.3.1"
    }

    lazy val V232 = new {
      val CassandraConnector = "2.3.2"
      val Spark = "2.3.2"
    }
    
    lazy val sharedSettings: Seq[Def.Setting[_]] = Seq(
      fork := true,
      scalacOptions := Seq(
        "-encoding",
        "UTF-8", // Specify character encoding used by source files.
        "-target:jvm-1.8", // Define what our target JVM is for object files
        "-unchecked", // Enable additional warnings where generated code depends on assumptions.
        "-deprecation", // Emit warning and location for usages of deprecated APIs.
        "-feature", // Emit warning and location for usages of features that should be imported explicitly.
        "-language:existentials", // Existential types (besides wildcard types) can be written and inferred
        "-language:higherKinds", // Allow higher-kinded types
        "-language:implicitConversions", // Allow definition of implicit functions called views
        "-language:postfixOps", // Allows you to use operator syntax in postfix position
        "-Xfuture" // Turn on future language features.
      )
    )
    
    lazy val commonSettings: Seq[Def.Setting[_]] = Seq(
      crossScalaVersions := Seq(V.Scala211, V.Scala210),
      libraryDependencies ++= Seq(
        // Test libraries
        "org.scalatest" %% "scalatest" % V.ScalaTest % Test,
        "com.typesafe" % "config" % V.TypesafeConfig % Test,
        "org.scalacheck" %% "scalacheck" % V.ScalaCheck % Test
      )
    ) ++ sharedSettings


    lazy val version220Settings: Seq[Def.Setting[_]] = Seq(
      crossScalaVersions := Seq(V.Scala211, V.Scala210),
      libraryDependencies ++= Seq(
        "com.datastax.spark" %% "spark-cassandra-connector" % V220.CassandraConnector,
        // Privided
        "org.apache.spark" %% "spark-core" % V220.Spark % Provided,
        "org.apache.spark" %% "spark-sql" % V220.Spark % Provided,
        "org.apache.spark" %% "spark-streaming" % V220.Spark % Provided,
        // Test libraries
        "org.scalatest" %% "scalatest" % V.ScalaTest % Test,
        "com.typesafe" % "config" % V.TypesafeConfig % Test,
        "org.scalacheck" %% "scalacheck" % V.ScalaCheck % Test
      )
    ) ++ sharedSettings

    lazy val version221Settings: Seq[Def.Setting[_]] = Seq(
      crossScalaVersions := Seq(V.Scala211, V.Scala210),
      libraryDependencies ++= Seq(
        "com.datastax.spark" %% "spark-cassandra-connector" % V221.CassandraConnector,
        // Privided
        "org.apache.spark" %% "spark-core" % V221.Spark % Provided,
        "org.apache.spark" %% "spark-sql" % V221.Spark % Provided,
        "org.apache.spark" %% "spark-streaming" % V221.Spark % Provided,
        // Test libraries
        "org.scalatest" %% "scalatest" % V.ScalaTest % Test,
        "com.typesafe" % "config" % V.TypesafeConfig % Test,
        "org.scalacheck" %% "scalacheck" % V.ScalaCheck % Test
      )
    ) ++ sharedSettings

    lazy val version230Settings: Seq[Def.Setting[_]] = Seq(
      crossScalaVersions := Seq(V.Scala211),
      libraryDependencies ++= Seq(
        "com.datastax.spark" %% "spark-cassandra-connector" % V230.CassandraConnector,
        // Privided
        "org.apache.spark" %% "spark-core" % V230.Spark % Provided,
        "org.apache.spark" %% "spark-sql" % V230.Spark % Provided,
        "org.apache.spark" %% "spark-streaming" % V230.Spark % Provided,
        // Test libraries
        "org.scalatest" %% "scalatest" % V.ScalaTest % Test,
        "com.typesafe" % "config" % V.TypesafeConfig % Test,
        "org.scalacheck" %% "scalacheck" % V.ScalaCheck % Test
      )
    ) ++ sharedSettings

    lazy val version231Settings: Seq[Def.Setting[_]] = Seq(
      crossScalaVersions := Seq(V.Scala211),
      libraryDependencies ++= Seq(
        "com.datastax.spark" %% "spark-cassandra-connector" % V231.CassandraConnector,
        // Privided
        "org.apache.spark" %% "spark-core" % V231.Spark % Provided,
        "org.apache.spark" %% "spark-sql" % V231.Spark % Provided,
        "org.apache.spark" %% "spark-streaming" % V231.Spark % Provided,
        // Test libraries
        "org.scalatest" %% "scalatest" % V.ScalaTest % Test,
        "com.typesafe" % "config" % V.TypesafeConfig % Test,
        "org.scalacheck" %% "scalacheck" % V.ScalaCheck % Test
      )
    ) ++ sharedSettings

    lazy val version232Settings: Seq[Def.Setting[_]] = Seq(
      crossScalaVersions := Seq(V.Scala211),
      libraryDependencies ++= Seq(
        "com.datastax.spark" %% "spark-cassandra-connector" % V232.CassandraConnector,
        // Privided
        "org.apache.spark" %% "spark-core" % V232.Spark % Provided,
        "org.apache.spark" %% "spark-sql" % V232.Spark % Provided,
        "org.apache.spark" %% "spark-streaming" % V232.Spark % Provided,
        // Test libraries
        "org.scalatest" %% "scalatest" % V.ScalaTest % Test,
        "com.typesafe" % "config" % V.TypesafeConfig % Test,
        "org.scalacheck" %% "scalacheck" % V.ScalaCheck % Test
      )
    ) ++ sharedSettings
  }
}
