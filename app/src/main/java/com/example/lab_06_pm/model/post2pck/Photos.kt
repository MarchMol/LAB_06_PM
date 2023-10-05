package com.example.lab_06_pm.model.post2pck

import com.google.gson.annotations.SerializedName

data class Photos(

    @SerializedName("image") var image: Image?= Image()
)
