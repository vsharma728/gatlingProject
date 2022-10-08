package com.orangeHrmApp

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class OrangeHrmUsingHttpProxy2 extends Simulation {

	val httpProtocol = http
		.baseUrl("https://opensource-demo.orangehrmlive.com")
		.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
		.acceptHeader("application/json")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.5")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:105.0) Gecko/20100101 Firefox/105.0")

	val headers_0 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8",
		"Sec-Fetch-Dest" -> "document",
		"Sec-Fetch-Mode" -> "navigate",
		"Sec-Fetch-Site" -> "same-origin",
		"Sec-Fetch-User" -> "?1",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_1 = Map(
		"Sec-Fetch-Dest" -> "empty",
		"Sec-Fetch-Mode" -> "cors",
		"Sec-Fetch-Site" -> "same-origin")

	val headers_2 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8",
		"Origin" -> "https://opensource-demo.orangehrmlive.com",
		"Sec-Fetch-Dest" -> "document",
		"Sec-Fetch-Mode" -> "navigate",
		"Sec-Fetch-Site" -> "same-origin",
		"Sec-Fetch-User" -> "?1",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_4 = Map(
		"Accept" -> "image/avif,image/webp,*/*",
		"Sec-Fetch-Dest" -> "image",
		"Sec-Fetch-Mode" -> "no-cors",
		"Sec-Fetch-Site" -> "same-origin")



	val scn = scenario("OrangeHrmUsingHttpProxy2")
		.exec(http("request_0")
			.get("/web/index.php/auth/login")
			.headers(headers_0)
			.resources(http("Login")
			.get("/web/index.php/core/i18n/messages")
			.headers(headers_1)))
		.pause(14)
		.exec(http("Enter Credential")
			.post("/web/index.php/auth/validate")
			.headers(headers_2)
			.formParam("_token", "41.gbrU4XritpLMMDpJOymNG_3iGt1wo1HKzQ6xT5pOQGg.yujskBbR7qvhcXc7Yx-0dpOLb4k06z-Yl1j9K9kAMBu2y7DWTKvXpYtcdw")
			.formParam("username", "Admin")
			.formParam("password", "admin123")
			.resources(http("Click Login")
			.get("/web/index.php/core/i18n/messages")
			.headers(headers_1),
            http("request_4")
			.get("/web/index.php/pim/viewPhoto/empNumber/7")
			.headers(headers_4),
            http("request_5")
			.get("/web/index.php/api/v2/pim/employees?limit=50&offset=0&model=detailed&includeEmployees=onlyCurrent&sortField=employee.firstName&sortOrder=ASC")
			.headers(headers_1),
            http("Click Admin tab")
			.get("/web/index.php/api/v2/admin/employment-statuses")
			.headers(headers_1),
            http("request_7")
			.get("/web/index.php/api/v2/admin/subunits")
			.headers(headers_1),
            http("request_8")
			.get("/web/index.php/api/v2/admin/job-titles")
			.headers(headers_1)))
		.pause(1)
		.exec(http("request_9")
			.get("/web/index.php/admin/viewAdminModule")
			.headers(headers_0)
			.resources(http("request_10")
			.get("/web/index.php/core/i18n/messages")
			.headers(headers_1),
            http("request_11")
			.get("/web/index.php/pim/viewPhoto/empNumber/7")
			.headers(headers_4),
            http("request_12")
			.get("/web/index.php/api/v2/admin/users?limit=50&offset=0&sortField=u.userName&sortOrder=ASC")
			.headers(headers_1)))
		.pause(10)
		.exec(http("request_13")
			.get("/web/index.php/api/v2/admin/users?limit=50&offset=0&userRoleId=1&sortField=u.userName&sortOrder=ASC")
			.headers(headers_1))
		.pause(9)
		.exec(http("Logout")
			.get("/web/index.php/auth/logout")
			.headers(headers_0)
			.resources(http("request_15")
			.get("/web/index.php/core/i18n/messages")
			.headers(headers_1)))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}