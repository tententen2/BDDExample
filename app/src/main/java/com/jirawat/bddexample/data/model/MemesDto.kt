package com.jirawat.bddexample.data.model

import com.google.gson.annotations.SerializedName

data class MemesDto(
        @SerializedName("success") val success: Boolean,
        @SerializedName("data") val data: Memes
)

data class Memes(
        @SerializedName("memes") val memes: List<Meme>
)
