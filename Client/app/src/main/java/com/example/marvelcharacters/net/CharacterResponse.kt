package com.example.marvelcharacters.net

import com.google.gson.annotations.SerializedName


//Heroes {
data class CharacterResponse(
    @SerializedName("code")
    var code: String,
    @SerializedName("data")
    var data: CharacterData
)

data class CharacterData(
    @SerializedName("count")
    var count: String,
    @SerializedName("results")
    var results: ArrayList<CharacterResult>
)

data class CharacterResult(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("thumbnail")
    var thumbnail: Thumbnail
)
//Heroes }

//Comics {
data class ComicsResponse(
    @SerializedName("code")
    var code: String,
    @SerializedName("data")
    var data: ComicsData
)

data class ComicsData(
    @SerializedName("count")
    var count: String,
    @SerializedName("results")
    var results: ArrayList<ComicsResult>
)
data class ComicsResult(
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("thumbnail")
    var thumbnail: Thumbnail
)
//Comics }

//Creators {
data class CreatorsResponse(
    @SerializedName("code")
    var code: String,
    @SerializedName("data")
    var data: CreatorsData
)

data class CreatorsData(
    @SerializedName("count")
    var count: String,
    @SerializedName("results")
    var results: ArrayList<CreatorsResult>
)
data class CreatorsResult(
    @SerializedName("id")
    var id: Int,
    @SerializedName("fullName")
    var fullname : String,
    @SerializedName("thumbnail")
    var thumbnail: Thumbnail
)
//Creators }

data class Thumbnail(
    @SerializedName("path")
    var path: String,
    @SerializedName("extension")
    var extension: String
)

