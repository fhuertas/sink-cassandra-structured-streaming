// Compiled
libraryDependencies+="com.datastax.spark" %% "spark-cassandra-connector" % "2.0.6"
// Privided
libraryDependencies+="org.apache.spark" %% "spark-core" % "2.2.0" % "provided"
libraryDependencies+="org.apache.spark" %% "spark-sql" % "2.2.0" % "provided"
//Test
libraryDependencies+="com.holdenkarau" %% "spark-testing-base" % "2.2.0_0.8.0" % "test"
libraryDependencies+="org.apache.spark" %% "spark-hive" % "2.2.0" % "test"
