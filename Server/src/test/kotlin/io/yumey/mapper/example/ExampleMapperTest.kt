package io.yumey.mapper.example

import io.yumey.entity.example.ExampleEntity
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import kotlin.test.assertEquals

class ExampleMapperTest {

    private val exampleMapper: ExampleMapper = Mappers.getMapper(ExampleMapper::class.java)

    @Test
    fun `entityToDto should map ExampleEntity to ExampleDTO correctly`() {
        val entity = ExampleEntity().apply {
            name = "Test Name"
            age = 10
        }

        val dto = exampleMapper.entityToDto(entity)

        assertEquals(entity.name, dto.name)
        assertEquals(entity.age, dto.ageInDays)
    }
}
