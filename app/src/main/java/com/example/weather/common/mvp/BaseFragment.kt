package com.example.weather.common.mvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes layoutRes: Int) : Fragment(layoutRes) {
    private var rootView: View? = null
    @Volatile private var counter = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return rootView ?: super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        counter++
        if (!isInitialSetup()) return
        rootView = view
    }

    /**
     * Переменная которая помогает восстановить состояние
     * фрагмента при возвращении со второго фрагмента назад
     * */
    protected fun isInitialSetup() = counter <= 1
}