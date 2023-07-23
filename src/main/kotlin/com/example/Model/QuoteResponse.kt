package com.example.Model

import kotlinx.serialization.Serializable


@Serializable
data class QuoteResponse(
    val status : Boolean,
    val data : String
)
