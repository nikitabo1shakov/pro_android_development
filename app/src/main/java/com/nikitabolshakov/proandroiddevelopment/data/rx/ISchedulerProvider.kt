package com.nikitabolshakov.proandroiddevelopment.data.rx

import io.reactivex.Scheduler

interface ISchedulerProvider {
    fun ui(): Scheduler
    fun io(): Scheduler
}