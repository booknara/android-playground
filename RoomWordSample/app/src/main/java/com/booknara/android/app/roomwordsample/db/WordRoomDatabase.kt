package com.booknara.android.app.roomwordsample.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Word::class), version = 1, exportSchema = false)
abstract class WordRoomDatabase: RoomDatabase() {
    abstract fun wordDao(): WordDao
    
    private class WordDatabaseCallback(val coroutineScope: CoroutineScope): RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                coroutineScope.launch {
                    populateDatabase(database.wordDao())        
                }
            }
        }
        
        suspend fun populateDatabase(wordDao: WordDao) {
            wordDao.deleteAll()

            val world = Word(word = "world")
            wordDao.insert(world)            
            val hello = Word(word = "hello1")
            wordDao.insert(hello)

            val todo = Word(word = "todo")
            wordDao.insert(todo)            
        }
    }
    
    companion object {
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null
        
        fun getDatabase(context: Context, coroutineScope: CoroutineScope) : WordRoomDatabase? {
            if (INSTANCE == null) synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, 
                    WordRoomDatabase::class.java, "word_database")
                    .addCallback(WordDatabaseCallback(coroutineScope))
                    .build()
                INSTANCE = instance
            }
            return INSTANCE
        }
    }
}
