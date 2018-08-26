package me.mxcsyounes.templateapp.networking.utilities

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface WebService<T> {
    companion object {
        // here goes the base URL
        const val BASE_URL = ""
    }

    val retrofit: Retrofit
        get() = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    @GET()
    fun getT(): Call<List<T>>
}
