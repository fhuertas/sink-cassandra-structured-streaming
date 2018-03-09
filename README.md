# sink-cassandra-structured-streaming

[![Build Status](https://travis-ci.org/fhuertas/cassandra-sink-spark-structured-streaming.svg?branch=master)](https://travis-ci.org/fhuertas/cassandra-sink-spark-structured-streaming)
[![Coverage Status](https://coveralls.io/repos/github/fhuertas/cassandra-sink-spark-structured-streaming/badge.svg?branch=multibuild)](https://coveralls.io/github/fhuertas/cassandra-sink-spark-structured-streaming?branch=multibuild)

Unoffical sink for cassandra for spark structured streaming. Is under develop

## Test
For testing it is neecesary to have a cassandra database with a simple simple table like this

```cql
CREATE TABLE test.test_table (
  first text PRIMARY KEY,
  second text);
```

And create a application.conf with the configuration to access to this table in the 
test resource folder
