package id.haaweejee.rawgandroid.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.haaweejee.rawgandroid.data.datasource.local.table.GameTable
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM game_list")
    fun getAllFavoriteGames(): Flow<List<GameTable>>

    @Query("SELECT * FROM game_list WHERE id = :id")
    fun getGameById(id: Int): Flow<GameTable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(data: GameTable)

    @Query("DELETE FROM game_list WHERE id = :id")
    fun deleteGameById(id: Int)
}