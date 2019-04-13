package com.aait.loginscreenmvp

import android.util.Log
import com.aait.loginscreenmvp.login.LoginContract
import com.aait.loginscreenmvp.login.LoginPresenter
import com.aait.loginscreenmvp.model.User
import com.aait.loginscreenmvp.repo.UserRepo
import com.nhaarman.mockitokotlin2.*
/*
import com.nhaarman.mockito_kotlin.MockitoKotlin
import com.nhaarman.mockito_kotlin.MockitoKotlinException
*/
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatcher
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import android.util.Patterns
import org.junit.Assert
import org.mockito.Mock

@RunWith(JUnit4::class)
class PresenterTest {
private lateinit var presenter:LoginPresenter
private lateinit var mockUserRepo:UserRepo
private lateinit var mockView: LoginContract.LoginView
    /*
    * mocking the repo because it may depended on other dependency as retrofit
    * mocking view too
    * */

    //it renamable run before test
    @Before
    fun setUp(){
        mockUserRepo = Mockito.mock(UserRepo::class.java)
        val user = User()
        user.id=1
        user.name="asd"
        user.password="pass"
        mockView = Mockito.mock(LoginContract.LoginView::class.java)
        Mockito.`when`(mockUserRepo.getUser(any())).thenReturn(user)
        presenter= LoginPresenter(mockUserRepo)
        presenter.setView(mockView)

    }

    @Test
    fun verifyNoInterAction(){
        presenter.onResume()
        presenter.onPause()
        Mockito.verifyZeroInteractions(mockView)

    }
    /*@Test
    fun onLogin(){
        var mail="asd@as.cas"
        var pass="pass"


    }*/

    @Test
    fun testValidateInputsTrue(){
        var mail="asd@as.cas"
        var pass="asd"
        presenter.login(mail,pass)

     //   presenter.validateInputs("asd@as.cas", "asd")
        //Assert.assertEquals(validateInputs,true)
        verify(mockView, times(0)).showErrorEmailInput("email is incorrect")
        verify(mockView, times(0)).showErrorPassInput("password is incorrect")
        //presenter.checkUser(User())
        presenter.validateInputs("asd","asd")
        presenter.validateInputs("asd@asd.ca","")
        //when error validation show once error for each statement
        verify(mockView, times(1)).showErrorEmailInput("email is incorrect")
        verify(mockView, times(1)).showErrorPassInput("password is incorrect")

        verify(mockUserRepo, times(1)).getUser(any())
        verify(mockUserRepo, times(1)).saveUser(any())
        verify(mockView, times(1)).showSuccess()
        Mockito.`when`(mockUserRepo.getUser(any())).thenReturn(null)
        presenter.checkUser(User())
        verify(mockView, times(1)).showError()
    }

    @Test
    fun testValidateInputsFalse(){
        presenter.validateInputs("asd@as","asd")
        presenter.validateInputs("","asd")
        /*//check email is false or empty or null and verify error message
        Assert.assertEquals(presenter.validateInputs("asd@as","asd"),false)
        Assert.assertEquals(presenter.validateInputs("","asd"),false)*/
        verify(mockView, times(2)).showErrorEmailInput("email is incorrect")
        //check password is Empty and verify error message password
        Assert.assertEquals(presenter.validateInputs("asd@gas.sa",""),false)
        verify(mockView, times(1)).showErrorPassInput("password is incorrect")
    }


}