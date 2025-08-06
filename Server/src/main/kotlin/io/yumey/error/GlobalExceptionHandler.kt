package io.yumey.error

import com.nimbusds.oauth2.sdk.OAuth2Error
import io.yumey.dto.error.ErrorResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<ErrorResponse> {
        logger.error("Handling IllegalArgumentException: {}", e.message, e)
        val error = OAuth2Error.INVALID_REQUEST.setDescription(e.message ?: "Invalid argument provided.")
        val errorResponse = ErrorResponse(error.description)

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(errorResponse)
    }
}