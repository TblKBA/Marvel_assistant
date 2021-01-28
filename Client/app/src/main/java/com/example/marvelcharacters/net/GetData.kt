package com.example.marvelcharacters.net

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GetHeroes {
    @GET("v1/public/characters")
    fun getData(@Query("apikey") apikey: String,
                @Query("ts") ts: Int,
                @Query("hash") hash: String) : Observable<CharacterResponse>

}

interface GetComics {
    @GET("/v1/public/comics")
    fun getData(@Query("apikey") apikey: String,
                @Query("ts") ts: Int,
                @Query("hash") hash: String) : Observable<ComicsResponse>

}

interface GetCreators {
    @GET("/v1/public/creators")
    fun getData(@Query("apikey") apikey: String,
                @Query("ts") ts: Int,
                @Query("hash") hash: String) : Observable<CreatorsResponse>

}