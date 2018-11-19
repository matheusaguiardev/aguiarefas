package br.com.aguiar.aguirefas.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager

import android.view.Menu
import android.view.MenuItem
import android.view.View
import br.com.aguiar.aguirefas.R
import br.com.aguiar.aguirefas.adapter.TarefasAdapter
import br.com.aguiar.aguirefas.data.entity.TarefaEntity
import br.com.aguiar.aguirefas.data.repository.TarefaRepository

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.tarefas_list as taskRecycleView
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class MainActivity : AppCompatActivity() {

    private lateinit var repository: TarefaRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        actionFab()
    }

    override fun onStart() {
        super.onStart()
        repository = TarefaRepository()

        GlobalScope.launch (Main){
            configuraRecycleView()
        }
    }

    private suspend fun configuraRecycleView(){
        val adapter = TarefasAdapter(this, recuperarTarefas())
        val mLayoutManager = LinearLayoutManager(applicationContext)

        taskRecycleView.itemAnimator = DefaultItemAnimator()
        taskRecycleView.layoutManager = mLayoutManager
        taskRecycleView.adapter = adapter
    }

    private fun atualizarLista(){
        GlobalScope.launch (Main) {
           val adapter:TarefasAdapter = taskRecycleView.adapter as TarefasAdapter
            adapter.taskList = recuperarTarefas()
            taskRecycleView.adapter?.notifyDataSetChanged()
        }
    }

    private fun actionFab(){
        fab.setOnClickListener { _ ->
            val novaTarefaDialog = NovaTarefaDialogFragment.newInstance()
            novaTarefaDialog.tarefaCriadaCallBack(::salvarTarefa)
            novaTarefaDialog.show(supportFragmentManager,"Dialog")
        }
    }

    private suspend fun recuperarTarefas(): List<TarefaEntity>{
        return repository.buscarTarefas()
    }

    private fun salvarTarefa(){
         val tarefa = criarTarefaMocks()
        GlobalScope.launch (IO) {
            repository.salvarTarefa(tarefa)
            atualizarLista()
        }
    }


/*
    private fun criarTarefaMocks() = TarefaEntity(
        titulo = "Um titulo de tarefa qualquer",
        detalhe= "Descricao de tarefa qualquer apenas para testes",
        dataCriacao= Date(),
        dataConclusao=  Date(),
        alerta= Date(),
        concluido= false,
        icone= ContextCompat.getDrawable(this, R.drawable.ic_default),
        importancia= Importancia.MEDIA
    )
    */

    private fun criarTarefaMocks() = TarefaEntity(
        titulo = "Um titulo de tarefa qualquer",
        detalhe= "Descricao de tarefa qualquer apenas para testes",
        dataCriacao= "",
        dataConclusao= "",
        alerta= "",
        concluido= false,
        icone= 0,
        importancia= 2
    )

    private fun mostrarMensagem(view: View, mensagem: String){
        Snackbar.make(view, mensagem, Snackbar.LENGTH_LONG)
            .setAction("Ok", null).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
