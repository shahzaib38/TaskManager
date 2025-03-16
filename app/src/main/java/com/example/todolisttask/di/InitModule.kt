package com.example.todolisttask.di

import android.content.Context
import com.example.todolisttask.database.TaskDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object InitModule {


    @Singleton
    @Provides
    fun provideMediaDb( @ApplicationContext context : Context) : TaskDb {
        return TaskDb.getDatabase(context) }




}