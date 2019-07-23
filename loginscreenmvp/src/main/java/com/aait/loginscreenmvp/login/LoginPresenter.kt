package com.aait.loginscreenmvp.login

import android.util.Patterns
import com.aait.loginscreenmvp.model.User
import com.aait.loginscreenmvp.repo.UserRepo

class LoginPresenter(val repo: UserRepo) :LoginContract.LoginPresenter{


    private lateinit var loginView:LoginContract.LoginView
    override fun setView(view: LoginContract.LoginView) {
        loginView=view

    }

    override fun login(email:String,password:String) {
      if (validateInputs(email,password)){
          checkUser(User(1,email,password))
      }
    }



    override fun checkUser(user: User) {
            if (repo.getUser(user)!=null){
                saveUser(user)
                loginView.showSuccess()
            }
        else{
                loginView.showError()
            }
    }

    override fun saveUser(user: User) {
        repo.saveUser(user)
    }


    override fun validateInputs(email:String,password:String):Boolean {
        when{
            email.isNullOrBlank() || !email.matches("^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$".toRegex()) || email.trim { it <= ' ' }.isEmpty()->{
                loginView.showErrorEmailInput("email is incorrect")
                return false
            }
            password.isNullOrBlank()-> {
                loginView.showErrorPassInput("password is incorrect")
                return false
            }
            else    ->  return true
        }

    }

    override fun onResume() {

    }

    override fun onPause() {

    }
}