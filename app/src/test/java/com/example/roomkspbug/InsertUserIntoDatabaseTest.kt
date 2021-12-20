package com.example.roomkspbug

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.time.LocalDate
import java.time.ZoneId

@RunWith(RobolectricTestRunner::class)
class InsertUserIntoDatabaseTest {
    @Test
    fun insertUserIntoDatabase() {
        val context: Context = ApplicationProvider.getApplicationContext()
        val db = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "database-name"
        )
            .allowMainThreadQueries() // just for testing purpose
            .build()

        val userDao = db.userDao()
        userDao.insert(
            User(
                uid = 1,
                firstName = "Keanu",
                lastName = "Reeves",
                bornAt = LocalDate.of(1964, 9, 2).atStartOfDay(ZoneId.of("GMT+2")).toInstant()
            )
        )
        val users: List<User> = userDao.getAll()
        assertEquals("Keanu", users.single().firstName)
    }
}