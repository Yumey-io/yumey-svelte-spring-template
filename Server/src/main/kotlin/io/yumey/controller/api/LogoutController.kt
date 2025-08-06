package io.yumey.controller.api

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/logout")
class LogoutController {

    @Value("\${frontend.url}")
    private lateinit var frontendUrl: String

    @GetMapping
    fun logout(request: HttpServletRequest, response: HttpServletResponse): String {
        val auth = SecurityContextHolder.getContext().authentication
        if (auth != null) {
            SecurityContextLogoutHandler().logout(request, response, auth)
        }
        response.sendRedirect("$frontendUrl/")
        return "Logout successful"
    }
}
