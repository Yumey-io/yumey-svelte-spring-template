package io.yumey.controller.example

import io.yumey.dto.example.ExampleDTO
import io.yumey.mapper.example.ExampleMapper
import io.yumey.service.example.ExampleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/example")
class ExampleController(
    @Autowired private val exampleService: ExampleService,
    @Autowired private val exampleMapper: ExampleMapper,
) {
    @GetMapping
    fun getExample(
        @AuthenticationPrincipal principal: OAuth2User,
    ): ResponseEntity<ExampleDTO> {
        return ResponseEntity.ok(
            exampleMapper.entityToDto(
                exampleService.getExample(
                    principal.name,
                    principal.attributes
                )
            )
        )
    }
}