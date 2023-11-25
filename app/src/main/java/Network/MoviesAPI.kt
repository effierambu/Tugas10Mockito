package network
import network.TmdbResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    @GET("search/movie")
    fun searchMovie(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): Call<TmdbResponse>
}
