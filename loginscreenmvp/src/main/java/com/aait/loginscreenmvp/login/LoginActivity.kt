package com.aait.loginscreenmvp.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.aait.loginscreenmvp.R
import com.aait.loginscreenmvp.repo.UserRepo
import kotlinx.android.synthetic.main.activity_login.*
import android.arch.lifecycle.ProcessLifecycleOwner
import android.content.Intent
import android.view.View
import com.aait.loginscreenmvp.MyLifeCycleObserver
import com.aait.loginscreenmvp.main.MainActivity


class LoginActivity : AppCompatActivity(),LoginContract.LoginView {

    override fun showSuccess() {
        Toast.makeText(this,"login success",Toast.LENGTH_SHORT).show()
    }

    override fun showError() {
        Toast.makeText(this,"user not found",Toast.LENGTH_SHORT).show()
    }

    override fun showErrorEmailInput(msg: String) {
        etEmail.error=msg
    }

    override fun showErrorPassInput(msg: String) {
        etPassword.error=msg
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val presenter = LoginPresenter(UserRepo())
        presenter.setView(this)

        loginBtn.setOnClickListener {
            presenter.login(etEmail.text.toString(),etPassword.text.toString())
        }
    }
    fun onMain(view:View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
