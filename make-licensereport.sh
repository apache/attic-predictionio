#!/usr/bin/env bash

#
# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

FWDIR="$(cd `dirname $0`; pwd)"
cd ${FWDIR}
set -x

sbt/sbt clean
sbt/sbt dumpLicenseReport

sbt/sbt storage/clean
sbt/sbt storage/dumpLicenseReport

rm -rf license-reports
mkdir license-reports

find . -name "*-licenses.csv" -exec cat {} >> license-reports/licences-concat.csv \;
cat license-reports/licences-concat.csv | sort | uniq | grep -v Apache | grep -v ASL | grep -v predictionio > license-reports/licences-notice.csv
