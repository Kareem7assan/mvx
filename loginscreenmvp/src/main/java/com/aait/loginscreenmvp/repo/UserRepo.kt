package com.aait.loginscreenmvp.repo

import android.util.Log
import com.aait.loginscreenmvp.model.User

open class UserRepo {

    fun saveUser(user: User){
        Log.e("user", "$user stored")
    }
    fun getUser(user: User): User? {
        if (user.id==1){
        return user
    }
       return null
    }
}