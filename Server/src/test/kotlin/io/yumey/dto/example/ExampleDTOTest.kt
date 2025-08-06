package io.yumey.dto.example

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class ExampleDTOTest {

    @Test
    fun `constructor sets properties correctly`() {
        val dto = ExampleDTO("Test Name", 10)

        assertEquals("Test Name", dto.name)
        assertEquals(10, dto.ageInDays)
    }

    @Test
    fun `equals returns true for equal objects`() {
        val dto1 = ExampleDTO("Test Name", 10)
        val dto2 = ExampleDTO("Test Name", 10)

        assertEquals(dto1, dto2)
        assertEquals(dto1.hashCode(), dto2.hashCode())
    }

    @Test
    fun `equals returns false for different objects`() {
        val dto1 = ExampleDTO("Test Name", 10)
        val dto2 = ExampleDTO("Different Name", 10)
        val dto3 = ExampleDTO("Test Name", 20)

        assertNotEquals(dto1, dto2)
        assertNotEquals(dto1, dto3)
    }

    @Test
    fun `copy creates a new instance with specified changes`() {
        val original = ExampleDTO("Test Name", 10)

        val copy1 = original.copy(name = "New Name")
        val copy2 = original.copy(ageInDays = 20)
        val copy3 = original.copy(name = "New Name", ageInDays = 20)

        assertEquals("New Name", copy1.name)
        assertEquals(10, copy1.ageInDays)

        assertEquals("Test Name", copy2.name)
        assertEquals(20, copy2.ageInDays)

        assertEquals("New Name", copy3.name)
        assertEquals(20, copy3.ageInDays)
    }

    @Test
    fun `component functions work correctly for destructuring`() {
        val dto = ExampleDTO("Test Name", 10)

        val (name, ageInDays) = dto

        assertEquals("Test Name", name)
        assertEquals(10, ageInDays)
    }
}
