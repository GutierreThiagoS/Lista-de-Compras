package com.example.listadecompras

import android.app.Application
import androidx.room.Room
import com.example.listadecompras.data.local.AppDataBase

class App: Application() {

    companion object{
        lateinit var context: App
        lateinit var db: AppDataBase
    }

    override fun onCreate() {
        super.onCreate()

        context = this

        db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,
            "database-shopping-note"
        ).build()
    }
}