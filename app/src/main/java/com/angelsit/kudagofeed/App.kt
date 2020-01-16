package com.angelsit.kudagofeed

import android.app.Application

/**
 * Класс для реализации доступа к контексту приложения
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }

    companion object {
        private lateinit var mInstance: App

        @JvmStatic
        fun getAppContext() = mInstance
    }
}