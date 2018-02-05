package com.github.dant3.eshop.api.impl

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.github.dant3.eshop.model.ItemSummary

@JsonIgnoreProperties(ignoreUnknown = true)
class ItemsResponse @JsonCreator constructor(
        @field:JsonProperty("total") val itemCount: Int,
        @field:JsonProperty("limit") val limit: Int,
        @field:JsonProperty("offset") val offset: Int,
        @field:JsonProperty("itemSummaries") val items: List<ItemSummary>
)