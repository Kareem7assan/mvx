package com.aait.loginscreenmvp.dummyTest

import android.view.View
import com.nhaarman.mockitokotlin2.eq
import org.junit.Assert

class MyAssert {
        fun assertView(view:View){
            Assert.assertNotNull(view)
            Assert.assertEquals(view.visibility , eq(View.VISIBLE))
        }
}