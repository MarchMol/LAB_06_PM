package com.example.lab_06_pm.model

import com.google.gson.annotations.SerializedName

data class Curies(
    @SerializedName("href"      ) var href      : String?  = null,
    @SerializedName("name"      ) var name      : String?  = null,
    @SerializedName("templated" ) var templated : Boolean? = null
)
