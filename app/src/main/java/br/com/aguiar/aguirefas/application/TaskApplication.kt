package br.com.aguiar.aguirefas.application

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import io.realm.Realm
import io.realm.RealmConfiguration

class TaskApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        AndroidThreeTen.init(this)
    }

}