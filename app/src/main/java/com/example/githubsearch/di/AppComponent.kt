package com.example.githubsearch.di

import android.app.Application
import com.example.githubsearch.data.api.RetrofitClient
import com.example.githubsearch.ui.view.fragments.screens.SearchFragment
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjection
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    DataModule::class,
    AndroidSupportInjectionModule::class,
    AndroidInjectionModule::class,
    ActivityBindingModule::class
])

interface AppComponent: AndroidInjector<ApplicationClass> {
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build() :AppComponent
    }
}

//interface AppComponent {
//    fun inject(fragment: SearchFragment)
//}
//
//class App : Application() {
//    val appComponent = DaggerAppComponent.create()
//}
