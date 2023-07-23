package com.example.Model

import kotlinx.serialization.Serializable


@Serializable
data class QuoteRequest(
    val quote : String
)
