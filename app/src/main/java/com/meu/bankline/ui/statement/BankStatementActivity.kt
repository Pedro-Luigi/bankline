package com.meu.bankline.ui.statement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.meu.bankline.data.State
import com.meu.bankline.databinding.ActivityBankStatementBinding
import com.meu.bankline.domain.Correntista
import com.meu.bankline.domain.Movimentacao
import com.meu.bankline.domain.TipoMovimentacao
import java.lang.IllegalArgumentException

class BankStatementActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityBankStatementBinding.inflate(layoutInflater)
    }

    companion object {
        const val EXTRA_ACCOUNT_HOLDER = "com.meu.bankline_android.iu.statement.EXTRA_ACCOUNT_HOLDER"
    }

    //Activity is waiting for a value.
    private val accountHolder by lazy {
        intent.getParcelableExtra<Correntista>(EXTRA_ACCOUNT_HOLDER) ?: throw IllegalArgumentException()
    }

    private val viewModel by viewModels<BankStatementViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvBankStatement.layoutManager = LinearLayoutManager(this)

        findBankStatement()

        binding.srlBankStatement.setOnRefreshListener { findBankStatement() }
    }

    private fun findBankStatement() {
        viewModel.findBankStatement(accountHolder.id).observe(this){ state ->
            when(state){
                is State.Success -> {
                    //SE EXISTIR VALOR PARA state.date, EXECUTE BankStatementAdapter(it)
                    binding.rvBankStatement.adapter = state.data?.let { BankStatementAdapter(it) }
                    binding.srlBankStatement.isRefreshing = false
                }
                is State.Error -> {
                    state.message?.let { Snackbar.make(binding.rvBankStatement, it, Snackbar.LENGTH_LONG).show() }
                    binding.srlBankStatement.isRefreshing = false
                }
                State.Wait -> binding.srlBankStatement.isRefreshing = true
            }

        }
    }
}