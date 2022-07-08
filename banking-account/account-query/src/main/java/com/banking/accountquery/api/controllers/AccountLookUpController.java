package com.banking.accountquery.api.controllers;

import com.banking.accountquery.api.dto.AccountLookUpResponse;
import com.banking.accountquery.api.dto.EqualityType;
import com.banking.accountquery.api.queries.FindAccountByHolderQuery;
import com.banking.accountquery.api.queries.FindAccountByIdQuery;
import com.banking.accountquery.api.queries.FindAccountWithBalanceQuery;
import com.banking.accountquery.api.queries.FindAllAccountsQuery;
import com.banking.accountquery.domain.BankAccount;
import com.banking.cqrs.core.infrastructure.QueryDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/bankAccountLookUp")
public class AccountLookUpController {

    private final Logger logger = Logger.getLogger(AccountLookUpController.class.getName());
    @Autowired
    private QueryDispatcher queryDispatcher;

    @GetMapping("/")
    public ResponseEntity<AccountLookUpResponse> getAllAccounts(){
        try{
            List<BankAccount> accounts = queryDispatcher.send(new FindAllAccountsQuery());
            if (accounts == null || accounts.isEmpty()){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            var response = AccountLookUpResponse.builder()
                    .accounts(accounts)
                    .message(MessageFormat.format("Se realizo la consulta con exito",null))
                    .build();
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            var safeErrorMessage = "Errores mientras se ejecuta la consulta";
            logger.log(Level.SEVERE,safeErrorMessage,e);
            return new ResponseEntity<>(new AccountLookUpResponse(safeErrorMessage),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<AccountLookUpResponse> getAccountById(@PathVariable(value = "id") String id){
        try{
            List<BankAccount> accounts = queryDispatcher.send(new FindAccountByIdQuery(id));
            if (accounts == null || accounts.isEmpty()){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            var response = AccountLookUpResponse.builder()
                    .accounts(accounts)
                    .message(MessageFormat.format("Se realizo la consulta con exito",null))
                    .build();
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            var safeErrorMessage = "Errores mientras se ejecuta la consulta";
            logger.log(Level.SEVERE,safeErrorMessage,e);
            return new ResponseEntity<>(new AccountLookUpResponse(safeErrorMessage),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byHolder/{holder}")
    public ResponseEntity<AccountLookUpResponse> getAccountByHolder(@PathVariable(value = "holder") String holder){
        try{
            List<BankAccount> accounts = queryDispatcher.send(new FindAccountByHolderQuery(holder));
            if (accounts == null || accounts.isEmpty()){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            var response = AccountLookUpResponse.builder()
                    .accounts(accounts)
                    .message(MessageFormat.format("Se realizo la consulta con exito",null))
                    .build();
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            var safeErrorMessage = "Errores mientras se ejecuta la consulta";
            logger.log(Level.SEVERE,safeErrorMessage,e);
            return new ResponseEntity<>(new AccountLookUpResponse(safeErrorMessage),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byBalance/{balance}/{equalityType}")
    public ResponseEntity<AccountLookUpResponse> getAccountByBalance(@PathVariable(value = "balance") double balance,@PathVariable(value = "equalityType") EqualityType type){
        try{
            List<BankAccount> accounts = queryDispatcher.send(new FindAccountWithBalanceQuery(balance,type));
            if (accounts == null || accounts.isEmpty()){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            var response = AccountLookUpResponse.builder()
                    .accounts(accounts)
                    .message(MessageFormat.format("Se realizo la consulta con exito",null))
                    .build();
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            var safeErrorMessage = "Errores mientras se ejecuta la consulta";
            logger.log(Level.SEVERE,safeErrorMessage,e);
            return new ResponseEntity<>(new AccountLookUpResponse(safeErrorMessage),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
