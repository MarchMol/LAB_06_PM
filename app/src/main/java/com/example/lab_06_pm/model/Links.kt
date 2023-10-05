package com.example.lab_06_pm.model

import com.google.gson.annotations.SerializedName

data class Links(

    @SerializedName("curies"  ) var curies  : ArrayList<Curies>  = arrayListOf(),
    @SerializedName("self"    ) var self    : Self?              = Self(),
    @SerializedName("ua:item" ) var ua_item : ArrayList<Ua_item> = arrayListOf()

)
