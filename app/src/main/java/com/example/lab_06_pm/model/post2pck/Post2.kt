package com.example.lab_06_pm.model.post2pck

import com.google.gson.annotations.SerializedName

data class Post2(
    @SerializedName("photos" ) var photos : ArrayList<Photos> = arrayListOf()

)
