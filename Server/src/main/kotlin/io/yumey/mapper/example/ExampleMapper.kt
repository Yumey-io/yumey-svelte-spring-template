package io.yumey.mapper.example

import io.yumey.dto.example.ExampleDTO
import io.yumey.entity.example.ExampleEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface ExampleMapper {

    @Mapping(source = "entity.age", target = "ageInDays")
    fun entityToDto(entity: ExampleEntity): ExampleDTO

}