package com.example.bookmarkmanager1.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bookmarkmanager1.data.BookmarkDao
import com.example.bookmarkmanager1.data.TagDao
import com.example.bookmarkmanager1.data.Tag

@Dao
interface BookmarkTagPairDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPair(bookmarkTagPair: BookmarkTagPair)

    @Delete
    fun deletePair(bookmarkTagPair: BookmarkTagPair)

    @Query(
        "SElECT b.*  " +
                "FROM tag t, bookmark b, bookmarktagpair bt " +
                "WHERE bt.tagid = t.tid " +
                "AND t.tagname = :tag " +
                "AND b.bid = bt.bookmarkid " +
                "GROUP BY b.bid"
    )
    fun getBookmarksWithTag(tag: String): List<Bookmark>

    @Query(
        "SELECT t.tagname  " +
                "FROM tag t, bookmark b, bookmarktagpair bt " +
                "WHERE b.bid = bt.bookmarkid " +
                "AND b.url = :url " +
                "AND bt.tagid = t.tid " +
                "GROUP BY t.tid"
    )
    fun getTagsWithBookmark(url: String): LiveData<List<String>>

    @Query(
        "INSERT INTO bookmarktagpair (bookmarkid, tagid) " +
                "VALUES ("+
                "(SELECT bookmark.bid FROM bookmark WHERE url = :url), "+
                "(SELECT tag.tid FROM tag WHERE tagname = :tag)"+
                ")"
    )
    fun addBookmarkTagPair(url: String, tag: String)
}