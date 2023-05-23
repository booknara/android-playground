package com.booknara.android.app.roomwordsample

import android.app.Application
import com.booknara.android.app.roomwordsample.db.WordRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WordApplication: Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())
    
    private val database by lazy { WordRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { WordRepository(database!!.wordDao()) }
}
