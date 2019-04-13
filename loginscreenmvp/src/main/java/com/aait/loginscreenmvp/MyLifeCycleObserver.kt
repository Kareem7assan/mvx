package com.aait.loginscreenmvp

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent



class MyLifeCycleObserver(val onLifeCycle:OnLifeCycle) : LifecycleObserver {

public interface OnLifeCycle{
    fun onForground()
    fun onBackGround()
}

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun onEnterForeground() {
            //run the code we need
            onLifeCycle.onForground()

        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun onEnterBackground() {
            //run the code we need
            onLifeCycle.onBackGround()
        }



}