package io.yumey.entity.example

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ExampleEntityTest {

    @Test
    fun `default constructor initializes with default values`() {
        val entity = ExampleEntity()

        assertEquals("", entity.name)
        assertEquals(0, entity.age)
    }

    @Test
    fun `setters update properties correctly`() {
        val entity = ExampleEntity()

        entity.name = "Updated Name"
        entity.age = 25

        assertEquals("Updated Name", entity.name)
        assertEquals(25, entity.age)
    }
}
