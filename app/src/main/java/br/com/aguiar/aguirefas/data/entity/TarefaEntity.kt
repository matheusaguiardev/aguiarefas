package br.com.aguiar.aguirefas.data.entity

import android.graphics.drawable.Drawable
import br.com.aguiar.aguirefas.enumeravel.Importancia
import io.realm.RealmObject
import org.threeten.bp.Instant
import java.util.*
/*
data class TarefaEntity(
    var titulo: String = "",
    var detalhe: String = "",
    var dataCriacao: Date = Date(),
    var dataConclusao: Date = Date(),
    var alerta: Date = Date(),
    var concluido: Boolean = false,
    var icone: Drawable? = null,
    var importancia: Importancia = Importancia.BAIXA
): RealmObject()

*/

open class TarefaEntity(
    var titulo: String = "",
    var detalhe: String = "",
    var dataCriacao: String = "",
    var dataConclusao: String = "",
    var alerta: String = "",
    var concluido: Boolean = false,
    var icone: Int? = 0,
    var importancia: Int = 0
): RealmObject()