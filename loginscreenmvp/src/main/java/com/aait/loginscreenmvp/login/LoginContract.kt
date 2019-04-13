package com.aait.loginscreenmvp.login

import com.aait.loginscreenmvp.base.LifeCyclePresenter
import com.aait.loginscreenmvp.model.User

interface LoginContract {
    interface LoginView{
        fun showSuccess()
        fun showError()
        fun showErrorEmailInput(msg:String)
        fun showErrorPassInput(msg:String)

    }
    interface LoginPresenter : LifeCyclePresenter {
        fun login(email:String,password:String)
        fun setView(view:LoginView)
        fun checkUser(user: User)
        fun saveUser(user: User)
        fun validateInputs(email:String,password:String):Boolean

        }
}