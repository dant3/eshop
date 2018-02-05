package com.github.dant3.eshop.api

class ApiException(val error: Error) : Exception(error.message)