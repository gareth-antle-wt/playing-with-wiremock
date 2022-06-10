package com.example.playingwithwiremock

import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @get:Rule
    val wireMockRuleConfig = WireMockRule(WireMockConfiguration.options().port(8080), false) // use this in tests to start up the wiremock instance

    @Test
    fun testMockHelloWorld() {
        WireMock.stubFor(
            WireMock.get(WireMock.urlPathMatching("/hello")).willReturn(
                WireMock.aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "text/plain")
                    .withBody("Hello World!")
            )
        )

        val url = URL("http://localhost:8080/hello")
        val connection = url.openConnection()
        BufferedReader(InputStreamReader(connection.getInputStream())).use { inp ->
            var line: String?
            while (inp.readLine().also { line = it } != null) {
                println(line)
                assertEquals("Hello World!", line)
            }
        }
    }

    @Test
    fun testParseJsonResponse() {
        WireMock.stubFor(
            WireMock.get(WireMock.urlPathMatching("/json")).willReturn(
                WireMock.aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "application/json")
                    .withBodyFile("testJsonBody.json")
            )
        )
        //.withBody("some body once told me..")))

        val url = URL("http://localhost:8080/hello")
        // val response = url.get
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}