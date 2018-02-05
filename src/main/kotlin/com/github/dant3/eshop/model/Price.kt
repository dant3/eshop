package com.github.dant3.eshop.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class Price @JsonCreator constructor(
        @JsonProperty("value") val amount: BigDecimal,
        @JsonProperty("currency") val currency: String
)