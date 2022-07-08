package com.banking.accountquery.infrastructure.handlers;

import com.banking.accountcommon.events.AccountClosedEvent;
import com.banking.accountcommon.events.AccountOpenedEvent;
import com.banking.accountcommon.events.FundsDepositedEvent;
import com.banking.accountcommon.events.FundsWithdrawnEvent;
import com.banking.accountquery.domain.AccountRepository;
import com.banking.accountquery.domain.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountEventHandler implements EventHandler{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void on(AccountOpenedEvent event) {
        var bankAccount = BankAccount.builder()
                .Id(event.getId())
                .accountHolder(event.getAccountHolder())
                .creationDate(event.getCreatedDate())
                .accountType(event.getType())
                .balance(event.getOpeningBalance())
                .build();

        accountRepository.save(bankAccount);
    }

    @Override
    public void on(FundsDepositedEvent event) {
        var bankAccount = accountRepository.findById(event.getId());
        if (bankAccount.isEmpty()) {
            return;
        }

        var currentBalance = bankAccount.get().getBalance();
        var finalBalance = currentBalance + event.getAmount();
        bankAccount.get().setBalance(finalBalance);
        accountRepository.save(bankAccount.get());
    }

    @Override
    public void on(FundsWithdrawnEvent event) {
        var bankAccount = accountRepository.findById(event.getId());
        if (bankAccount.isEmpty()) {
            return;
        }
        var currentBalance = bankAccount.get().getBalance();
        var finalBalance = currentBalance - event.getAmount();
        bankAccount.get().setBalance(finalBalance);
        accountRepository.save(bankAccount.get());
    }

    @Override
    public void on(AccountClosedEvent event) {
        accountRepository.deleteById(event.getId());
    }
}
