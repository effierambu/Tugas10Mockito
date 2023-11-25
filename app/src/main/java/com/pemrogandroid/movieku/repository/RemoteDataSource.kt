package com.pemrogandroid.movieku.repository

import androidx.lifecycle.MutableLiveData
import com.pemrogandroid.movieku.MovieKuApp
import com.pemrogandroid.movieku.R
import network.RetrofitClient
import network.TmdbResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class RemoteDataSource {
    val TMDBMovies: MutableLiveData<TmdbResponse> = MutableLiveData<TmdbResponse>()

    fun searchResultsObservale(query: String) {
        val enqueue = RetrofitClient.moviesApi
            .searchMovie(MovieKuApp.instance.resources.getString(R.string.TMDB_API_KEY), query)
            .enqueue(object : Callback<TmdbResponse?> {
                override fun onResponse(
                    call: Call<TmdbResponse?>,
                    response: Response<TmdbResponse?>
                ) {
                    TMDBMovies.postValue(response.body())
                }

                override fun onFailure(call: Call<TmdbResponse?>, t: Throwable) {
                    TMDBMovies.postValue(null)
                }
            })
    }
}
