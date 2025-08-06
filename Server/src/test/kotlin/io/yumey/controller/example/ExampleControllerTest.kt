package io.yumey.controller.example

import io.yumey.dto.example.ExampleDTO
import io.yumey.entity.example.ExampleEntity
import io.yumey.mapper.example.ExampleMapper
import io.yumey.service.example.ExampleService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.springframework.http.HttpStatus
import org.springframework.security.oauth2.core.user.OAuth2User
import kotlin.test.assertEquals

class ExampleControllerTest {

    private lateinit var exampleService: ExampleService
    private lateinit var exampleMapper: ExampleMapper
    private lateinit var exampleController: ExampleController
    private lateinit var principal: OAuth2User

    @BeforeEach
    fun setUp() {
        exampleService = mock()
        exampleMapper = mock()
        exampleController = ExampleController(exampleService, exampleMapper)
        principal = mock()
    }

    @Test
    fun `getExample returns expected response`() {
        val userName = "testUser"
        val attributes = mapOf<String, Any>("attr1" to "value1")
        val exampleEntity = ExampleEntity().apply {
            name = userName
            age = 5
        }
        val exampleDTO = ExampleDTO(userName, 5)

        whenever(principal.name).thenReturn(userName)
        whenever(principal.attributes).thenReturn(attributes)
        whenever(exampleService.getExample(eq(userName), any())).thenReturn(exampleEntity)
        whenever(exampleMapper.entityToDto(exampleEntity)).thenReturn(exampleDTO)

        val response = exampleController.getExample(principal)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(exampleDTO, response.body)
        verify(exampleService).getExample(eq(userName), eq(attributes))
        verify(exampleMapper).entityToDto(exampleEntity)
    }
}
