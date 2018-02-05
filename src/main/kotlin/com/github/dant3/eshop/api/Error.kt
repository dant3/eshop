package com.github.dant3.eshop.api

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class Error @JsonCreator constructor(
        @JsonProperty("code") val code: Int,
        @JsonProperty("message") val message: String
)