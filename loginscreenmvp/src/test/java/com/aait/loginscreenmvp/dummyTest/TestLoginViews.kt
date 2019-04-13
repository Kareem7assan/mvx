package com.aait.loginscreenmvp.dummyTest

import android.content.res.Resources
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.aait.loginscreenmvp.BuildConfig
import com.aait.loginscreenmvp.R
import com.aait.loginscreenmvp.login.LoginActivity
import com.aait.loginscreenmvp.main.MainActivity
import com.aait.loginscreenmvp.model.dummy_db.Dummy
import com.aait.loginscreenmvp.model.dummy_db.DummyDB
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import kotlinx.android.synthetic.main.activity_login.view.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.internal.stubbing.answers.ThrowsException
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.robolectric.shadow.api.Shadow
import org.robolectric.shadows.ShadowActivity
import org.robolectric.shadows.ShadowToast
import java.io.IOException
import kotlin.Exception
@RunWith(RobolectricTestRunner::class)
class TestLoginViews {
    private lateinit var dummy:Dummy
    private lateinit var mockDummyDB: DummyDB
    private lateinit var activity:LoginActivity
    private lateinit var myAssert: MyAssert
    private lateinit var etMail: EditText
    private lateinit var etPass: EditText
    private lateinit var btn: Button
    private lateinit var btn2: Button

    @Before
    fun setup(){
         activity = Robolectric.setupActivity(LoginActivity::class.java)
         myAssert = MyAssert()
         etMail=activity.findViewById<EditText>(R.id.etEmail)
         etPass=activity.findViewById<EditText>(R.id.etPassword)
         btn=activity.findViewById<Button>(R.id.loginBtn)
         btn2=activity.findViewById<Button>(R.id.goBtn)
    }

    /*
    * test initials
    * */
    @Test
    fun shouldActivityNotNull(){
         activity = Robolectric.setupActivity(LoginActivity::class.java)
        Assert.assertNotNull(activity)
    }
    @Test
    fun shouldHaveMail(){
        myAssert.assertView(etMail)
        Assert.assertEquals(etMail.hint.toString(),"email")
    }
    @Test
    fun shouldHaveButton(){
        myAssert.assertView(btn)

    }

    /*
    * test hints login on pass
    * */
    @Test
    fun shouldHavePass(){
        myAssert.assertView(etPass)
        Assert.assertEquals(etPass.hint.toString(),"password")
    }

/*
* test login
* */

    @Test
    fun shouldMailAndPasswordIdent(){
        etMail.setText("asd@asd.com")
        etPass.setText("asdasd")
        btn.performClick()
        Assert.assertEquals(ShadowToast.getTextOfLatestToast(),"login success")
    }

/*
* test error login on email
* */
    @Test
    fun shouldNotLoginEmailError(){
        etMail.setText("asd.com")
        etPass.setText("asdasd")
        btn.performClick()
        Assert.assertEquals(etMail.error,"email is incorrect")
    }
/*
* test intent
* */
    @Test
    fun shouldGoMainIntent(){
        btn2.performClick()
        val shadowActivity = Shadows.shadowOf(activity)
        val intent = shadowActivity.nextStartedActivity
        val shadowOf = Shadows.shadowOf(intent)
        Assert.assertEquals(shadowOf.intentClass,MainActivity::class.java)
    }
    /*
    * test error pass on email
    * */
    @Test
    fun shouldNotLoginPassError(){
        etMail.setText("asd@asd.com")
        etPass.setText("")
        btn.performClick()
        Assert.assertEquals(etPass.error,"password is incorrect")
    }
/*
* use doReturn -> when
*                 if there is no return type
* */

    @Test
    @Throws(Resources.NotFoundException::class)
    fun shouldUserExistThrow() {
        Mockito.doReturn(Resources.NotFoundException::class.java).`when`(mockDummyDB).onAdd(any())
        dummy.select(any())
    }


}