package com.example.playingwithwiremock

import java.net.URL

class HttpRequestHandler {

    fun makeGetRequest(urlString: String): String {
        val url = URL(urlString)
        val connection = url.openConnection()
        return connection.getInputStream().bufferedReader().readText()
    }
}