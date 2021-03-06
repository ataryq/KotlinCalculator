package com.example.melearning

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope
import javax.inject.Singleton

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope

@ActivityScope
@Component(dependencies = [ApplicationComponent::class],
    modules = [
        MainActivity::class,
        CalculationFragmentViewModelFactory::class])
interface ActivityComponent {
    fun inject(obj: BottomNavigationFragment)
    fun inject(obj: CalculationFragment)
}

class ActivityDaggerComponent {
    companion object {
        val instance = ActivityDaggerComponent()
    }

    private lateinit var activityComponent: ActivityComponent

    fun inject(obj: BottomNavigationFragment) = activityComponent.inject(obj)
    fun inject(obj: CalculationFragment) = activityComponent.inject(obj)

    fun build(activity: MainActivity) {
        activityComponent = DaggerActivityComponent.builder()
            .applicationComponent(ApplicationDaggerComponent.instance.applicationComponent)
            .mainActivity(activity)
            .build()
        Utils.mActivity = activity
    }
}

