package br.com.aguiar.aguirefas.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.aguiar.aguirefas.R
import br.com.aguiar.aguirefas.data.entity.TarefaEntity
import br.com.aguiar.aguirefas.ui.TarefaCustomItem


class TarefasAdapter(val context: Context, lista: List<TarefaEntity>): RecyclerView.Adapter<TarefaCustomItem>() {

    var taskList: List<TarefaEntity> = lista

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaCustomItem {
       val itemView = LayoutInflater.from(context).inflate(R.layout.custom_item_task, parent, false)
        return TarefaCustomItem(itemView)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(itemView: TarefaCustomItem, position: Int) {
        val tarefaItem = taskList[position]

        //itemView.icon.setImageDrawable(tarefaItem.icone)
        itemView.finished.isChecked = tarefaItem.concluido
        itemView.title.text = tarefaItem.titulo
    }

}