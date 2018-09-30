/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.predictionio.tools.admin

import org.specs2.mutable.Specification
import akka.http.scaladsl.testkit.Specs2RouteTest

class AdminAPISpec extends Specification with Specs2RouteTest {
  val route = AdminServer.createRoute()

  "GET / request" should {
    "properly produce OK HttpResponses" in {
      Get() ~> route ~> check {
        response.status.intValue() shouldEqual 200
        responseAs[String] shouldEqual """{"status":"alive"}"""
      }
    }
  }

  "GET /cmd/app request" should {
    "properly produce OK HttpResponses" in {
      Get("/cmd/app") ~> route ~> check {
        println(response.headers)
        response.status.intValue() shouldEqual 200
        responseAs[String] shouldEqual """{"status":1}"""
      }
    }
  }
}
