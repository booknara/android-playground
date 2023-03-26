package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {
    private var _score = 0
    private var _currentWordCount = 0
    private val usedWords = mutableSetOf<String>()
    private lateinit var _currentScrambledWord: String
    private lateinit var currentWord: String

    init {
        Log.d("GameFragment", "GameViewModel created!")
        getNextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment", "GameViewModel destroyed!")
    }
    
    val score: Int
        get() {
          return _score
        }

    val currentWordCount: Int
        get() {
            return _currentWordCount
        }

    val currentScrambledWord: String
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

        _currentWordCount++
        _currentScrambledWord = String(tempWord)
    }

    /**
    * Returns true if the current word count is less than MAX_NO_OF_WORDS.
    * Updates the next word.
    */
    fun nextWord(): Boolean {
        return if (currentWordCount < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else {
            false;
        }
    }
    
    private fun increaseScore() {
        _score += SCORE_INCREASE
    }
    
    fun isUserWordCorrect(inputWord: String): Boolean {
        if (inputWord == currentWord) {
            increaseScore()
            return true
        }
        return false
    }

    fun reinitializeData() {
        _score = 0
        _currentWordCount = 0
        usedWords.clear()
        getNextWord()
    }
}
