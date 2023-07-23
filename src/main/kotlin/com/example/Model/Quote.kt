package com.example.Model

import kotlinx.serialization.Serializable


@Serializable
data class Quote(
    val id : Int,
    val quote : String
)
