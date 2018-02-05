package com.github.dant3.eshop.api.impl.json

import com.fasterxml.jackson.databind.module.SimpleModule

object EShopJacksonModule {
    operator fun invoke() = SimpleModule(javaClass.simpleName)
            .addTypeMappingModule(BigDecimalTypeMapping(2))
            .addTypeMappingModule(UriTypeMapping)
}