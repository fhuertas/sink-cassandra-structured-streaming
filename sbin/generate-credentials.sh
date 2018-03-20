#!/usr/bin/env bash
BASE_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" ;pwd)"
# TODO delete this file?
cat << EOF > ${BASE_DIR}/../.credentials
realm=Sonatype nexus
host=oss.sonatype.org
user=${SONATYPE_USER}
password=${SONATYPE_PASS}
EOF