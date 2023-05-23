package com.booknara.android.app.roomwordsample

import androidx.annotation.WorkerThread
import com.booknara.android.app.roomwordsample.db.Word
import com.booknara.android.app.roomwordsample.db.WordDao
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {
    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()
    
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}
