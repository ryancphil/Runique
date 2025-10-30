package com.ryanphillips.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ryanphillips.core.database.dao.RunDao
import com.ryanphillips.core.database.dao.RunPendingSyncDao
import com.ryanphillips.core.database.entity.DeletedRunSyncEntity
import com.ryanphillips.core.database.entity.RunEntity
import com.ryanphillips.core.database.entity.RunPendingSyncEntity

@Database(
    entities = [RunEntity::class, RunPendingSyncEntity::class, DeletedRunSyncEntity::class],
    version = 1
)
abstract class RunDatabase : RoomDatabase() {

    abstract val runDao: RunDao
    abstract val runPendingSyncDao: RunPendingSyncDao
}