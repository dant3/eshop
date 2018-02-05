package com.github.dant3.eshop.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.net.URL

data class Image @JsonCreator constructor(@JsonProperty("imageUrl") val url: URL)