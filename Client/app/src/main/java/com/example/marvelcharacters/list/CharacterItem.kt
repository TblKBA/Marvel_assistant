package com.example.marvelcharacters.list

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CharacterItem(@SerializedName("characterImage") var characterImage: String = "",
                         @SerializedName("characterImageLarge") var characterImageLarge: String = "",
                         @SerializedName("characterName") var characterName: String = "",
                         @SerializedName("characterDesc") var characterDesc: String? = "") : Serializable {

}

data class ComicsItem(
   // @SerializedName("id") var id: Int,
    @SerializedName("comicsImage") var comicsImage: String = "",
    @SerializedName("comicsImageLarge") var comicsImageLarge: String = "",
    @SerializedName("comicsTitle") var comicsTitle: String = "",
    @SerializedName("comicsDesc") var comicsDesc: String? = "") : Serializable {

}

data class CreatorsItem(
    // @SerializedName("id") var id: Int,
    @SerializedName("creatorsImage") var creatorsImage: String = "",
    @SerializedName("creatorsImageLarge") var creatorsImageLarge: String = "",
    @SerializedName("creatorsFullname") var creatorsFullname : String = "") : Serializable {
}