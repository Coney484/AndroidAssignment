package com.example.androidassignment.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Results(
    @SerializedName("email")
    var email: String,
    @SerializedName("location")
    var location: Location,
    @SerializedName("name")
    var name: Name,
    @SerializedName("phone")
    var phone: String,
    @SerializedName("picture")
    var picture: Picture
) : Serializable