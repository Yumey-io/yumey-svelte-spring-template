package io.yumey.service.example

import io.yumey.service.example.ExampleService.Companion.CREATED_AT_ATTRIBUTE
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import kotlin.test.assertEquals

class ExampleServiceTest {

    private lateinit var exampleService: ExampleService

    @BeforeEach
    fun setUp() {
        exampleService = ExampleService()
    }

    @Test
    fun `getExample creates entity with correct name`() {
        val name = "testUser"
        val attributes = mutableMapOf<String, Any>()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX")
        val createdAtInstant = Instant.now()
        val creationDate = createdAtInstant.atOffset(ZoneOffset.UTC).format(formatter)
        attributes[CREATED_AT_ATTRIBUTE] = creationDate

        val result = exampleService.getExample(name, attributes)

        assertEquals(name, result.name)
        assertEquals(0, result.age)
    }

    @Test
    fun `getExample calculates age when createdAt attribute is present`() {
        val name = "testUser"
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX")

        val createdAtInstant = Instant.now().minusSeconds(86400L * 5)
        val creationDate = createdAtInstant.atOffset(ZoneOffset.UTC).format(formatter)

        val attributes = mapOf(CREATED_AT_ATTRIBUTE to creationDate)
        val result = exampleService.getExample(name, attributes)

        assertEquals(name, result.name)
        assertEquals(5, result.age)
    }
}
