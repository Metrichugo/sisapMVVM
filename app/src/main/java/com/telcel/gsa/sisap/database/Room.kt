package com.telcel.gsa.sisap.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FolioDao {
    @Query("select * from databasefolio")
    fun getFolios(): LiveData<List<DatabaseFolio>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg folios: DatabaseFolio)
}

@Database(entities = [DatabaseFolio::class], version = 1)
abstract class SisapDatabase : RoomDatabase() {
    abstract val folioDao: FolioDao
}

private lateinit var INSTANCE: SisapDatabase

fun getDatabase(context: Context): SisapDatabase {
    synchronized(SisapDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                SisapDatabase::class.java, "Sisap"
            ).build()
        }
    }
    return INSTANCE
}

