package com.example.githubsearch.di

import android.app.Application
import com.example.githubsearch.ApplicationClass
import com.example.githubsearch.di.modules.ActivityBindingModule
import com.example.githubsearch.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBindingModule::class,
        AppModule::class
    ]
)
interface AppComponent : AndroidInjector<ApplicationClass> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(applicationClass: ApplicationClass)
}
