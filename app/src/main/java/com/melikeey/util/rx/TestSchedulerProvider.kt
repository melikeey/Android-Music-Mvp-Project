package com.melikeey.util.rx

import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler

class TestSchedulerProvider(private val mTestScheduler: TestScheduler) : SchedulerProvider {
    override fun ui(): Scheduler? {
        return mTestScheduler
    }

    override fun io(): Scheduler? {
        return mTestScheduler
    }
}