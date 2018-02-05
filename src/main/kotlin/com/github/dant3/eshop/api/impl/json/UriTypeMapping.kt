package com.github.dant3.eshop.api.impl.json

import android.net.Uri
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider

object UriTypeMapping: JacksonTypeMappingModule<Uri> {
    override val deserializer: JsonDeserializer<Uri> = object: JsonDeserializer<Uri>() {
        override fun deserialize(parser: JsonParser, context: DeserializationContext): Uri {
            return Uri.parse(parser.valueAsString)
        }
    }
    override val serializer: JsonSerializer<Uri> = object: JsonSerializer<Uri>() {
        override fun serialize(value: Uri, gen: JsonGenerator, serializers: SerializerProvider) {
            gen.writeString(value.toString())
        }
    }
}