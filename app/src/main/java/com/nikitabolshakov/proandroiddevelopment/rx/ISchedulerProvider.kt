package com.nikitabolshakov.proandroiddevelopment.rx

import io.reactivex.Scheduler

// In the sake of testing
interface ISchedulerProvider {

    fun ui(): Scheduler

    fun io(): Scheduler
}