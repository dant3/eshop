package com.github.dant3.eshop.api.impl.json

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.math.BigDecimal

class BigDecimalTypeMapping(private val scale: Int): JacksonTypeMappingModule<BigDecimal> {
    override val deserializer = object: JsonDeserializer<BigDecimal>() {
        override fun deserialize(parser: JsonParser, context: DeserializationContext): BigDecimal {
            return BigDecimal(parser.valueAsString)
        }
    }

    override val serializer = object: JsonSerializer<BigDecimal>() {
        override fun serialize(value: BigDecimal, gen: JsonGenerator, serializers: SerializerProvider) {
            gen.writeString(value.setScale(scale, BigDecimal.ROUND_HALF_UP).toString())
        }
    }
}