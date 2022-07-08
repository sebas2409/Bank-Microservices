package com.banking.accountquery.api.queries;

import com.banking.accountquery.api.dto.EqualityType;
import com.banking.accountquery.domain.AccountRepository;
import com.banking.cqrs.core.domain.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountQueryHandler implements QueryHandler{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<BaseEntity> handle(FindAllAccountsQuery query) {
        var bankAccounts = accountRepository.findAll();
        List<BaseEntity> bankAccountList = new ArrayList<>();
        bankAccounts.forEach(bankAccountList::add);
        return bankAccountList;
    }

    @Override
    public List<BaseEntity> handle(FindAccountByIdQuery query) {
        var bankAccount = accountRepository.findById(query.getId());
        if (bankAccount.isEmpty()){
            return null;
        }

        List<BaseEntity> bankAccountList = new ArrayList<>();
        bankAccountList.add(bankAccount.get());
        return bankAccountList;
    }

    @Override
    public List<BaseEntity> handle(FindAccountByHolderQuery query) {
        var bankAccount = accountRepository.findByAccountHolder(query.getAccounHolder());
        if (bankAccount.isEmpty()){
            return null;
        }
        List<BaseEntity> bankAccountList = new ArrayList<>();
        bankAccountList.add(bankAccount.get());
        return bankAccountList;
    }

    @Override
    public List<BaseEntity> handle(FindAccountWithBalanceQuery query) {

        return query.getEqualityType() == EqualityType.GREATER_THAN
                ? accountRepository.findByBalanceGreaterThan(query.getBalance())
                :accountRepository.findByBalanceLessThan(query.getBalance());
    }
}
