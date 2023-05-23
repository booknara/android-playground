package com.booknara.android.app.roomwordsample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.booknara.android.app.roomwordsample.db.Word
import kotlinx.coroutines.launch

class WordViewModel(private val wordRepository: WordRepository): ViewModel() {
    val allWords = wordRepository.allWords.asLiveData()
    
    fun insert(word: Word) {
        viewModelScope.launch { 
            wordRepository.insert(word)
        }
    }
}
