package br.com.aguiar.aguirefas.data.repository

import br.com.aguiar.aguirefas.data.entity.TarefaEntity
import io.realm.Realm
import io.realm.RealmResults

import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class TarefaRepository {

    fun salvarTarefa(tarefa: TarefaEntity){
        val realmObject = realm()
        realmObject.executeTransactionAsync {
            val task = it.createObject(TarefaEntity::class.java)
            task.titulo = tarefa.titulo
            task.detalhe = tarefa.detalhe
            task.dataCriacao = tarefa.dataCriacao
            task.dataConclusao = tarefa.dataConclusao
            task.alerta = tarefa.alerta
        }

    }

    suspend fun buscarTarefas(): RealmResults<TarefaEntity> {
        return GlobalScope.async (Main) {
            val realm = realm()
            realm.where(TarefaEntity::class.java).findAll()
        }.await()
    }

    private fun realm() = Realm.getDefaultInstance()

}