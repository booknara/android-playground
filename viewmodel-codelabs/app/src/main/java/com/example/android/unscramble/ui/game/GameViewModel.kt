package com.example.android.unscramble.ui.game

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel(application: Application, extra: String): ViewModel() {
    private val _score = MutableLiveData<Int>(0)
    private val _currentWordCount = MutableLiveData<Int>(0)
    private val _currentScrambledWord = MutableLiveData<String>()
    
    private val usedWords = mutableSetOf<String>()
    private lateinit var currentWord: String

    init {
        Log.d("GameFragment", "GameViewModel created!")
        getNextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment", "GameViewModel destroyed!")
    }
    
    val score: LiveData<Int>
        get() {
          return _score
        }

    val currentWordCount: LiveData<Int>
        get() {
            return _currentWordCount
        }

    val currentScrambledWord: LiveData<String>
        get() = _currentScrambledWord

    private fun getNextWord() {
        currentWord = allWordsList.random()
        // used word handling
        while (usedWords.contains(currentWord)) {
            currentWord = allWordsList.random()
        }
        usedWords.add(currentWord)
        val tempWord = currentWord.toCharArray()
        while (currentWord.equals(tempWord.shuffle())) {
            // same word handling after shuffle
            tempWord.shuffle()
        }

        _currentScrambledWord.value = String(tempWord)
        _currentWordCount.value = _currentWordCount.value?.inc()
    }

    /**
    * Returns true if the current word count is less than MAX_NO_OF_WORDS.
    * Updates the next word.
    */
    fun nextWord(): Boolean {
        return if (_currentWordCount.value!! < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else {
            false;
        }
    }
    
    private fun increaseScore() {
        _score.value = _score.value?.plus(SCORE_INCREASE) 
    }
    
    fun isUserWordCorrect(inputWord: String): Boolean {
        if (inputWord == currentWord) {
            increaseScore()
            return true
        }
        return false
    }

    fun reinitializeData() {
        _score.value = 0
        _currentWordCount.value = 0
        usedWords.clear()
        getNextWord()
    }
}
