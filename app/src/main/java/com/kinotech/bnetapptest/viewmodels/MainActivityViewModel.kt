package com.kinotech.bnetapptest.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kinotech.bnetapptest.models.DataInfo
import com.kinotech.bnetapptest.models.Entries
import com.kinotech.bnetapptest.models.Entry
import com.kinotech.bnetapptest.repositories.MainActivityRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivityViewModel : ViewModel() {

    private val mainActivityRepo : MainActivityRepo = MainActivityRepo()
    val readAllData : MutableLiveData<List<Entry>> = mainActivityRepo.readAllData
    val sessionData : MutableLiveData<DataInfo> = mainActivityRepo.session


    suspend fun readAllEntries(session: String) = withContext(Dispatchers.IO){
        mainActivityRepo.readAllData(session)
    }

    fun getEntries(): MutableLiveData<List<Entry>> {
        return mainActivityRepo.getData()
    }

    suspend fun generateSession() = withContext(Dispatchers.IO){
        mainActivityRepo.generateSession()
    }

    fun getSession(): MutableLiveData<DataInfo> {
        return mainActivityRepo.getSessionId()
    }


}