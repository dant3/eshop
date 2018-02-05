package com.github.dant3.eshop.api.impl.json

import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializer

interface JacksonTypeMappingModule<T : Any> {
    val deserializer: JsonDeserializer<T>
    val serializer: JsonSerializer<T>
}