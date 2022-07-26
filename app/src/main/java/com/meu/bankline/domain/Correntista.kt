package com.meu.bankline.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Correntista(
    val id:Int
) : Parcelable