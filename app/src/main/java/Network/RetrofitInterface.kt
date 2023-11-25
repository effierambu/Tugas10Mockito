package network

import com.pemrogandroid.movieku.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

    // Example endpoint: https://api.themoviedb.org/3/discover/movie

    @GET("search/movie")
    fun searchMovie(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): Call<TmdbResponse>
}
