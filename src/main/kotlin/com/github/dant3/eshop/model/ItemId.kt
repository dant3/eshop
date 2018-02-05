package com.github.dant3.eshop.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

data class ItemId @JsonCreator constructor(@get:JsonValue val value: String)