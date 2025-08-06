package io.yumey.service.example

import io.yumey.entity.example.ExampleEntity
import org.springframework.stereotype.Service
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@Service
class ExampleService {

    companion object {
        const val CREATED_AT_ATTRIBUTE = "createdAt"
    }

    fun getExample(name: String, attributes: Map<String, Any>): ExampleEntity {
        if (!attributes.containsKey(CREATED_AT_ATTRIBUTE)) {
            throw IllegalArgumentException("Attribute '$CREATED_AT_ATTRIBUTE' is not set")
        }

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX")

        val createdAtString = attributes[CREATED_AT_ATTRIBUTE] as? String
            ?: throw IllegalArgumentException("Attribute '$CREATED_AT_ATTRIBUTE' must be a String")

        val creationDate = OffsetDateTime.parse(createdAtString, formatter)

        val daysSinceCreation = ChronoUnit.DAYS.between(creationDate.toLocalDate(), OffsetDateTime.now().toLocalDate())

        val exampleEntity = ExampleEntity()
        exampleEntity.name = name
        exampleEntity.age = daysSinceCreation.toInt()
        return exampleEntity
    }
}