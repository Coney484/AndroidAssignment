package com.example.androidassignment.model


import java.util.*

data class UserModel(
    var id: Int = getAutoId(),
    var name: String = "",
    var email: String = "",
    var phone: String = "",
    var address: String = ""
) {
    companion object {
        fun getAutoId(): Int {
            val random = Random()
            return random.nextInt(100)
        }
    }
}