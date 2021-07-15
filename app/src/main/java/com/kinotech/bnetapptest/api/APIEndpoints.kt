package com.kinotech.bnetapptest.api

import com.kinotech.bnetapptest.models.Entries
import com.kinotech.bnetapptest.models.Entry
import com.kinotech.bnetapptest.models.SessionData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface APIEndpoints {
    @Headers(
        "token: J3rVasq-8u-4mesb4N"
    )
    @GET("/?а=new_session")
    fun generateSession(): Call<SessionData>

    @Headers(
        "token: J3rVasq-8u-4mesb4N"
    )
    @GET("/?a=get_entries")
    fun getEntries(
        @Query("session") session: String
    ):Call<Entries>

    @Headers(
        "token: J3rVasq-8u-4mesb4N"
    )
    @POST("/?a=add_entry")
    fun addEntry(
        @Query("session") session: String,
        @Query("body") body: String
    )


}