package io.yumey.config

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Value("\${frontend.url}")
    private lateinit var frontendUrl: String

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .cors { it.configurationSource(corsConfigurationSource()) }
            .authorizeHttpRequests { authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/", "/public/**", "/session").permitAll()
                    .requestMatchers("/oauth2/callback/**").permitAll()
                    .requestMatchers("/error").permitAll()
                    .anyRequest().authenticated()
            }
            .oauth2Login { oauth2Login ->
                oauth2Login
                    .loginPage("/oauth2/authorization/yumey")
                    .defaultSuccessUrl("$frontendUrl/", true)
                    .failureUrl("$frontendUrl/login?error=true")
                    .authorizationEndpoint {
                        it.baseUri("/oauth2/authorization")
                    }
                    .redirectionEndpoint {
                        it.baseUri("/oauth2/callback/*")
                    }
            }
            .exceptionHandling {
                it.authenticationEntryPoint { _, response, _ ->
                    response.sendRedirect("/oauth2/authorization/yumey")
                }
            }
        return http.build()
    }

    @Bean
    fun successHandler(): AuthenticationSuccessHandler {
        return AuthenticationSuccessHandler { _: HttpServletRequest, response: HttpServletResponse, _: Authentication ->
            response.sendRedirect(frontendUrl)
        }
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf(frontendUrl)
        configuration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
        configuration.allowedHeaders = listOf("*")
        configuration.allowCredentials = true
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}