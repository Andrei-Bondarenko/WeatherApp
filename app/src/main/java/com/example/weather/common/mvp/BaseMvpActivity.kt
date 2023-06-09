package com.example.weather.common.mvp

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity

abstract class BaseMvpActivity<V : MvpView, P : MvpPresenter<V>> : AppCompatActivity(), MvpView {

    abstract val presenter: P
    private lateinit var langCode: String

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNCHECKED_CAST")
        presenter.attach(this as V)
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

}