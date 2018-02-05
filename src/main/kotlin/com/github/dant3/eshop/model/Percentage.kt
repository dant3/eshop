package com.github.dant3.eshop.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import java.math.BigDecimal

class Percentage @JsonCreator constructor(@get:JsonValue val amount: BigDecimal)