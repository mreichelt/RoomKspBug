package com.example.roomkspbug

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.time.Instant

@TypeConverters(
    TimeConverter::class,
    AwesomenessConverter::class,
)
@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}

class TimeConverter {
    @TypeConverter
    fun instantToValue(value: Instant?): String? = value?.toString()

    @TypeConverter
    fun valueToInstant(value: String?): Instant? = value?.let { Instant.parse(it) }
}

class AwesomenessConverter {
    @TypeConverter
    fun awesomenessToValue(value: Awesomeness): String = value.toString()

    @TypeConverter
    fun valueToAwesomeness(value: String?): Awesomeness = when (value) {
        "AWESOME" -> Awesomeness.AWESOME
        "SUPER_DUPER_AWESOME" -> Awesomeness.SUPER_DUPER_AWESOME
        else -> Awesomeness.AWESOME // everyone is awesome by default
    }
}
