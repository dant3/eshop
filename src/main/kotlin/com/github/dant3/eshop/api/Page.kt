package com.github.dant3.eshop.api

class Page<T>(val index: Int, val totalPages: Int, val items: List<T>)