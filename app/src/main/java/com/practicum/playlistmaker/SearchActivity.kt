package com.practicum.playlistmaker

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged

class SearchActivity : AppCompatActivity() {
    private lateinit var searchField: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val backButton = findViewById<ImageView>(R.id.back_button)
        searchField = findViewById<EditText>(R.id.search_field)
        val clearSearchButton = findViewById<ImageView>(R.id.clear_button)
        clearSearchButton.visibility = View.GONE

        backButton.setOnClickListener {
            finish()
        }

        searchField.doOnTextChanged{text, start, before, count ->
            clearSearchButton.visibility = clearButtonVisibility(text)
            searchQuery = text?.toString() ?: ""
        }

/*        val textWatcher = object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                clearSearchButton.visibility = clearButtonVisibility(s)
                searchQuery = s?.toString() ?: ""
            }

        }*/
//        searchField.addTextChangedListener(textWatcher)

        clearSearchButton.setOnClickListener {
            searchField.text.clear()
            val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager
            inputMethodManager?.hideSoftInputFromWindow(searchField.windowToken, 0)
        }

        savedInstanceState?.let {
            restoreEditTextState(it)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(searchText, searchQuery)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        restoreEditTextState(savedInstanceState)
    }

    private fun restoreEditTextState(savedInstanceState: Bundle) {
        searchQuery = savedInstanceState.getString(searchText, "")
        if (!searchQuery.isEmpty()) {
            searchField.setText(searchQuery)
            searchField.setSelection(searchQuery.length)
        }
    }

    private fun clearButtonVisibility(s: CharSequence?): Int {
        return if (s.isNullOrEmpty()) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    companion object {
        const val searchText = "SEARCH_TEXT"

        private var searchQuery = ""
    }

}