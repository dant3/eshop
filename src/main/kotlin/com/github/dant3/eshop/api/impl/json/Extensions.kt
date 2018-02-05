package com.github.dant3.eshop.api.impl.json

import com.fasterxml.jackson.databind.module.SimpleModule

internal inline fun <reified T : Any> SimpleModule.addTypeMappingModule(typeMappingModule: JacksonTypeMappingModule<T>): SimpleModule {
    return addSerializer(T::class.java, typeMappingModule.serializer)
            .addDeserializer(T::class.java, typeMappingModule.deserializer)
}