package com.example.roomwordsample.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "word_table")
data class Word(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @NotNull @ColumnInfo(name = "word") val word: String
)