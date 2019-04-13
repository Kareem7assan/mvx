package com.aait.loginscreenmvp.model.dummy_db

import android.content.res.Resources
import com.aait.loginscreenmvp.model.User

class Dummy(val db: DummyDB){
    @Throws(Resources.NotFoundException::class)
    fun select(id: Int) {
        db.onSelect(id)
    }
    fun hasUser(mail:String):Boolean{
        val isExist = db.onSelect(2)
        return isExist
    }
}