package com.meu.bankline.ui.statement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvBankStatement.layoutManager = LinearLayoutManager(this)

        findBankStatement()
    }

    private fun findBankStatement() {
        val dataSet = ArrayList<Movimentacao>()
        dataSet.add(Movimentacao(1, "03/05/2022 09:25:21", "Lorem Ipsum", 1000.00, TipoMovimentacao.RECEITA, 1))
        dataSet.add(Movimentacao(1, "03/05/2022 09:25:21", "Lorem Ipsum", 1000.00, TipoMovimentacao.DESPESA, 1))
        dataSet.add(Movimentacao(1, "03/05/2022 09:25:21", "Lorem Ipsum", 1000.00, TipoMovimentacao.RECEITA, 1))
        dataSet.add(Movimentacao(1, "03/05/2022 09:25:21", "Lorem Ipsum", 1000.00, TipoMovimentacao.DESPESA, 1))
        dataSet.add(Movimentacao(1, "03/05/2022 09:25:21", "Lorem Ipsum", 1000.00, TipoMovimentacao.RECEITA, 1))
        binding.rvBankStatement.adapter = BankStatementAdapter(dataSet)
    }
}