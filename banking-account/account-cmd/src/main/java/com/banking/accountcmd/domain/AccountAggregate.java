package com.banking.accountcmd.domain;

import com.banking.accountcmd.api.command.OpenAccountCommand;
import com.banking.accountcommon.events.AccountClosedEvent;
import com.banking.accountcommon.events.AccountOpenedEvent;
import com.banking.accountcommon.events.FundsDepositedEvent;
import com.banking.accountcommon.events.FundsWithdrawnEvent;
import com.banking.cqrs.core.domain.AggregateRoute;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class AccountAggregate extends AggregateRoute {
    private Boolean active;
    private double balance;

    public AccountAggregate(OpenAccountCommand command){
        raiseEvent(AccountOpenedEvent.builder()
                .id(command.getId())
                .accountHolder(command.getAccountHolder())
                .openingBalance(command.getOpeningBalance())
                .createdDate(new Date())
                .type(command.getType())
                .build()
        );
    }

    public void apply(AccountOpenedEvent event){
        this.id = event.getId();
        this.active = true;
        this.balance = event.getOpeningBalance();
    }

    public void depositFunds(double amount){
        if (!this.active){
            throw new IllegalStateException("Los fondos no pueden ser depositados en esta cuenta");
        }

        if (amount <=0 ){
            throw new IllegalStateException("El deposito de dinero no puede ser cero o negativo");
        }

        raiseEvent(FundsDepositedEvent.builder()
                .id(this.id)
                .amount(amount)
                .build()
        );
    }

    public void apply(FundsDepositedEvent event){
        this.id= event.getId();
        this.balance += event.getAmount();
    }

    public void withdrawFunds(double amount){
        if (!active){
            throw new IllegalStateException("La cuenta bancaria esta cerrada");
        }

        raiseEvent(FundsWithdrawnEvent.builder()
                .id(this.id)
                .amount(amount)
                .build()
        );
    }

    public void apply(FundsWithdrawnEvent event){
        this.id= event.getId();
        this.balance -= event.getAmount();
    }

    public void closeAccount(){
        if (!active){
            throw new IllegalStateException("La cuenta se banco ya esta cerrada ");
        }

        raiseEvent(AccountClosedEvent.builder()
                .id(this.id)
                .build()
        );
    }

    public void apply(AccountClosedEvent event){
        this.id = event.getId();
        this.active=false;
    }

}
