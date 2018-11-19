package br.com.aguiar.aguirefas.ui

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.aguiar.aguirefas.R
import kotlinx.android.synthetic.main.fragment_dialog_nova_tarefa.*

class NovaTarefaDialogFragment: DialogFragment() {

    private lateinit var callBack: () -> Unit

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_dialog_nova_tarefa, container, false)
    }

    override fun onStart() {
        super.onStart()
        criarTarefaButton.setOnClickListener {button -> onClickButton(button)}
    }

    companion object {
       fun newInstance(): NovaTarefaDialogFragment{
           return NovaTarefaDialogFragment()
        }
    }

    fun tarefaCriadaCallBack(func: () -> Unit ){
        callBack = func
    }

    private fun onClickButton(view: View){
        when(view.id){
            criarTarefaButton.id -> {
                callBack.invoke()
                dismiss()
            }
        }

    }

}