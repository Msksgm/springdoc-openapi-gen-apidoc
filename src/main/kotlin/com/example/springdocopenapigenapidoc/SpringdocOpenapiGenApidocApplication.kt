package com.example.springdocopenapigenapidoc

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.info.License
import io.swagger.v3.oas.annotations.servers.Server
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * SpringdocOpenapiGenApidocApplication
 */
@SpringBootApplication
@OpenAPIDefinition(
    info = Info(
        title = "API Title",
        version = "0.0",
        description = "My API",
        license = License(name = "Apache 2.0", url = "http://sample"),
        contact = Contact(url = "http://sample.com", name = "sample", email = "sample@sample.com")
    ),
    servers = [
        Server(
            description = "ローカルサーバーの説明です",
            url = "http://localhost:8080",
        ),
        Server(
            description = "事前環境サーバーの説明です",
            url = "http://stg:8080",
        ),
        Server(
            description = "本番環境サーバーの説明です",
            url = "http://prod:8080",
        ),
    ],
)
class SpringdocOpenapiGenApidocApplication

/**
 * main 関数
 *
 * @param args
 */
fun main(args: Array<String>) {
    @Suppress("SpreadOperator")
    runApplication<SpringdocOpenapiGenApidocApplication>(*args)
}
