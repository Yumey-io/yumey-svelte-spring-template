package io.yumey

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class YumeyServer

fun main(args: Array<String>) {
    runApplication<YumeyServer>(*args)
}
