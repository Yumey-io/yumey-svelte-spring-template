package io.yumey.dto

import io.yumey.dto.user.UserDTO
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class UserDTOTest {

    @Test
    fun `UserDTO properly stores and retrieves user information`() {
        val id = "1"
        val username = "testuser"
        val email = "test@example.com"
        
        val userDTO = UserDTO(id.toString(), username, email)
        
        assertEquals(id, userDTO.id)
        assertEquals(username, userDTO.name)
        assertEquals(email, userDTO.email)
    }
    
    @Test
    fun `UserDTO equals and hashCode work correctly`() {
        val user1 = UserDTO("1", "user1", "user1@example.com")
        val user2 = UserDTO("1", "user1", "user1@example.com")
        val user3 = UserDTO("2", "user2", "user2@example.com")
        
        assertEquals(user1, user2, "Equal DTOs should be equal")
        assertNotEquals(user1, user3, "Different DTOs should not be equal")
        assertEquals(user1.hashCode(), user2.hashCode(), "Equal DTOs should have same hash code")
    }
    
    @Test
    fun `UserDTO toString contains relevant information`() {
        val userDTO = UserDTO("1", "testuser", "test@example.com")
        
        val toStringResult = userDTO.toString()
        
        assertTrue(toStringResult.contains("1"))
        assertTrue(toStringResult.contains("testuser"))
        assertTrue(toStringResult.contains("test@example.com"))
    }
}
