package com.github.dant3.eshop.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class ItemSummary @JsonCreator constructor(
        @field:JsonProperty("itemId") val id: ItemId,
        @field:JsonProperty("title") val title: String,
        @field:JsonProperty("image") val image: Image,
        @field:JsonProperty("price") val price: Price,
        @field:JsonProperty("seller") val seller: Seller
)