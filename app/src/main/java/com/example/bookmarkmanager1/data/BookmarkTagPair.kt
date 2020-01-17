package com.example.bookmarkmanager1.data

import  androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(
        entity = Bookmark::class,
        parentColumns = ["bId"],
        childColumns  = ["bookmarkId"],
        onDelete = CASCADE),
                        ForeignKey(
        entity = Tag::class,
        parentColumns = ["tId"],
        childColumns = ["tagId"],
        onDelete = CASCADE)])
data class BookmarkTagPair(
    @PrimaryKey(autoGenerate = true) val pId: Int?,
    val bookmarkId: Int,
    val tagId: Int
)
