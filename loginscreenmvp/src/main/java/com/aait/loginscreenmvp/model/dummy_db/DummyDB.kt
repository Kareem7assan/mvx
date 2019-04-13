package com.aait.loginscreenmvp.model.dummy_db

import com.aait.loginscreenmvp.model.User

interface DummyDB {
    fun onDelete(id:Int)
    fun onAdd(user: User)
    fun onSelect(id:Int):Boolean
}