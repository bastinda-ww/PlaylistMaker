package com.practicum.playlistmaker

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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

        initRecyclerView()

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

    private fun initRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.song_list)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val trackList: MutableList<Track> = mutableListOf()
        trackList.add(Track("Smells Like Teen Spirit", "Nirvana", "5:01", "https://is5-ssl.mzstatic.com/image/thumb/Music115/v4/7b/58/c2/7b58c21a-2b51-2bb2-e59a-9bb9b96ad8c3/00602567924166.rgb.jpg/100x100bb.jpg"))
        trackList.add(Track("Billie Jean", "Michael Jackson", "4:35", "https://is5-ssl.mzstatic.com/image/thumb/Music125/v4/3d/9d/38/3d9d3811-71f0-3a0e-1ada-3004e56ff852/827969428726.jpg/100x100bb.jpg"))
        trackList.add(Track("Stayin' Alive", "Bee Gees", "4:10", "https://is4-ssl.mzstatic.com/image/thumb/Music115/v4/1f/80/1f/1f801fc1-8c0f-ea3e-d3e5-387c6619619e/16UMGIM86640.rgb.jpg/100x100bb.jpg"))
        trackList.add(Track("Whole Lotta Love", "Led Zeppelin", "5:33", "https://is2-ssl.mzstatic.com/image/thumb/Music62/v4/7e/17/e3/7e17e33f-2efa-2a36-e916-7f808576cf6b/mzm.fyigqcbs.jpg/100x100bb.jpg"))
        trackList.add(Track("Sweet Child O'Mine", "Guns N' Roses", "5:03", "https://is5-ssl.mzstatic.com/image/thumb/Music125/v4/a0/4d/c4/a04dc484-03cc-02aa-fa82-5334fcb4bc16/18UMGIM24878.rgb.jpg/100x100bb.jpg"))

        val newsAdapter = TrackAdapter(trackList)
        recyclerView.adapter = newsAdapter
    }

    companion object {
        const val searchText = "SEARCH_TEXT"

        private var searchQuery = ""
    }

}