#!/usr/bin/env bash


setup_git() {
  git config user.email "travis@travis-ci.org"
  git config user.name "Travis CI"
}
un_setup_git() {
  git config user.email "francisco@fhuertas.com"
  git config user.name "Francisco Huertas"
}

#read_version_no_snapshot() {
#    VERSION=$(cat version.sbt)
#    VERSION=${VERSION/-SNAPSHOT\"/}
#    VERSION=${VERSION#*\"}
#    echo ${VERSION/-SNAPSHOT/}
#}
#
#write_version() {
#    VERSION=$1
#    echo "version in ThisBuild:= \"${VERSION}\"" > version.sbt
#}
#create_mayor_version() {
#    # TODO en travis!
#    GH_REPO=github.com/fhuertas/cassandra-sink-spark-structured-streaming
#    VERSION=$(read_version_no_snapshot)
#    IFS='.' read -r -a array <<< ${VERSION}
#    NEXT_BRANCH="branch-${array[0]}.${array[1]}"
#    NEXT_VERSION=$((${array[0]} + 1 )).${array[1]}.${array[2]}
#    PREV_BRANCH=$(git rev-parse --abbrev-ref HEAD)
#    echo "Current version: ${VERSION}"
#    echo "Current branch"
#    echo "Next mayor version: ${NEXT_VERSION}"
#    echo "Creating new branch: ${NEXT_BRANCH}"
#    git checkout -b ${NEXT_BRANCH}
#
#    write_version "${VERSION}-RC0-SNAPSHOT"
#    setup_git
#    git commit -a -m "Generated: new version branch"
#    git push "https://${GH_TOKEN}@${GH_REPO}" ${NEXT_BRANCH}
#    git checkout ${PREV_BRANCH}
##    write_version "${NEXT_VERSION}-SNAPSHOT"
##    git commit -a -m "Travis: Up master version"
##    git push "https://${GH_TOKEN}@${GH_REPO}" ${PREV_BRANCH}
#    un_setup_git
#}

init-docker() {
    docker pull cassandra:3.11
    docker run -d --name cassandra -v $(pwd)/integration/vol:/vol -p 9042:9042 cassandra:3.11
    docker exec cassandra sh -c /vol/init_db.sh
}

build() {
#    sbt ++${TRAVIS_SCALA_VERSION} coverage test
#    sbt ++${TRAVIS_SCALA_VERSION} coverageReport
#    sbt ++${TRAVIS_SCALA_VERSION} coverageAggregate
#    sbt ++${TRAVIS_SCALA_VERSION} coveralls
    sbt ci-all
}

publish_snapshot() {
   sbt ++$TRAVIS_SCALA_VERSION publish
}

publish() {
    if [[ "${TRAVIS_TAG}" != "" ]]; then
      write_version ${TRAVIS_TAG/v}
      sbt ++$TRAVIS_SCALA_VERSION release
    else
      sbt ++$TRAVIS_SCALA_VERSION publish
    fi
}

get_sha(){
    TMP_SHA=`curl https://api.github.com/repos/fhuertas/cassandra-sink-spark-structured-streaming/git/refs/tags | jq '.[] | select(.ref == "refs/tags/'$1'") | .object.sha'`
    echo ${TMP_SHA//\"}
}

get_comment(){
    SHA=$1
    MESSAGE_TMP=`curl https://api.github.com/repos/fhuertas/cassandra-sink-spark-structured-streaming/git/tags/${SHA} | jq .message`
    printf "$MESSAGE_TMP" | sed -e '$ s/.$//' | sed -e '1s/^.//'
}


replace_tag() {
    setup_git
    echo "Delete pre-release branch"
    COMMENT=git tag -l -n9 ${TRAVIS_TAG}
    git push origin :${TRAVIS_TAG}
    VERSION=${TRAVIS_TAG/v}
    write_version ${VERSION}
    git commit -a

    git push
    un_setup_git
}
release() {
    echo "Getting tag info"
    SHA=`get_sha ${TRAVIS_TAG}`
    MESSAGE=`get_comment ${SHA}`
    NEW_TAG=${TRAVIS_TAG/v}
    echo "SHA: ${SHA}"
    echo "New tag: ${NEW_TAG}"
    printf "$MESSAGE" > comment
    printf "Message: $MESSAGE"
    git tag -a ${NEW_TAG} -F comment


#    echo "Message ${}"
#    echo "Remove
#
##    write_version ${TRAVIS_TAG/v}
#    OUTPUT=`curl https://api.github.com/repos/fhuertas/cassandra-sink-spark-structured-streaming/tags`
#    echo $OUTPUT
#    set +x


}
