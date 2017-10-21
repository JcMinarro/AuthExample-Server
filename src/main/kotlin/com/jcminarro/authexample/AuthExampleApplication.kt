package com.jcminarro.authexample

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication(scanBasePackages = arrayOf("com.jcminarro.authexample"))
@ComponentScan
class AuthExampleApplication

fun main(args: Array<String>) {
    SpringApplication.run(AuthExampleApplication::class.java, *args)
}
