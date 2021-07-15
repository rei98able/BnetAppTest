package com.kinotech.bnetapptest.models

data class Entries(
    val status: Int,
    val data: List<Entry>
)

data class Entry(
    val id: String,
    val body: String,
    val da: String,
    val dm: String
)