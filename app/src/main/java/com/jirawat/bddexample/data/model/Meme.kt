package com.jirawat.bddexample.data.model

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable


const val EXTRA_MEME = "extra_meme"

@SuppressLint("ParcelCreator")
data class Meme(
        val name: String = "",
        val imageUrl: String = "",
        val imageWidth: Int = 0,
        val imageHeight: Int = 0
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(name)
        writeString(imageUrl)
        writeInt(imageWidth)
        writeInt(imageHeight)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Meme> = object : Parcelable.Creator<Meme> {
            override fun createFromParcel(source: Parcel): Meme = Meme(source)
            override fun newArray(size: Int): Array<Meme?> = arrayOfNulls(size)
        }
    }
}
