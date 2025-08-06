package io.yumey.controller.api

import io.yumey.dto.user.UserDTO
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/session")
class SessionController {

    @GetMapping
    fun getSession(@AuthenticationPrincipal principal: OAuth2User?): ResponseEntity<out Map<String, Any?>> {
        if (principal == null) {
            return ResponseEntity.ok(mapOf(
                "isAuthenticated" to false,
                "user" to null
            ))
        }

        val user = UserDTO(
            id = principal.name,
            name = principal.attributes["username"] as? String ?: principal.name,
            email = principal.attributes["email"] as? String ?: "",
            avatar = principal.attributes["avatar"] as? String
        )

        return ResponseEntity.ok(mapOf(
            "isAuthenticated" to true,
            "user" to user
        ))
    }
}
