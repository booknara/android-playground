package com.booknara.android.app.roomwordsample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class NewWordActivity : AppCompatActivity() {
    private lateinit var editText: EditText
    private lateinit var saveButton: Button
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)
        editText = findViewById(R.id.edit_word)
        saveButton = findViewById(R.id.button_save)
        
        saveButton.setOnClickListener {
            val intent = Intent()
            if (TextUtils.isEmpty(editText.text.toString())) {
                setResult(Activity.RESULT_CANCELED, intent)
            } else {
                intent.putExtra(EXTRA_REPLY, editText.text.toString())
                setResult(Activity.RESULT_OK, intent)
            }
            finish()
        }
    }
    
    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}
