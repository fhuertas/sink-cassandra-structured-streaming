#!/usr/bin/env bash
SPARK_VERSION=${1:-"2.2.1"}
BASE_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" ;pwd)"

if [[ "${SPARK_VERSION}" == "2.2.0" ]]; then
    CASSANDRA_CONNECTOR_VERSION="2.0.6"
    HOLDENK_VERSION="${SPARK_VERSION}_0.8.0"
elif [[ "${SPARK_VERSION}" == "2.2.1" ]]; then
    CASSANDRA_CONNECTOR_VERSION="2.0.6"
    HOLDENK_VERSION="2.2.0_0.8.0"
    echo "Not supported spark version: $SPARK_VERSION"
    exit 1
else
    echo "Invalid Spark Version: $SPARK_VERSION"
    exit 1
fi

echo "Genrate library.sbt for Spark ${SPARK_VERSION}"

#cat
cat <<EOF > ${BASE_DIR}/../libraries.sbt
// Compiled
libraryDependencies+="com.datastax.spark" %% "spark-cassandra-connector" % "${CASSANDRA_CONNECTOR_VERSION}"
// Privided
libraryDependencies+="org.apache.spark" %% "spark-core" % "${SPARK_VERSION}" % "provided"
libraryDependencies+="org.apache.spark" %% "spark-sql" % "${SPARK_VERSION}" % "provided"
//Test
libraryDependencies+="com.holdenkarau" %% "spark-testing-base" % "${HOLDENK_VERSION}" % "test"
libraryDependencies+="org.apache.spark" %% "spark-hive" % "${SPARK_VERSION}" % "test"
EOF

sed  -i -e "s/^name := \"cassandra-sink_.*/name := \"cassandra-sink_${SPARK_VERSION}\"/g" ${BASE_DIR}/../build.sbt