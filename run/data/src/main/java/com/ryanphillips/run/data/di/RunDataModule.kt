package com.ryanphillips.run.data.di

import com.ryanphillips.core.domain.run.SyncRunScheduler
import com.ryanphillips.run.data.CreateRunWorker
import com.ryanphillips.run.data.DeleteRunWorker
import com.ryanphillips.run.data.FetchRunsWorker
import com.ryanphillips.run.data.SyncRunWorkerScheduler
import org.koin.androidx.workmanager.dsl.workerOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val runDataModule = module {
    workerOf(::CreateRunWorker)
    workerOf(::FetchRunsWorker)
    workerOf(::DeleteRunWorker)

    singleOf(::SyncRunWorkerScheduler).bind<SyncRunScheduler>()
}