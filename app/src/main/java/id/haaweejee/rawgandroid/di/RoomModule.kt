package id.haaweejee.rawgandroid.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.haaweejee.rawgandroid.data.datasource.local.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase =
        Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "rawg.db")
            .build()

    @Provides
    fun provideGameDao(db: AppDatabase) = db.gameDao()
}
