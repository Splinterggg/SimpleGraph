package com.app.simplegraph.models


data class Data(
    val id: String,
    val symbol: String,
    val name: String,
    val values: List<List<String>>
)