package br.com.aguiar.aguirefas.ui


import android.support.v7.widget.AppCompatCheckBox
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.custom_item_task.view.*

import kotlinx.android.synthetic.main.custom_item_task.view.iconeImageView as icone
import kotlinx.android.synthetic.main.custom_item_task.view.tituloTextView as titulo
import kotlinx.android.synthetic.main.custom_item_task.view.concluidoCheckBox as concluido
import kotlinx.android.synthetic.main.custom_item_task.view.quandoTextView as quando

class TarefaCustomItem(view: View): RecyclerView.ViewHolder(view) {

    var icon: AppCompatImageView = view.icone
    var title: AppCompatTextView = view.titulo
    var finished: AppCompatCheckBox = view.concluido
    var concluir: AppCompatTextView = view.quando

}