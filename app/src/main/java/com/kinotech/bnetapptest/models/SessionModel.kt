package com.kinotech.bnetapptest.models

data class SessionData(
    val status: Int,
    val data: DataInfo
)

data class DataInfo(
    val session: String
)