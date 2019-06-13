package com.example.roomwordsample.Repos

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.example.roomwordsample.DAO.WordDao
import com.example.roomwordsample.Entities.Word

class WordRepository(private val wordDao: WordDao) {

    fun allWords(): DataSource.Factory<Int, Word> = wordDao.getAllWords()

    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

}