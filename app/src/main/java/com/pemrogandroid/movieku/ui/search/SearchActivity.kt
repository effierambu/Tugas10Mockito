package com.pemrogandroid.movieku.ui.search

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.pemrogandroid.movieku.databinding.ActivitySearchMovieBinding
import com.pemrogandroid.movieku.viewmodel.MoviesViewModel

class SearchActivity : AppCompatActivity() {
    private val TAG = "SearchActivity"
    private lateinit var binding: ActivitySearchMovieBinding
    private lateinit var adapter: SearchAdapter
    private lateinit var query: String
    private val movieViewModel by viewModels<MoviesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        query = intent.getStringExtra(SEARCH_QUERY)!!

        binding.searchResultsRecyclerview.layoutManager = LinearLayoutManager(this)
    }

    override fun onStart() {
        super.onStart()
        binding.progressBar.visibility = VISIBLE
        getSearchResults(query)
    }

    override fun onStop() {
        super.onStop()
    }

    fun getSearchResults(query: String) {
    }

    /*
    fun displayResult(tmdbResponse: TmdbResponse) {
        binding.progressBar.visibility = INVISIBLE

        if (tmdbResponse.totalResults == null || tmdbResponse.totalResults == 0) {
            binding.searchResultsRecyclerview.visibility = INVISIBLE
            binding.noMoviesTextview.visibility = VISIBLE
        } else {
            adapter = SearchAdapter(
                tmdbResponse.results
                    ?: arrayListOf(), this@SearchActivity, itemListener
            )
            binding.searchResultsRecyclerview.adapter = adapter

            binding.searchResultsRecyclerview.visibility = VISIBLE
            binding.noMoviesTextview.visibility = INVISIBLE
        }
    }
    */
    companion object {

        val SEARCH_QUERY = "searchQuery"
        val EXTRA_ID = "SearchActivity.ID"
        val EXTRA_TITLE = "SearchActivity.TITLE_REPLY"
        val EXTRA_RELEASE_DATE = "SearchActivity.RELEASE_DATE_REPLY"
        val EXTRA_POSTER_PATH = "SearchActivity.POSTER_PATH_REPLY"
    }

    /**
     * Listener for clicks on tasks in the ListView.
     */
    internal var itemListener: RecyclerItemListener = object : RecyclerItemListener {
        override fun onItemClick(v: View, position: Int) {
            val movie = adapter.getItemAtPosition(position)

            val replyIntent = Intent()
            replyIntent.putExtra(EXTRA_ID, movie.id)
            replyIntent.putExtra(EXTRA_TITLE, movie.title)
            replyIntent.putExtra(EXTRA_RELEASE_DATE, movie.getReleaseYearFromDate())
            replyIntent.putExtra(EXTRA_POSTER_PATH, movie.posterPath)
            setResult(Activity.RESULT_OK, replyIntent)

            finish()
        }
    }

    interface RecyclerItemListener {
        fun onItemClick(v: View, position: Int)
    }

}

