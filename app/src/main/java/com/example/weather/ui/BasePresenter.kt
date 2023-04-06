package com.example.weather.ui

import androidx.annotation.CallSuper

abstract class BasePresenter<V : MVPView> : MVPPresenter<V> {
    protected var view: V? = null
        private set

    @CallSuper
    override fun attach(view: V) {
      this.view = view
    }

    @CallSuper
    override fun detach() {
        view = null
    }

}