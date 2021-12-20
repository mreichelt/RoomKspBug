package com.example.roomkspbug

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant

@Entity
data class User(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    // note: there is no 'awesomeness' field here at all - still 'AWESOME' gets written to the DB
    // as the value for `born_at`
    @ColumnInfo(name = "born_at") val bornAt: Instant,
)
