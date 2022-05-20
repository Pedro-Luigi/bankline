package com.meu.bankline.ui.statement

import androidx.lifecycle.ViewModel
import com.meu.bankline.data.BanklineRepository

class BankStatementViewModel: ViewModel() {

    fun findBankStatement(accountHolderId: Int) =
        BanklineRepository.findBankStatement(accountHolderId)

}