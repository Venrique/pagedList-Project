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

const val PAGE_SIZE = 20
private const val INITIAL_LOAD_SIZE_HINT = 20
var limit = INITIAL_LOAD_SIZE_HINT

class WordViewModel(application: Application): AndroidViewModel(application) {

    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(INITIAL_LOAD_SIZE_HINT)
        .setPageSize(PAGE_SIZE)
        .build()


    val wordsDao = WordRoomDatabase.getDatabase(application, viewModelScope).wordDao()

    private val repository = WordRepository(wordsDao)

    val allWords =
        LivePagedListBuilder(repository.allWords(), pagedListConfig)
            .setBoundaryCallback(boundCallback(this))
            .build()

    fun insert() = viewModelScope.launch(Dispatchers.IO) {
        var word: Word
        if(limit < 200) {
            for (i in (limit+1)..(limit + PAGE_SIZE)) {
                if (i < 100) {
                    word = Word(0,"Palabra0" + i)
                } else {
                    word = Word(0,"Palabra" + i)
                }
                repository.insert(word)
            }
            limit += PAGE_SIZE
        }
    }



    private class boundCallback(val vm: WordViewModel) : PagedList.BoundaryCallback<Word>(){
        override fun onItemAtEndLoaded(itemAtEnd: Word) {
            vm.insert()
        }

        override fun onZeroItemsLoaded() {
            super.onZeroItemsLoaded()
        }
    }


}