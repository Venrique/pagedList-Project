package com.example.roomwordsample.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.roomwordsample.Databases.WordRoomDatabase
import com.example.roomwordsample.Entities.Word
import com.example.roomwordsample.Repos.WordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val PAGE_SIZE = 10
private const val INITIAL_LOAD_SIZE_HINT = 10

class WordViewModel(application: Application): AndroidViewModel(application) {

    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(INITIAL_LOAD_SIZE_HINT)
        .setPageSize(PAGE_SIZE)
        .build()


    val wordsDao = WordRoomDatabase.getDatabase(application, viewModelScope).wordDao()

    private val repository = WordRepository(wordsDao)

    val allWords = LivePagedListBuilder(repository.allWords(), pagedListConfig).build()

    fun insert(word: Word) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(word)
    }

}