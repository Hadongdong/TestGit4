package com.example.noticesubscribe

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.firebase.firestore.ServerTimestamp
import com.google.type.Date

data class Keyword(
    @PrimaryKey var key: String = "",
    //@PrimaryKey var timestamp : Long? = null
    //@ServerTimestamp var keyword_added_date: Date? = null
)