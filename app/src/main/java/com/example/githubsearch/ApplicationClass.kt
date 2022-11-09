package com.example.githubsearch

import com.example.githubsearch.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class ApplicationClass: DaggerApplication() {
    private val applicationInjector = DaggerAppComponent
        .builder()
        .application(this)
        .build()

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = applicationInjector
}