package com.kinotech.bnetapptest.repositories

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.kinotech.bnetapptest.api.APIEndpoints
import com.kinotech.bnetapptest.api.ServiceBuilder
import com.kinotech.bnetapptest.models.DataInfo
import com.kinotech.bnetapptest.models.Entries
import com.kinotech.bnetapptest.models.Entry
import com.kinotech.bnetapptest.models.SessionData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityRepo {
    private val request = ServiceBuilder.buildService(APIEndpoints::class.java)
    val readAllData = MutableLiveData<List<Entry>>()
    val session = MutableLiveData<DataInfo>()

    fun generateSession() {
        val call = request.generateSession()
        call.enqueue(
            object : Callback<SessionData> {
                override fun onResponse(call: Call<SessionData>, response: Response<SessionData>) {
                    if (response.isSuccessful) {
                        Log.d("cout", "response is ${response.body()}")
                        session.postValue(response.body()!!.data)
                    }
                }

                override fun onFailure(call: Call<SessionData>, t: Throwable) {
                    Log.d("cout", "onResponse: $t")
                }

            }
        )

    }

    fun readAllData(sessionId: String) {
        val call = request.getEntries(sessionId)
        call.enqueue(
            object : Callback<Entries> {
                override fun onResponse(call: Call<Entries>, response: Response<Entries>) {
                    if (response.isSuccessful) {
                        Log.d("count", "response is ${response.body()}")
                        readAllData.postValue(response.body()!!.data)
                    }
                }

                override fun onFailure(call: Call<Entries>, t: Throwable) {
                    Log.d("cout", "onResponse: $t")
                }

            }
        )

    }

    fun getSessionId(): MutableLiveData<DataInfo> {
        return session
    }

    fun getData(): MutableLiveData<List<Entry>> {
        return readAllData
    }

}