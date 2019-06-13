package com.example.roomwordsample.Adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomwordsample.Entities.Word
import com.example.roomwordsample.R

class WordListAdapter internal constructor(
    context: Context
) : PagedListAdapter<Word, WordListAdapter.WordViewHolder>(diffCallback) {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    //var words = emptyList<Word>() // Cached copy of words

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {

        Log.d("Update: ","Binding view holder at position $position")

        val current: Word? = getItem(position)

        with(holder){
            bind(current)
        }
    }

    override fun submitList(pagedList: PagedList<Word>?) {
        Log.d("Update: ","Test")
        super.submitList(pagedList)

    }
/*
    internal fun setWords(words: List<Word>) {
        this.words = words
        notifyDataSetChanged()
    }*/


    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var word: Word? = null

        val wordItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(item: Word?){
            word = item
            wordItemView.text = word?.let { it.word } ?: ""
        }

    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Word>() {
            override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean =
                oldItem.word == newItem.word

            override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean =
                oldItem == newItem
        }
    }
}