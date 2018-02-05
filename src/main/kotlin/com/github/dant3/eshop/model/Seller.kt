package com.github.dant3.eshop.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class Seller @JsonCreator constructor(
        @field:JsonProperty("username") val username: String,
        @field:JsonProperty("feedbackPercentage") val feedbackPercentage: Percentage,
        @field:JsonProperty("feedbackScore") val feedbackScore: Long
)