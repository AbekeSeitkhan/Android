package com.example.pizzahut.model

import androidx.annotation.DrawableRes

data class Pizza(
    val title:String,
    @DrawableRes val img:Int,
    val desc:String,
    val price:Int,
    val type:String,
    val size:String,
)
