package id.haaweejee.rawgandroid.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import id.haaweejee.rawgandroid.data.datasource.local.dao.GameDao
import id.haaweejee.rawgandroid.data.datasource.local.table.GameTable

@Database(
    entities = [
        GameTable::class,
    ],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao
}
