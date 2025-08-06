package io.yumey

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class YumeyServerTest {

    @Test
    fun `context loads successfully`() {
        // This test will fail if the application context cannot start
    }
}
